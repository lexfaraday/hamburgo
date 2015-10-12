
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
    "processingDurationMillis",
    "authorisedAPI",
    "success",
    "airline",
    "flightLegInfos",
    "passengers",
    "Errors",
    "Warnings"
})
public class CheckinRS {

    @JsonProperty("processingDurationMillis")
    private Integer processingDurationMillis;
    @JsonProperty("authorisedAPI")
    private Boolean authorisedAPI;
    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("airline")
    private String airline;
    @JsonProperty("flightLegInfos")
    private List<FlightLegInfo> flightLegInfos = new ArrayList<FlightLegInfo>();
    @JsonProperty("passengers")
    private List<Passenger> passengers = new ArrayList<Passenger>();
    @JsonProperty("Errors")
    private List<Object> Errors = new ArrayList<Object>();
    @JsonProperty("Warnings")
    private List<Warning> Warnings = new ArrayList<Warning>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The processingDurationMillis
     */
    @JsonProperty("processingDurationMillis")
    public Integer getProcessingDurationMillis() {
        return processingDurationMillis;
    }

    /**
     * 
     * @param processingDurationMillis
     *     The processingDurationMillis
     */
    @JsonProperty("processingDurationMillis")
    public void setProcessingDurationMillis(Integer processingDurationMillis) {
        this.processingDurationMillis = processingDurationMillis;
    }

    /**
     * 
     * @return
     *     The authorisedAPI
     */
    @JsonProperty("authorisedAPI")
    public Boolean getAuthorisedAPI() {
        return authorisedAPI;
    }

    /**
     * 
     * @param authorisedAPI
     *     The authorisedAPI
     */
    @JsonProperty("authorisedAPI")
    public void setAuthorisedAPI(Boolean authorisedAPI) {
        this.authorisedAPI = authorisedAPI;
    }

    /**
     * 
     * @return
     *     The success
     */
    @JsonProperty("success")
    public Boolean getSuccess() {
        return success;
    }

    /**
     * 
     * @param success
     *     The success
     */
    @JsonProperty("success")
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     * 
     * @return
     *     The airline
     */
    @JsonProperty("airline")
    public String getAirline() {
        return airline;
    }

    /**
     * 
     * @param airline
     *     The airline
     */
    @JsonProperty("airline")
    public void setAirline(String airline) {
        this.airline = airline;
    }

    /**
     * 
     * @return
     *     The flightLegInfos
     */
    @JsonProperty("flightLegInfos")
    public List<FlightLegInfo> getFlightLegInfos() {
        return flightLegInfos;
    }

    /**
     * 
     * @param flightLegInfos
     *     The flightLegInfos
     */
    @JsonProperty("flightLegInfos")
    public void setFlightLegInfos(List<FlightLegInfo> flightLegInfos) {
        this.flightLegInfos = flightLegInfos;
    }

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
     *     The Errors
     */
    @JsonProperty("Errors")
    public List<Object> getErrors() {
        return Errors;
    }

    /**
     * 
     * @param Errors
     *     The Errors
     */
    @JsonProperty("Errors")
    public void setErrors(List<Object> Errors) {
        this.Errors = Errors;
    }

    /**
     * 
     * @return
     *     The Warnings
     */
    @JsonProperty("Warnings")
    public List<Warning> getWarnings() {
        return Warnings;
    }

    /**
     * 
     * @param Warnings
     *     The Warnings
     */
    @JsonProperty("Warnings")
    public void setWarnings(List<Warning> Warnings) {
        this.Warnings = Warnings;
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
