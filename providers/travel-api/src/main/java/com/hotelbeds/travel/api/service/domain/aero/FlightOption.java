package com.hotelbeds.travel.api.service.domain.aero;

import java.math.BigDecimal;
import java.util.List;

public class FlightOption {
	private Double amount;
	private String     currency;
	private List<Flight> flights;
	
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public List<Flight> getFlights() {
		return flights;
	}
	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

}
