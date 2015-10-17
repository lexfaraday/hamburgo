package test.backend.www.model.sita;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import test.backend.www.model.sita.domain.Airport;
import test.backend.www.model.sita.domain.AirportResponse;

@Data
@Slf4j
public class SitaService {
	public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_INSTANT;

	@Autowired
	private RestTemplate restTemplate;

	private String baseUrl;
	private String key;

	public SitaService(String baseUrl, String key) {
		this.baseUrl = baseUrl;
		this.key = key;
	}

	public List<Airport> findAirportsByGeoPos(String lat, String lng, int max)
			throws JsonParseException, JsonMappingException, IOException {

		String response = restTemplate.getForObject(
				this.baseUrl + "/airport/nearest/" + lat + "/" + lng + "?maxAirports=" + max + "&user_key=" + this.key,
				String.class);

		ObjectMapper mapper = new ObjectMapper();
		AirportResponse airportResponse = mapper.readValue(response, AirportResponse.class);

		return airportResponse.getAirports();

	}
}
