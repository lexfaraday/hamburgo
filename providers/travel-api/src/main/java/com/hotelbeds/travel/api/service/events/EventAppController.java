package com.hotelbeds.travel.api.service.events;

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

import com.google.gson.Gson;
import com.hotelbeds.travel.api.profiles.ApplicationProfile;
import com.hotelbeds.travel.api.websocket.MessageFlow;

@Controller
@RequestMapping(value = "/events")
public class EventAppController {

    @Autowired
    @Qualifier("applicationProfile")
    private ApplicationProfile appProfile;

    @Autowired
    private MessageFlow messageFlow;

    @Autowired
    private EventBriteProvider eventBriteProvider;

    @RequestMapping(value = "")
    public String renderIndex(Model uiModel) {
        return "index";
    }

    @RequestMapping(value = "/error")
    public String renderError(Model uiModel) {
        return "error";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseEntity<?> searchEvents() throws Exception {
        messageFlow.publish("Searching events..");
        return new ResponseEntity<String>(new Gson().toJson(eventBriteProvider.searchEvents()), HttpStatus.OK);
    }
}
