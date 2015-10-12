package com.hotelbeds.travel.api.service.domain.aero;

public class AeroSearchRQ {
	
	private static final String SLASH = "/";
	private String version 			= "v1";	//v1
	private String airline; 				//MH
	private String dep; 					//DUB
	private String arr;						//LHR
	private String adults;					//2
	private String cabin;					//Economy Flexible
	private String childrens;				//1				
	private String infants;					//1
	private String outboundDate;			//2013-09-13
	private String returnDate;				//2013-10-30
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public String getDep() {
		return dep;
	}
	public void setDep(String dep) {
		this.dep = dep;
	}
	public String getArr() {
		return arr;
	}
	public void setArr(String arr) {
		this.arr = arr;
	}
	public String getAdults() {
		return adults;
	}
	public void setAdults(String adults) {
		this.adults = adults;
	}
	public String getCabin() {
		return cabin;
	}
	public void setCabin(String cabin) {
		this.cabin = cabin;
	}
	public String getChildrens() {
		return childrens;
	}
	public void setChildrens(String childrens) {
		this.childrens = childrens;
	}
	public String getInfants() {
		return infants;
	}
	public void setInfants(String infants) {
		this.infants = infants;
	}
	public String getOutboundDate() {
		return outboundDate;
	}
	public void setOutboundDate(String outboundDate) {
		this.outboundDate = outboundDate;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	
	public String getPathAPI(){
		return "/shop/"+this.version+SLASH+this.airline+SLASH+this.dep+SLASH+this.arr+
				SLASH+this.adults+SLASH+this.cabin+SLASH+this.childrens+SLASH+this.infants+
				SLASH+this.outboundDate+SLASH+returnDate;
	};

}
