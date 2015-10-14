package test.backend.www.model.sabre;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.codec.binary.Base64;

import test.backend.www.model.sabre.SabreOriginDestinationLocationsResponse.SabreLocation;
import test.backend.www.model.sabre.SabreOriginDestinationLocationsResponse.SabreOriginDestinationLocation;

import com.fasterxml.jackson.databind.ObjectMapper;

@Data
@Slf4j
public class SabreService
{
  private static final String ORIGIN_DESTINATIONS_PATH = "v1/lists/supported/shop/flights/origins-destinations";
  private static final String FLIGHTS_PATH = "v1/shop/flights";
  public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

  private final DSCommHandler dsC = new DSCommHandler();

  private final ObjectMapper mapper = new ObjectMapper();

  private final String baseUrl;

  private final String token;

  private Map<String, SabreLocation> locations = null;

  private Map<String, Set<String>> pairs = null;

  public SabreService(String baseUrl, String key, String secret)
  {
    this.baseUrl = baseUrl;

    // base64 encode clientId and clientSecret
    String encodedClientId = Base64.encodeBase64String((key).getBytes());
    String encodedClientSecret = Base64.encodeBase64String((secret).getBytes());
    // Concatenate encoded client and secret strings, separated with colon
    String encodedClientIdSecret = String.join(":", encodedClientId, encodedClientSecret);
    // Convert the encoded concatenated string to a single base64 encoded string.
    encodedClientIdSecret = Base64.encodeBase64String(encodedClientIdSecret.getBytes());
    this.token = dsC.getAuthToken(baseUrl, encodedClientIdSecret);
  }

  public void initSabreOriginDestinationLocations()
  {
    locations = new HashMap<>();
    pairs = new HashMap<>();

    String response = dsC.sendRequest(composeUrl(ORIGIN_DESTINATIONS_PATH), token);
    try
    {
      SabreOriginDestinationLocationsResponse sabreOriginDestinationLocationsResponse = mapper
                                                                                              .readValue(response,
                                                                                                         SabreOriginDestinationLocationsResponse.class);
      for (SabreOriginDestinationLocation originDestinationLocation : sabreOriginDestinationLocationsResponse.getOriginDestinationLocations())
      {
        String originCode = originDestinationLocation.getOriginLocation().getAirportCode();
        String destinationCode = originDestinationLocation.getDestinationLocation().getAirportCode();
        if (!locations.containsKey(originCode))
        {
          locations.put(originCode, originDestinationLocation.getOriginLocation());
        }
        if (!locations.containsKey(destinationCode))
        {
          locations.put(destinationCode, originDestinationLocation.getDestinationLocation());
        }
        if (!pairs.containsKey(originCode))
        {
          pairs.put(originCode, new HashSet<>());
        }
        pairs.get(originCode).add(destinationCode);
      }
      log.info("Origin-destination maps initialised: {} locations, {} pairs", locations.size(), pairs.size());
    }
    catch (Exception e)
    {
      log.error("Error intialising origin-destinations list", e);
    }
  }

  public SabrePricedItineraries getFlights(String origin, String destination, LocalDate from, LocalDate to)
  {
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
    // sb.append("&");
    // sb.append("outboundflightstops=");
    // sb.append("2");
    // sb.append("&");
    // sb.append("inboundflightstops=");
    // sb.append("2");

    String response = dsC.sendRequest(sb.toString(), token);

    try
    {
      sabrePricedItineraries = mapper.readValue(response, SabrePricedItineraries.class);
    }
    catch (IOException e)
    {
      log.error("Error reading response", e);
    }

    return sabrePricedItineraries;
  }

  private String composeUrl(String relativePath)
  {
    return String.join("/", this.baseUrl, relativePath);
  }

}