package test.backend.www.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class FlightOfferData {
	private final String flightNumber;
	private final String airline;
	private final String departureAirport;
	private final String arrivalAirport;
	private final Date arrival;
	private final Date departure;

	private BigDecimal price;
	private String currency;

}
