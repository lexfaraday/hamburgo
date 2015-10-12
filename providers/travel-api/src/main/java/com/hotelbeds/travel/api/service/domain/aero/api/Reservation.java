
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
    "passengers",
    "magicString",
    "creditCardPaymentInfo"
})
public class Reservation {

    @JsonProperty("passengers")
    private List<Passenger> passengers = new ArrayList<Passenger>();
    @JsonProperty("magicString")
    private String magicString;
    @JsonProperty("creditCardPaymentInfo")
    private CreditCardPaymentInfo creditCardPaymentInfo;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The passengers
     */
    @JsonProperty("passengers")
    public List<Passenger> getPassengers() {
        return passengers;
    }

    /**
     * 
     * @param passengers
     *     The passengers
     */
    @JsonProperty("passengers")
    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    /**
     * 
     * @return
     *     The magicString
     */
    @JsonProperty("magicString")
    public String getMagicString() {
        return magicString;
    }

    /**
     * 
     * @param magicString
     *     The magicString
     */
    @JsonProperty("magicString")
    public void setMagicString(String magicString) {
        this.magicString = magicString;
    }

    /**
     * 
     * @return
     *     The creditCardPaymentInfo
     */
    @JsonProperty("creditCardPaymentInfo")
    public CreditCardPaymentInfo getCreditCardPaymentInfo() {
        return creditCardPaymentInfo;
    }

    /**
     * 
     * @param creditCardPaymentInfo
     *     The creditCardPaymentInfo
     */
    @JsonProperty("creditCardPaymentInfo")
    public void setCreditCardPaymentInfo(CreditCardPaymentInfo creditCardPaymentInfo) {
        this.creditCardPaymentInfo = creditCardPaymentInfo;
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
