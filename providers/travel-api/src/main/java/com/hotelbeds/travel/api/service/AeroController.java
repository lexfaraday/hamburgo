package com.hotelbeds.travel.api.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hotelbeds.travel.api.profiles.ApplicationProfile;
import com.hotelbeds.travel.api.websocket.MessageFlow;

@Controller
@RequestMapping(value = "/aero-app")
public class AeroController {

    @Autowired
    @Qualifier("applicationProfile")
    private ApplicationProfile appProfile;

    @Autowired
    private MessageFlow messageFlow;

    @Autowired
    private IATAProvider iataProvider;
    
    @Autowired
    private SITAProvider sitaProvider;

    @RequestMapping(value = "")
    public String renderIndex(Model uiModel) {
        return "index";
    }

    @RequestMapping(value = "/error")
    public String renderError(Model uiModel) {
        return "error";
    }

    @RequestMapping(value = "/flights/search", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseEntity<?> searchFlights() throws JsonProcessingException, IOException, ParseException {
        messageFlow.publish("Searching flights..");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-M hh:mm:ss");
        iataProvider.searchFlights(formatter.parse("2015-20-11 16:00:00") , formatter.parse("2015-25-11 20:00:00"), "CDG", "LHR");
        return new ResponseEntity<String>("hh", HttpStatus.OK);
    }
    
    @RequestMapping(value = "/flights/searchAirports", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseEntity<?> searchAirPorts() throws JsonProcessingException, IOException, ParseException, JAXBException, SAXException, ParserConfigurationException {
        messageFlow.publish("Searching flights..");
        sitaProvider.findAirportsByGeoPos("52.297097", "4.879903", "10");
        return new ResponseEntity<String>("hh", HttpStatus.OK);
    }
    /*
    @RequestMapping(value = "/flights/book", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseEntity<?> bookFlight() throws JsonProcessingException, IOException {
        messageFlow.publish("Booking flights..");
        return new ResponseEntity<String>(aeroProvider.bookFlight("magicString").toString(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/flights/checkin", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseEntity<?> checkinFlight() throws JsonProcessingException, IOException {
        messageFlow.publish("Checkin flights..");
        return new ResponseEntity<String>(aeroProvider.checkin(), HttpStatus.OK);
    }
    */
}
