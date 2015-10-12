package com.hotelbeds.travel.api.service;

import java.io.IOException;
import java.text.ParseException;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hotelbeds.travel.api.profiles.ApplicationProfile;
import com.hotelbeds.travel.api.service.domain.aero.AeroSearchRQ;
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
    private AeroProvider aeroProvider;

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
        return new ResponseEntity<String>(aeroProvider.searchFlights(new AeroSearchRQ()).toString(), HttpStatus.OK);
    }
    
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
}
