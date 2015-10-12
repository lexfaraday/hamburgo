
package com.hotelbeds.travel.api.service.domain.aero.api;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "airport",
    "duration",
    "durationAsString"
})
public class StopOver_ {

    @JsonProperty("airport")
    private Airport_ airport;
    @JsonProperty("duration")
    private String duration;
    @JsonProperty("durationAsString")
    private String durationAsString;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The airport
     */
    @JsonProperty("airport")
    public Airport_ getAirport() {
        return airport;
    }

    /**
     * 
     * @param airport
     *     The airport
     */
    @JsonProperty("airport")
    public void setAirport(Airport_ airport) {
        this.airport = airport;
    }

    /**
     * 
     * @return
     *     The duration
     */
    @JsonProperty("duration")
    public String getDuration() {
        return duration;
    }

    /**
     * 
     * @param duration
     *     The duration
     */
    @JsonProperty("duration")
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * 
     * @return
     *     The durationAsString
     */
    @JsonProperty("durationAsString")
    public String getDurationAsString() {
        return durationAsString;
    }

    /**
     * 
     * @param durationAsString
     *     The durationAsString
     */
    @JsonProperty("durationAsString")
    public void setDurationAsString(String durationAsString) {
        this.durationAsString = durationAsString;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
