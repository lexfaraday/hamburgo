
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
    "flightNumber",
    "departureAirport",
    "arrivalAirport",
    "marketingAirline",
    "operatingAirline",
    "equipment",
    "flightRPH",
    "comments",
    "depScheduled",
    "arrScheduled",
    "depEstimated",
    "depActual",
    "arrEstimated",
    "arrActual",
    "cabin",
    "fareMarketingType",
    "rbd",
    "seatsAvailable",
    "minutesToScheduledFlightDeparture"
})
public class Flight {

    @JsonProperty("flightNumber")
    private String flightNumber;
    @JsonProperty("departureAirport")
    private DepartureAirport departureAirport;
    @JsonProperty("arrivalAirport")
    private ArrivalAirport arrivalAirport;
    @JsonProperty("marketingAirline")
    private String marketingAirline;
    @JsonProperty("operatingAirline")
    private Object operatingAirline;
    @JsonProperty("equipment")
    private String equipment;
    @JsonProperty("flightRPH")
    private Integer flightRPH;
    @JsonProperty("comments")
    private List<Object> comments = new ArrayList<Object>();
    @JsonProperty("depScheduled")
    private String depScheduled;
    @JsonProperty("arrScheduled")
    private String arrScheduled;
    @JsonProperty("depEstimated")
    private Object depEstimated;
    @JsonProperty("depActual")
    private Object depActual;
    @JsonProperty("arrEstimated")
    private Object arrEstimated;
    @JsonProperty("arrActual")
    private Object arrActual;
    @JsonProperty("cabin")
    private String cabin;
    @JsonProperty("fareMarketingType")
    private String fareMarketingType;
    @JsonProperty("rbd")
    private String rbd;
    @JsonProperty("seatsAvailable")
    private Integer seatsAvailable;
    @JsonProperty("minutesToScheduledFlightDeparture")
    private Integer minutesToScheduledFlightDeparture;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The flightNumber
     */
    @JsonProperty("flightNumber")
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * 
     * @param flightNumber
     *     The flightNumber
     */
    @JsonProperty("flightNumber")
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    /**
     * 
     * @return
     *     The departureAirport
     */
    @JsonProperty("departureAirport")
    public DepartureAirport getDepartureAirport() {
        return departureAirport;
    }

    /**
     * 
     * @param departureAirport
     *     The departureAirport
     */
    @JsonProperty("departureAirport")
    public void setDepartureAirport(DepartureAirport departureAirport) {
        this.departureAirport = departureAirport;
    }

    /**
     * 
     * @return
     *     The arrivalAirport
     */
    @JsonProperty("arrivalAirport")
    public ArrivalAirport getArrivalAirport() {
        return arrivalAirport;
    }

