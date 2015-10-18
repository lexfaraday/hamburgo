package test.backend.www.model.iata;

import java.text.ParseException;
import java.util.Date;

import org.iata.ndc.NdcClient;
import org.iata.ndc.builder.AirShoppingRQBuilder;
import org.iata.ndc.schema.AirShoppingRQ;
import org.iata.ndc.schema.AirShoppingRS;

import lombok.Data;

@Data
public class IataService {

	private final NdcClient client;

	public IataService(String baseUrl, String key) {
		client = new NdcClient(baseUrl, key);
	}

	public AirShoppingRS searchFlights(Date depDate, String origin, String destination) throws ParseException {

		AirShoppingRQBuilder builder = new AirShoppingRQBuilder();

		builder.addTravelAgencySender("Hovee agency", "0000XXXX", "hovee agent");
		builder.addOriginDestination(origin, destination, depDate);
		builder.addAirlinePreference("C9");

		AirShoppingRQ request = builder.build();

		AirShoppingRS response = null;
		try {
			response = client.airShopping(request);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return response;

	}
}
