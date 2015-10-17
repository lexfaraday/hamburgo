package com.hotelbeds.travel.api.profiles;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.hotelbeds.travel.api.profiles.domain.ProfileStruct;

@Component
@ConfigurationProperties(value = "app")
public class ApplicationProfile extends ProfileStruct {
    
	public ProfileStruct getActiveProfile() {
        return this;
    }
    
    String eventToken;
    String eventUrl;
	
    public String getEventToken() {
		return eventToken;
	}
	public void setEventToken(String eventToken) {
		this.eventToken = eventToken;
	}
	public String getEventUrl() {
		return eventUrl;
	}
	public void setEventUrl(String eventUrl) {
		this.eventUrl = eventUrl;
	}
}