package com.hotelbeds.travel.api.service;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotelbeds.travel.api.profiles.ApplicationProfile;
import com.hotelbeds.travel.api.service.domain.sita.Airport;
import com.hotelbeds.travel.api.service.domain.sita.AirportResponse;





@Service
public class SITAProvider {


    @Autowired
    private RestTemplate restTemplate;
    
	@Autowired
	private ApplicationProfile appProfile;
    
    
    public List<Airport> findAirportsByGeoPos(String lat, String lng, String max) throws JsonParseException, JsonMappingException, IOException{
    	
    	String response = restTemplate.getForObject("https://airport.api.aero/airport/nearest/"+lat+"/"+lng+"?maxAirports="+max+"&user_key="+appProfile.getSitaKey(), String.class);
    	
    	ObjectMapper mapper = new ObjectMapper();
    	AirportResponse airportResponse = mapper.readValue(response, AirportResponse.class);
    	
    	return airportResponse.getAirports();
    	
    }
    

    

}
