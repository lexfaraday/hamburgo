package com.hotelbeds.travel.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Service;

@Service
public class EventBriteProvider {

    private static final String OAUTH_TOKEN = "";

    @Autowired
    private OAuth2RestOperations restTemplate;

    //    @Autowired
    //    private RestTemplate restTemplate;

    private HttpEntity<String> prepareHttpEntity() {
        //        HttpHeaders httpHeaders = new HttpHeaders();
        //        httpHeaders.add(headerName, headerValue);
        //        return new HttpEntity<String>("", httpHeaders);
        return null;
    }

    public void searchEvents() {
        // System.out.println(restTemplate.exchange(prepareHttpEntity(), String.class));
        System.out.println(restTemplate.getAccessToken());
    }
}
