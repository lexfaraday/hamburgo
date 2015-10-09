package com.hotelbeds.travel.api.profiles;

import org.springframework.stereotype.Component;

import com.hotelbeds.travel.api.profiles.domain.ProfileStruct;

@Component
public class ApplicationProfile extends ProfileStruct {
    public ProfileStruct getActiveProfile() {
        return this;
    }
}