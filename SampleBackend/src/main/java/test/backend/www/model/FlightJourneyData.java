package test.backend.www.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class FlightJourneyData {
	private final FlightOfferData going;
	private final FlightOfferData coming;
	private final BigDecimal price;
	private final String currency;
}
