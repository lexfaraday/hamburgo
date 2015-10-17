package test.backend.www.model.eventbrite.domain;

import lombok.Data;

@Data
public class EventBean {
	String title;
	String description;
	String id;
	String startDate;
	String endDate;
	String capacity;
	String currency;
	String image;
	VenueBean venue;
}
