package com.hotelbeds.travel.api.profiles.domain;

import org.springframework.beans.factory.annotation.Autowired;

import com.hotelbeds.travel.api.profiles.CommonsProfile;

/**
 * Application custom properties
 * 
 * @author Alej4ndro G0m3z.
 *
 */
public class ApplicationStruct {

    @Autowired
    private CommonsProfile commonsProfile;

    public CommonsProfile getCommonsProfile() {
        return commonsProfile;
    }

    public void setCommonsProfile(CommonsProfile commonsProfile) {
        this.commonsProfile = commonsProfile;
    }

}
