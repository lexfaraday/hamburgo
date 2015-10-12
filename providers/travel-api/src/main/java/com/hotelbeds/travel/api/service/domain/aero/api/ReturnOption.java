
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
    "magicString",
    "seatsAvailable",
    "corporateAccount",
    "flightCanBeHeld",
    "directFlight",
    "lowestSeatCount"
})
public class ReturnOption {

    @JsonProperty("optionId")
    private Integer optionId;
    @JsonProperty("flights")
    private List<Flight_> flights = new ArrayList<Flight_>();
    @JsonProperty("stopOvers")
    private List<StopOver_> stopOvers = new ArrayList<StopOver_>();
    @JsonProperty("fareDetails")
    private FareDetails_ fareDetails;
    @JsonProperty("magicString")
    private String magicString;
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
    public List<Flight_> getFlights() {
        return flights;
    }

    /**
     * 
     * @param flights
     *     The flights
     */
    @JsonProperty("flights")
    public void setFlights(List<Flight_> flights) {
        this.flights = flights;
    }

    /**
     * 
     * @return
     *     The stopOvers
     */
    @JsonProperty("stopOvers")
    public List<StopOver_> getStopOvers() {
        return stopOvers;
    }

    /**
     * 
     * @param stopOvers
     *     The stopOvers
     */
    @JsonProperty("stopOvers")
    public void setStopOvers(List<StopOver_> stopOvers) {
        this.stopOvers = stopOvers;
    }

    /**
     * 
     * @return
     *     The fareDetails
     */
    @JsonProperty("fareDetails")
    public FareDetails_ getFareDetails() {
        return fareDetails;
    }

    /**
     * 
     * @param fareDetails
     *     The fareDetails
     */
    @JsonProperty("fareDetails")
    public void setFareDetails(FareDetails_ fareDetails) {
        this.fareDetails = fareDetails;
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

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
