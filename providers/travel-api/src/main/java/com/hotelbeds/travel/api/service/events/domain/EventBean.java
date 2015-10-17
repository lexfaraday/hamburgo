package com.hotelbeds.travel.api.service.events.domain;

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
	String aspectRadio;
	String latitude;
	String longitude;
}
