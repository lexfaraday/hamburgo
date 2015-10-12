
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
    "passengerType",
    "fare",
    "currencyCode"
})
public class PerPassengerJourneyFare_ {

    @JsonProperty("passengerType")
    private String passengerType;
    @JsonProperty("fare")
    private String fare;
    @JsonProperty("currencyCode")
    private String currencyCode;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The passengerType
     */
    @JsonProperty("passengerType")
    public String getPassengerType() {
        return passengerType;
    }

    /**
     * 
     * @param passengerType
     *     The passengerType
     */
    @JsonProperty("passengerType")
    public void setPassengerType(String passengerType) {
        this.passengerType = passengerType;
    }

    /**
     * 
     * @return
     *     The fare
     */
    @JsonProperty("fare")
    public String getFare() {
        return fare;
    }

    /**
     * 
     * @param fare
     *     The fare
     */
    @JsonProperty("fare")
    public void setFare(String fare) {
        this.fare = fare;
    }

    /**
     * 
     * @return
     *     The currencyCode
     */
    @JsonProperty("currencyCode")
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * 
     * @param currencyCode
     *     The currencyCode
     */
    @JsonProperty("currencyCode")
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
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
