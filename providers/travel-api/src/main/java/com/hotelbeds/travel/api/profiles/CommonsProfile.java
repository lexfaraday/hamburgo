package com.hotelbeds.travel.api.profiles;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(value = "commons")
public class CommonsProfile {

}