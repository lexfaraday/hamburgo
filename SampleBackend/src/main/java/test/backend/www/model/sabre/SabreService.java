package test.backend.www.model.sabre;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import test.backend.www.model.sabre.SabreOriginDestinationLocationsResponse.SabreLocation;
import test.backend.www.model.sabre.SabreOriginDestinationLocationsResponse.SabreOriginDestinationLocation;

@Data
@Slf4j
public class SabreService {
	private static final String ORIGIN_DESTINATIONS_PATH = "v1/lists/supported/shop/flights/origins-destinations";
	private static final String FLIGHTS_PATH = "v1/shop/flights";
	public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

	private final DSCommHandler dsC = new DSCommHandler();

	private final ObjectMapper mapper = new ObjectMapper();

	private final String baseUrl;

	private final String token;

	private Map<String, SabreLocation> locations = null;

	private Map<String, Set<String>> originPairs = null;
	private Map<String, Set<String>> destinationPairs = null;

	public SabreService(String baseUrl, String key, String secret) {
		this.baseUrl = baseUrl;

		// base64 encode clientId and clientSecret
		String encodedClientId = Base64.encodeBase64String(key.getBytes());
		String encodedClientSecret = Base64.encodeBase64String(secret.getBytes());
		// Concatenate encoded client and secret strings, separated with colon
		String encodedClientIdSecret = String.join(":", encodedClientId, encodedClientSecret);
		// Convert the encoded concatenated string to a single base64 encoded
		// string.
		encodedClientIdSecret = Base64.encodeBase64String(encodedClientIdSecret.getBytes());
		this.token = dsC.getAuthToken(baseUrl, encodedClientIdSecret);
	}

	public void initSabreOriginDestinationLocations() throws IOException {
		locations = new HashMap<>();
		originPairs = new HashMap<>();
		destinationPairs = new HashMap<>();

		String response = dsC.sendRequest(composeUrl(ORIGIN_DESTINATIONS_PATH), token);
		try {
			SabreOriginDestinationLocationsResponse sabreOriginDestinationLocationsResponse = mapper.readValue(response,
					SabreOriginDestinationLocationsResponse.class);
			for (SabreOriginDestinationLocation originDestinationLocation : sabreOriginDestinationLocationsResponse
					.getOriginDestinationLocations()) {
				String originCode = originDestinationLocation.getOriginLocation().getAirportCode();
				String destinationCode = originDestinationLocation.getDestinationLocation().getAirportCode();
				if (!locations.containsKey(originCode)) {
					locations.put(originCode, originDestinationLocation.getOriginLocation());
				}
				if (!locations.containsKey(destinationCode)) {
					locations.put(destinationCode, originDestinationLocation.getDestinationLocation());
				}
				if (!originPairs.containsKey(originCode)) {
					originPairs.put(originCode, new HashSet<>());
				}
				originPairs.get(originCode).add(destinationCode);
				if (!destinationPairs.containsKey(destinationCode)) {
					destinationPairs.put(destinationCode, new HashSet<>());
				}
				destinationPairs.get(destinationCode).add(originCode);
			}
			log.info("Origin-destination maps initialised: {} locations, {} pairs", locations.size(),
					originPairs.size());
		} catch (Exception e) {
			log.error("Error intialising origin-destinations list", e);
		}
	}

	public SabrePricedItineraries getFlights(String country, String origin, String destination, LocalDate from,
			LocalDate to) throws IOException {
		SabrePricedItineraries sabrePricedItineraries = null;

		StringBuilder sb = new StringBuilder(composeUrl(FLIGHTS_PATH));
		sb.append("?origin=");
		sb.append(origin);
		sb.append("&");
		sb.append("destination=");
		sb.append(destination);
		sb.append("&");
		sb.append("departuredate=");
		sb.append(from.format(DATE_FORMATTER));
		sb.append("&");
		sb.append("returndate=");
		sb.append(to.format(DATE_FORMATTER));
		sb.append("&");
		sb.append("pointofsalecountry=");
		sb.append(country);
		sb.append("&");
		sb.append("outboundflightstops=");
		sb.append("0");
		sb.append("&");
		sb.append("inboundflightstops=");
		sb.append("0");

		String response = dsC.sendRequest(sb.toString(), token);
		if (response != null) {
			try {
				sabrePricedItineraries = mapper.readValue(response, SabrePricedItineraries.class);
			} catch (IOException e) {
				log.error("Error reading response", e);
			}
		}
		return sabrePricedItineraries;
	}

	private String composeUrl(String relativePath) {
		return String.join("/", this.baseUrl, relativePath);
	}

}
