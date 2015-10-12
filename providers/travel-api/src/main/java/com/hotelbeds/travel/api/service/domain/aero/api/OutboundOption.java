
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
    "optionId",
    "flights",
    "stopOvers",
    "fareDetails",
    "returnOptions",
    "magicString",
    "seatsAvailable",
    "corporateAccount",
    "flightCanBeHeld",
    "directFlight",
    "lowestSeatCount",
    "departureAirport",
    "arrivalAirport",
    "fareRules",
    "Errors",
    "Warnings"
})
public class OutboundOption {

    @JsonProperty("optionId")
    private Integer optionId;
    @JsonProperty("flights")
    private List<Flight> flights = new ArrayList<Flight>();
    @JsonProperty("stopOvers")
    private List<StopOver> stopOvers = new ArrayList<StopOver>();
    @JsonProperty("fareDetails")
    private FareDetails fareDetails;
    @JsonProperty("returnOptions")
    private List<ReturnOption> returnOptions = new ArrayList<ReturnOption>();
    @JsonProperty("magicString")
    private Object magicString;
    @JsonProperty("seatsAvailable")
    private List<Integer> seatsAvailable = new ArrayList<Integer>();
    @JsonProperty("corporateAccount")
    private Boolean corporateAccount;
    @JsonProperty("flightCanBeHeld")
    private Boolean flightCanBeHeld;
    @JsonProperty("directFlight")
    private Boolean directFlight;
    @JsonProperty("lowestSeatCount")
    private Integer lowestSeatCount;
    @JsonProperty("departureAirport")
    private DepartureAirport__ departureAirport;
    @JsonProperty("arrivalAirport")
    private ArrivalAirport__ arrivalAirport;
    @JsonProperty("fareRules")
    private List<FareRule> fareRules = new ArrayList<FareRule>();
    @JsonProperty("Errors")
    private List<Object> Errors = new ArrayList<Object>();
    @JsonProperty("Warnings")
    private List<Object> Warnings = new ArrayList<Object>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The optionId
     */
    @JsonProperty("optionId")
    public Integer getOptionId() {
        return optionId;
    }

    /**
     * 
     * @param optionId
     *     The optionId
     */
    @JsonProperty("optionId")
    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    /**
     * 
     * @return
     *     The flights
     */
    @JsonProperty("flights")
    public List<Flight> getFlights() {
        return flights;
    }

    /**
     * 
     * @param flights
     *     The flights
     */
    @JsonProperty("flights")
    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    /**
     * 
     * @return
     *     The stopOvers
     */
    @JsonProperty("stopOvers")
    public List<StopOver> getStopOvers() {
        return stopOvers;
    }

    /**
     * 
     * @param stopOvers
     *     The stopOvers
     */
    @JsonProperty("stopOvers")
    public void setStopOvers(List<StopOver> stopOvers) {
        this.stopOvers = stopOvers;
    }

    /**
     * 
     * @return
     *     The fareDetails
     */
    @JsonProperty("fareDetails")
    public FareDetails getFareDetails() {
        return fareDetails;
    }

    /**
     * 
     * @param fareDetails
     *     The fareDetails
     */
    @JsonProperty("fareDetails")
    public void setFareDetails(FareDetails fareDetails) {
        this.fareDetails = fareDetails;
    }

    /**
     * 
     * @return
     *     The returnOptions
     */
    @JsonProperty("returnOptions")
    public List<ReturnOption> getReturnOptions() {
        return returnOptions;
    }

    /**
     * 
     * @param returnOptions
     *     The returnOptions
     */
    @JsonProperty("returnOptions")
    public void setReturnOptions(List<ReturnOption> returnOptions) {
        this.returnOptions = returnOptions;
    }

    /**
     * 
     * @return
     *     The magicString
     */
    @JsonProperty("magicString")
    public Object getMagicString() {
        return magicString;
    }

    /**
     * 
     * @param magicString
     *     The magicString
     */
    @JsonProperty("magicString")
    public void setMagicString(Object magicString) {
        this.magicString = magicString;
    }

    /**
     * 
     * @return
     *     The seatsAvailable
     */
    @JsonProperty("seatsAvailable")
    public List<Integer> getSeatsAvailable() {
        return seatsAvailable;
    }

    /**
     * 
     * @param seatsAvailable
     *     The seatsAvailable
     */
    @JsonProperty("seatsAvailable")
    public void setSeatsAvailable(List<Integer> seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    /**
     * 
     * @return
     *     The corporateAccount
     */
    @JsonProperty("corporateAccount")
    public Boolean getCorporateAccount() {
        return corporateAccount;
    }

    /**
     * 
     * @param corporateAccount
     *     The corporateAccount
     */
    @JsonProperty("corporateAccount")
    public void setCorporateAccount(Boolean corporateAccount) {
        this.corporateAccount = corporateAccount;
    }

    /**
     * 
     * @return
     *     The flightCanBeHeld
     */
    @JsonProperty("flightCanBeHeld")
    public Boolean getFlightCanBeHeld() {
        return flightCanBeHeld;
    }

    /**
     * 
     * @param flightCanBeHeld
     *     The flightCanBeHeld
     */
    @JsonProperty("flightCanBeHeld")
    public void setFlightCanBeHeld(Boolean flightCanBeHeld) {
        this.flightCanBeHeld = flightCanBeHeld;
    }

    /**
     * 
     * @return
     *     The directFlight
     */
    @JsonProperty("directFlight")
    public Boolean getDirectFlight() {
        return directFlight;
    }

    /**
     * 
     * @param directFlight
     *     The directFlight
     */
    @JsonProperty("directFlight")
    public void setDirectFlight(Boolean directFlight) {
        this.directFlight = directFlight;
    }

    /**
     * 
     * @return
     *     The lowestSeatCount
     */
    @JsonProperty("lowestSeatCount")
    public Integer getLowestSeatCount() {
        return lowestSeatCount;
    }

    /**
     * 
     * @param lowestSeatCount
     *     The lowestSeatCount
     */
    @JsonProperty("lowestSeatCount")
    public void setLowestSeatCount(Integer lowestSeatCount) {
        this.lowestSeatCount = lowestSeatCount;
    }

    /**
     * 
     * @return
     *     The departureAirport
     */
    @JsonProperty("departureAirport")
    public DepartureAirport__ getDepartureAirport() {
        return departureAirport;
    }

    /**
     * 
     * @param departureAirport
     *     The departureAirport
     */
    @JsonProperty("departureAirport")
    public void setDepartureAirport(DepartureAirport__ departureAirport) {
        this.departureAirport = departureAirport;
    }

    /**
     * 
     * @return
     *     The arrivalAirport
     */
    @JsonProperty("arrivalAirport")
    public ArrivalAirport__ getArrivalAirport() {
        return arrivalAirport;
    }

    /**
     * 
     * @param arrivalAirport
     *     The arrivalAirport
     */
    @JsonProperty("arrivalAirport")
    public void setArrivalAirport(ArrivalAirport__ arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    /**
     * 
     * @return
     *     The fareRules
     */
    @JsonProperty("fareRules")
    public List<FareRule> getFareRules() {
        return fareRules;
    }

    /**
     * 
     * @param fareRules
     *     The fareRules
     */
    @JsonProperty("fareRules")
    public void setFareRules(List<FareRule> fareRules) {
        this.fareRules = fareRules;
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
    public List<Object> getWarnings() {
        return Warnings;
    }

    /**
     * 
     * @param Warnings
     *     The Warnings
     */
    @JsonProperty("Warnings")
    public void setWarnings(List<Object> Warnings) {
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
