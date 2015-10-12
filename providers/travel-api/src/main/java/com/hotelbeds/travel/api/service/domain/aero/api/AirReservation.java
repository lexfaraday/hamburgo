
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
    "bookingReferenceID",
    "originDestinationOptions",
    "passengers",
    "ticketing",
    "remarks",
    "SSRs",
    "OSIs"
})
public class AirReservation {

    @JsonProperty("bookingReferenceID")
    private String bookingReferenceID;
    @JsonProperty("originDestinationOptions")
    private List<OriginDestinationOption> originDestinationOptions = new ArrayList<OriginDestinationOption>();
    @JsonProperty("passengers")
    private List<Passenger> passengers = new ArrayList<Passenger>();
    @JsonProperty("ticketing")
    private Ticketing ticketing;
    @JsonProperty("remarks")
    private List<Object> remarks = new ArrayList<Object>();
    @JsonProperty("SSRs")
    private List<SSR> SSRs = new ArrayList<SSR>();
    @JsonProperty("OSIs")
    private List<OSI> OSIs = new ArrayList<OSI>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The bookingReferenceID
     */
    @JsonProperty("bookingReferenceID")
    public String getBookingReferenceID() {
        return bookingReferenceID;
    }

    /**
     * 
     * @param bookingReferenceID
     *     The bookingReferenceID
     */
    @JsonProperty("bookingReferenceID")
    public void setBookingReferenceID(String bookingReferenceID) {
        this.bookingReferenceID = bookingReferenceID;
    }

    /**
     * 
     * @return
     *     The originDestinationOptions
     */
    @JsonProperty("originDestinationOptions")
    public List<OriginDestinationOption> getOriginDestinationOptions() {
        return originDestinationOptions;
    }

    /**
     * 
     * @param originDestinationOptions
     *     The originDestinationOptions
     */
    @JsonProperty("originDestinationOptions")
    public void setOriginDestinationOptions(List<OriginDestinationOption> originDestinationOptions) {
        this.originDestinationOptions = originDestinationOptions;
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
     *     The ticketing
     */
    @JsonProperty("ticketing")
    public Ticketing getTicketing() {
        return ticketing;
    }

    /**
     * 
     * @param ticketing
     *     The ticketing
     */
    @JsonProperty("ticketing")
    public void setTicketing(Ticketing ticketing) {
        this.ticketing = ticketing;
    }

    /**
     * 
     * @return
     *     The remarks
     */
    @JsonProperty("remarks")
    public List<Object> getRemarks() {
        return remarks;
    }

    /**
     * 
     * @param remarks
     *     The remarks
     */
    @JsonProperty("remarks")
    public void setRemarks(List<Object> remarks) {
        this.remarks = remarks;
    }

    /**
     * 
     * @return
     *     The SSRs
     */
    @JsonProperty("SSRs")
    public List<SSR> getSSRs() {
        return SSRs;
    }

    /**
     * 
     * @param SSRs
     *     The SSRs
     */
    @JsonProperty("SSRs")
    public void setSSRs(List<SSR> SSRs) {
        this.SSRs = SSRs;
    }

    /**
     * 
     * @return
     *     The OSIs
     */
    @JsonProperty("OSIs")
    public List<OSI> getOSIs() {
        return OSIs;
    }

    /**
     * 
     * @param OSIs
     *     The OSIs
     */
    @JsonProperty("OSIs")
    public void setOSIs(List<OSI> OSIs) {
        this.OSIs = OSIs;
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
