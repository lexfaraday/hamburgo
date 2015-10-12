package com.hotelbeds.travel.api.service.domain.aero;

import java.math.BigDecimal;
import java.util.List;

public class AeroSearchRS {
	private BigDecimal amount;
	private String     currency;
	private List<Flight> flights;
	private String magicString;
	
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
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
	public String getMagicString() {
		return magicString;
	}
	public void setMagicString(String magicString) {
		this.magicString = magicString;
	}

}
