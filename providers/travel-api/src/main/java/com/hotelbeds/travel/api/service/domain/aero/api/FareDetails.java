
package com.hotelbeds.travel.api.service.domain.aero.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    "perPassengerJourneyFares",
    "journeyFare",
    "totalTripFare",
    "fareCurrency"
})
public class FareDetails {

    @JsonProperty("perPassengerJourneyFares")
    private List<PerPassengerJourneyFare> perPassengerJourneyFares = new ArrayList<PerPassengerJourneyFare>();
    @JsonProperty("journeyFare")
    private String journeyFare;
    @JsonProperty("totalTripFare")
    private Object totalTripFare;
    @JsonProperty("fareCurrency")
    private String fareCurrency;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The perPassengerJourneyFares
     */
    @JsonProperty("perPassengerJourneyFares")
    public List<PerPassengerJourneyFare> getPerPassengerJourneyFares() {
        return perPassengerJourneyFares;
    }

    /**
     * 
     * @param perPassengerJourneyFares
     *     The perPassengerJourneyFares
     */
    @JsonProperty("perPassengerJourneyFares")
    public void setPerPassengerJourneyFares(List<PerPassengerJourneyFare> perPassengerJourneyFares) {
        this.perPassengerJourneyFares = perPassengerJourneyFares;
    }

    /**
     * 
     * @return
     *     The journeyFare
     */
    @JsonProperty("journeyFare")
    public String getJourneyFare() {
        return journeyFare;
    }

    /**
     * 
     * @param journeyFare
     *     The journeyFare
     */
    @JsonProperty("journeyFare")
    public void setJourneyFare(String journeyFare) {
        this.journeyFare = journeyFare;
    }

    /**
     * 
     * @return
     *     The totalTripFare
     */
    @JsonProperty("totalTripFare")
    public Object getTotalTripFare() {
        return totalTripFare;
    }

    /**
     * 
     * @param totalTripFare
     *     The totalTripFare
     */
    @JsonProperty("totalTripFare")
    public void setTotalTripFare(Object totalTripFare) {
        this.totalTripFare = totalTripFare;
    }

    /**
     * 
     * @return
     *     The fareCurrency
     */
    @JsonProperty("fareCurrency")
    public String getFareCurrency() {
        return fareCurrency;
    }

    /**
     * 
     * @param fareCurrency
     *     The fareCurrency
     */
    @JsonProperty("fareCurrency")
    public void setFareCurrency(String fareCurrency) {
        this.fareCurrency = fareCurrency;
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
