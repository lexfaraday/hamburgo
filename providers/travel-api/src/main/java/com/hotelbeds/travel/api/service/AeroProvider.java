package com.hotelbeds.travel.api.service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotelbeds.travel.api.service.domain.aero.Booking;
import com.hotelbeds.travel.api.service.domain.aero.Flight;
import com.hotelbeds.travel.api.service.domain.aero.FlightOption;


@Service
public class AeroProvider {

    //private static final String OAUTH_TOKEN = "";

    //@Autowired
    //private OAuth2RestOperations restTemplate;

    //    @Autowired
    //    private RestTemplate restTemplate;

    private HttpEntity<String> prepareHttpEntity() {
        //        HttpHeaders httpHeaders = new HttpHeaders();
        //        httpHeaders.add(headerName, headerValue);
        //        return new HttpEntity<String>("", httpHeaders);
        return null;
    }
    
    public String bookFlight() throws JsonProcessingException, IOException{
    	Booking booking = new Booking();
    	//read json file data to String
    	byte[] jsonData = getResponse("reservation").getBytes();
    	 
    	//create ObjectMapper instance
    	ObjectMapper objectMapper = new ObjectMapper();
    	 
    	//read JSON like DOM Parser
    	JsonNode rootNode = objectMapper.readTree(jsonData);
    	booking.setRef(rootNode.path("airReservation").path("bookingReferenceID").asText());
    	
    	return objectMapper.writeValueAsString(booking);
    }

    public String searchFlights() throws JsonProcessingException, IOException, ParseException {
    	
    	//read json file data to String
    	byte[] jsonData = getResponse("shop").getBytes();
    	 
    	//create ObjectMapper instance
    	ObjectMapper objectMapper = new ObjectMapper();
    	 
    	//read JSON like DOM Parser
    	JsonNode rootNode = objectMapper.readTree(jsonData);

    	JsonNode outboundOptionsNode = rootNode.path("outboundOptions");
    	Iterator<JsonNode> elements = outboundOptionsNode.elements();
    	List<FlightOption> options = new ArrayList<FlightOption>(); 
    	FlightOption option;
    	List<Flight> flights;
    	Flight flight = new Flight();
    	while(elements.hasNext()){
    	    JsonNode outboundOption = elements.next();
    	    
    	    option = new FlightOption();
    	    option.setAmount(outboundOption.path("fareDetails").path("journeyFare").asDouble());
    	    option.setCurrency(outboundOption.path("fareDetails").path("fareCurrency").asText());
    	    
    	    JsonNode flightsNode = outboundOption.path("flights");
    	    Iterator<JsonNode> flightsIte = flightsNode.elements();
    	    flights = new ArrayList<Flight>();
    	    while(flightsIte.hasNext()){
    	    	flight = new Flight();
    	    	JsonNode flightNode = flightsIte.next();
    	    	flight.setFlightNumber(flightNode.path("flightNumber").asText());
    	    	flight.setAptDep(flightNode.path("departureAirport").path("name").asText());
    	    	flight.setAptArr(flightNode.path("arrivalAirport").path("name").asText());
    	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
    	    	flight.setFecDep(sdf.parse(flightNode.path("depScheduled").asText()));
    	    	flight.setFecArr(sdf.parse(flightNode.path("arrScheduled").asText()));
    	    	flights.add(flight);
    	    }
    	    option.setFlights(flights);
    	    options.add(option);
    	    
    	}
    	
    	return objectMapper.writeValueAsString(options);

    }
    
    public String getResponse(String type){
    	String response = "";
    	try{
    		response = new String(Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource("aero/"+type+".json").getFile())));
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return response;
    }
}
