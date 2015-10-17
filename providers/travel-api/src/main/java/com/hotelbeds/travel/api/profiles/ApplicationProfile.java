package com.hotelbeds.travel.api.profiles;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.hotelbeds.travel.api.profiles.domain.ProfileStruct;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(value = "app")
public class ApplicationProfile extends ProfileStruct {
    
	public ProfileStruct getActiveProfile() {
        return this;
    }
    
    String eventToken;
    String eventUrl;
    String sitaKey;
    String iataUrl;
    String iataKey;
	
}