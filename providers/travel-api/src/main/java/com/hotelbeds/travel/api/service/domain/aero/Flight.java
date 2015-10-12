package com.hotelbeds.travel.api.service.domain.aero;

import java.util.Date;

public class Flight {
	
	private String 		flightNumber;
	private String 		aptDep;
	private String 		aptArr;
	private Date   		fecDep;
	private Date   		fecArr;

	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getAptDep() {
		return aptDep;
	}
	public void setAptDep(String aptDep) {
		this.aptDep = aptDep;
	}
	public String getAptArr() {
		return aptArr;
	}
	public void setAptArr(String aptArr) {
		this.aptArr = aptArr;
	}
	public Date getFecDep() {
		return fecDep;
	}
	public void setFecDep(Date fecDep) {
		this.fecDep = fecDep;
	}
	public Date getFecArr() {
		return fecArr;
	}
	public void setFecArr(Date fecArr) {
		this.fecArr = fecArr;
	}
}
