package test.backend.www.model;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
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
