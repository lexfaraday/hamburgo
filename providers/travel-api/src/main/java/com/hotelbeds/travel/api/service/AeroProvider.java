package com.hotelbeds.travel.api.service;


import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotelbeds.travel.api.service.domain.aero.AeroBookRS;
import com.hotelbeds.travel.api.service.domain.aero.AeroSearchRQ;
import com.hotelbeds.travel.api.service.domain.aero.AeroSearchRS;
import com.hotelbeds.travel.api.service.domain.aero.api.CheckinRS;
import com.hotelbeds.travel.api.service.domain.aero.api.CreditCardPaymentInfo;
import com.hotelbeds.travel.api.service.domain.aero.api.Flight;
import com.hotelbeds.travel.api.service.domain.aero.api.OutboundOption;
import com.hotelbeds.travel.api.service.domain.aero.api.Passenger;
import com.hotelbeds.travel.api.service.domain.aero.api.Reservation;
import com.hotelbeds.travel.api.service.domain.aero.api.ReservationRS;
import com.hotelbeds.travel.api.service.domain.aero.api.Shop;



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
    
    public AeroBookRS bookFlight(String magicKey) throws JsonProcessingException, IOException{
    	
    	ObjectMapper mapper = new ObjectMapper();
    	
    	//Dummy Reservation Data
    	Reservation reservation = getDummyReservationData();
    	
    	String jsonRQ = mapper.writeValueAsString(reservation);
    	
    	AeroBookRS booking = new AeroBookRS();

    	ReservationRS reservationRS = mapper.readValue(callApi("reservation",jsonRQ,null), ReservationRS.class);
    	booking.setRef(reservationRS.getAirReservation().getBookingReferenceID());
    	return booking;
    }

	public Reservation getDummyReservationData() {
		Reservation reservation = new Reservation();
    	reservation.setMagicString("XXX");
    	
    	CreditCardPaymentInfo card = new CreditCardPaymentInfo();
    	card.setCardCode("VI");
    	card.setCardHolderName("Solomeo Paredes");
    	card.setCardNumber("4444333322221111");
    	card.setCardType(1);
    	card.setCcv("585");
    	card.setExpireDateMMyyyy("25082010");
    	reservation.setCreditCardPaymentInfo(card);
    	
    	List<Passenger> lstPas = new ArrayList<Passenger>();
    	Passenger pas = new Passenger();
    	pas.setEmail("test@test.es");
    	lstPas.add(pas);
    	reservation.setPassengers(lstPas);
		return reservation;
	}

    public List<AeroSearchRS> searchFlights(AeroSearchRQ rq) throws JsonProcessingException, IOException, ParseException {
    	    	
    	ObjectMapper mapper = new ObjectMapper();
    	List<AeroSearchRS> rs = new ArrayList<AeroSearchRS>();
    	List<com.hotelbeds.travel.api.service.domain.aero.Flight> flights;
    	AeroSearchRS option = new AeroSearchRS();
    	
    	Shop shop = mapper.readValue(callApi("shop",null,null), Shop.class);

    	for(OutboundOption outOption : shop.getOutboundOptions()){
    		
    		option = new AeroSearchRS();
    		option.setAmount(new BigDecimal(outOption.getFareDetails().getJourneyFare()));
    		option.setCurrency(outOption.getFareDetails().getFareCurrency());
    		option.setMagicString(outOption.getMagicString().toString()); //TODO WTF
    		flights = new ArrayList<com.hotelbeds.travel.api.service.domain.aero.Flight>();
    		com.hotelbeds.travel.api.service.domain.aero.Flight flight;
    		
    		for(Flight flight2 : outOption.getFlights()){
    			
    			flight = new com.hotelbeds.travel.api.service.domain.aero.Flight();
    			flight.setFlightNumber(flight2.getFlightNumber());
    			flight.setAptDep(flight2.getDepartureAirport().getName());
    			flight.setAptArr(flight2.getArrivalAirport().getName());
    			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
    			flight.setFecDep(sdf.parse(flight2.getDepScheduled()));
    			flight.setFecArr(sdf.parse(flight2.getArrScheduled()));
    			flights.add(flight);
    			
    		}
    		
    		option.setFlights(flights);
    	}
    	
    	
    	return rs;

    }
    
    public String checkin() throws JsonParseException, JsonMappingException, IOException{
    	
    	ObjectMapper mapper = new ObjectMapper();
    	
    	CheckinRS checkin = mapper.readValue(callApi("checkin",null,null), CheckinRS.class);
    	
    	checkin.getSuccess();
    	
    	return mapper.writeValueAsString(checkin); 
    }
    
    public String callApi(String path, String rq, Map<String,String> param){
    	
    	String response = "";
    	try{
    		response = new String(Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource("aero/"+path+".json").getFile())));
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return response;
    }
}
