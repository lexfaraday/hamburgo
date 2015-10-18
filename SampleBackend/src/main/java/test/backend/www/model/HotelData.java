package test.backend.www.model;

import lombok.Data;

@Data
public class HotelData {
	private final String name;
	private final String amount;
	private final String currency;
	private final String checkin;
	private final String checkout;
	private final String category;
}
