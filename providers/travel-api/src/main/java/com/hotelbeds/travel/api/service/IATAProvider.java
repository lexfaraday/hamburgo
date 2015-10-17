package com.hotelbeds.travel.api.service;


import java.text.ParseException;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.iata.ndc.NdcClient;
import org.iata.ndc.builder.AirShoppingRQBuilder;
import org.iata.ndc.schema.AirShoppingRQ;
import org.iata.ndc.schema.AirShoppingRS;
import org.iata.ndc.schema.OrderCreateRQ;
import org.iata.ndc.schema.OrderViewRS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelbeds.travel.api.profiles.ApplicationProfile;





@Service
public class IATAProvider {

	@Autowired
	private ApplicationProfile appProfile;
	
   	private NdcClient client;
   	
   	@PostConstruct
   	public void init(){
   		client = new NdcClient(appProfile.getIataUrl(), appProfile.getIataKey());
   	}
    
    
    public void bookFlight() {
    	OrderCreateRQ rq = new OrderCreateRQ();
    	
    	try {
			OrderViewRS rs = client.orderCreate(rq);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }

    public AirShoppingRS searchFlights(Date depDate, Date arrDate, String origin, String destination) throws ParseException  {
    	    	
    	AirShoppingRQBuilder builder = new AirShoppingRQBuilder();
    	
    	builder.addTravelAgencySender("Hovee agency", "0000XXXX", "hovee agent");
    	builder.addOriginDestination(origin, destination, depDate);
    	builder.addAirlinePreference("C9");

    	AirShoppingRQ request = builder.build();

    	AirShoppingRS response = null;
    	try {
    	    response = client.airShopping(request);
    	} catch (Exception e) {
    		e.printStackTrace();
         
    	}
    	return response;

    }
    

}
