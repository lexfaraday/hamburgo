
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
    "ticketType",
    "ticketingStatus",
    "travelerRefNumber",
    "ticketTimeLimit"
})
public class Ticketing {

    @JsonProperty("ticketType")
    private String ticketType;
    @JsonProperty("ticketingStatus")
    private Integer ticketingStatus;
    @JsonProperty("travelerRefNumber")
    private String travelerRefNumber;
    @JsonProperty("ticketTimeLimit")
    private Object ticketTimeLimit;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The ticketType
     */
    @JsonProperty("ticketType")
    public String getTicketType() {
        return ticketType;
    }

    /**
     * 
     * @param ticketType
     *     The ticketType
     */
    @JsonProperty("ticketType")
    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    /**
     * 
     * @return
     *     The ticketingStatus
     */
    @JsonProperty("ticketingStatus")
    public Integer getTicketingStatus() {
        return ticketingStatus;
    }

    /**
     * 
     * @param ticketingStatus
     *     The ticketingStatus
     */
    @JsonProperty("ticketingStatus")
    public void setTicketingStatus(Integer ticketingStatus) {
        this.ticketingStatus = ticketingStatus;
    }

    /**
     * 
     * @return
     *     The travelerRefNumber
     */
    @JsonProperty("travelerRefNumber")
    public String getTravelerRefNumber() {
        return travelerRefNumber;
    }

    /**
     * 
     * @param travelerRefNumber
     *     The travelerRefNumber
     */
    @JsonProperty("travelerRefNumber")
    public void setTravelerRefNumber(String travelerRefNumber) {
        this.travelerRefNumber = travelerRefNumber;
    }

    /**
     * 
     * @return
     *     The ticketTimeLimit
     */
    @JsonProperty("ticketTimeLimit")
    public Object getTicketTimeLimit() {
        return ticketTimeLimit;
    }

    /**
     * 
     * @param ticketTimeLimit
     *     The ticketTimeLimit
     */
    @JsonProperty("ticketTimeLimit")
    public void setTicketTimeLimit(Object ticketTimeLimit) {
        this.ticketTimeLimit = ticketTimeLimit;
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
