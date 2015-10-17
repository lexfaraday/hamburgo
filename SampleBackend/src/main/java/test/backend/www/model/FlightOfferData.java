package test.backend.www.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class FlightOfferData {
	private final String flightNumber;
	private final String airline;
	private final BigDecimal price;
	private final String currency;
	private final String departureAirport;
	private final String arrivalAirport;
	private final Date arrival;
	private final Date departure;
}