    /**
     * 
     * @param arrivalAirport
     *     The arrivalAirport
     */
    @JsonProperty("arrivalAirport")
    public void setArrivalAirport(ArrivalAirport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    /**
     * 
     * @return
     *     The marketingAirline
     */
    @JsonProperty("marketingAirline")
    public String getMarketingAirline() {
        return marketingAirline;
    }

    /**
     * 
     * @param marketingAirline
     *     The marketingAirline
     */
    @JsonProperty("marketingAirline")
    public void setMarketingAirline(String marketingAirline) {
        this.marketingAirline = marketingAirline;
    }

    /**
     * 
     * @return
     *     The operatingAirline
     */
    @JsonProperty("operatingAirline")
    public Object getOperatingAirline() {
        return operatingAirline;
    }

    /**
     * 
     * @param operatingAirline
     *     The operatingAirline
     */
    @JsonProperty("operatingAirline")
    public void setOperatingAirline(Object operatingAirline) {
        this.operatingAirline = operatingAirline;
    }

    /**
     * 
     * @return
     *     The equipment
     */
    @JsonProperty("equipment")
    public String getEquipment() {
        return equipment;
    }

    /**
     * 
     * @param equipment
     *     The equipment
     */
    @JsonProperty("equipment")
    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    /**
     * 
     * @return
     *     The flightRPH
     */
    @JsonProperty("flightRPH")
    public Integer getFlightRPH() {
        return flightRPH;
    }

    /**
     * 
     * @param flightRPH
     *     The flightRPH
     */
    @JsonProperty("flightRPH")
    public void setFlightRPH(Integer flightRPH) {
        this.flightRPH = flightRPH;
    }

    /**
     * 
     * @return
     *     The comments
     */
    @JsonProperty("comments")
    public List<Object> getComments() {
        return comments;
    }

    /**
     * 
     * @param comments
     *     The comments
     */
    @JsonProperty("comments")
    public void setComments(List<Object> comments) {
        this.comments = comments;
    }

    /**
     * 
     * @return
     *     The depScheduled
     */
    @JsonProperty("depScheduled")
    public String getDepScheduled() {
        return depScheduled;
    }

    /**
     * 
     * @param depScheduled
     *     The depScheduled
     */
    @JsonProperty("depScheduled")
    public void setDepScheduled(String depScheduled) {
        this.depScheduled = depScheduled;
    }

    /**
     * 
     * @return
     *     The arrScheduled
     */
    @JsonProperty("arrScheduled")
    public String getArrScheduled() {
        return arrScheduled;
    }

    /**
     * 
     * @param arrScheduled
     *     The arrScheduled
     */
    @JsonProperty("arrScheduled")
    public void setArrScheduled(String arrScheduled) {
        this.arrScheduled = arrScheduled;
    }

    /**
     * 
     * @return
     *     The depEstimated
     */
    @JsonProperty("depEstimated")
    public Object getDepEstimated() {
        return depEstimated;
    }

    /**
     * 
     * @param depEstimated
     *     The depEstimated
     */
    @JsonProperty("depEstimated")
    public void setDepEstimated(Object depEstimated) {
        this.depEstimated = depEstimated;
    }

    /**
     * 
     * @return
     *     The depActual
     */
    @JsonProperty("depActual")
    public Object getDepActual() {
        return depActual;
    }

    /**
     * 
     * @param depActual
     *     The depActual
     */
    @JsonProperty("depActual")
    public void setDepActual(Object depActual) {
        this.depActual = depActual;
    }

    /**
     * 
     * @return
     *     The arrEstimated
     */
    @JsonProperty("arrEstimated")
    public Object getArrEstimated() {
        return arrEstimated;
    }

    /**
     * 
     * @param arrEstimated
     *     The arrEstimated
     */
    @JsonProperty("arrEstimated")
    public void setArrEstimated(Object arrEstimated) {
        this.arrEstimated = arrEstimated;
    }

    /**
     * 
     * @return
     *     The arrActual
     */
    @JsonProperty("arrActual")
    public Object getArrActual() {
        return arrActual;
    }

    /**
     * 
     * @param arrActual
     *     The arrActual
     */
    @JsonProperty("arrActual")
    public void setArrActual(Object arrActual) {
        this.arrActual = arrActual;
    }

    /**
     * 
     * @return
     *     The cabin
     */
    @JsonProperty("cabin")
    public String getCabin() {
        return cabin;
    }

    /**
     * 
     * @param cabin
     *     The cabin
     */
    @JsonProperty("cabin")
    public void setCabin(String cabin) {
        this.cabin = cabin;
    }

    /**
     * 
     * @return
     *     The fareMarketingType
     */
    @JsonProperty("fareMarketingType")
    public String getFareMarketingType() {
        return fareMarketingType;
    }

    /**
     * 
     * @param fareMarketingType
     *     The fareMarketingType
     */
    @JsonProperty("fareMarketingType")
    public void setFareMarketingType(String fareMarketingType) {
        this.fareMarketingType = fareMarketingType;
    }

    /**
     * 
     * @return
     *     The rbd
     */
    @JsonProperty("rbd")
    public String getRbd() {
        return rbd;
    }

    /**
     * 
     * @param rbd
     *     The rbd
     */
    @JsonProperty("rbd")
    public void setRbd(String rbd) {
        this.rbd = rbd;
    }

    /**
     * 
     * @return
     *     The seatsAvailable
     */
    @JsonProperty("seatsAvailable")
    public Integer getSeatsAvailable() {
        return seatsAvailable;
    }

    /**
     * 
     * @param seatsAvailable
     *     The seatsAvailable
     */
    @JsonProperty("seatsAvailable")
    public void setSeatsAvailable(Integer seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    /**
     * 
     * @return
     *     The minutesToScheduledFlightDeparture
     */
    @JsonProperty("minutesToScheduledFlightDeparture")
    public Integer getMinutesToScheduledFlightDeparture() {
        return minutesToScheduledFlightDeparture;
    }

    /**
     * 
     * @param minutesToScheduledFlightDeparture
     *     The minutesToScheduledFlightDeparture
     */
    @JsonProperty("minutesToScheduledFlightDeparture")
    public void setMinutesToScheduledFlightDeparture(Integer minutesToScheduledFlightDeparture) {
        this.minutesToScheduledFlightDeparture = minutesToScheduledFlightDeparture;
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
