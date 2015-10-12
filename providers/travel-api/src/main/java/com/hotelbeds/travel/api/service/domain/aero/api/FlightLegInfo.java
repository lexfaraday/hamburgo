
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
    "flightStatus",
    "boardingDateTime",
    "dcsFlightStatus",
    "frequentFlyerNbr",
    "frequentFlyerProgram",
    "frequentFlyerLevel",
    "checkedInStatus",
    "resBookDesigCode",
    "cabinClassName",
    "apiRequired",
    "barcodeData",
    "flightSequenceNumber",
    "passengerSequenceNumber",
    "closed",
    "seatNumber",
    "checkedIn",
    "boarded",
    "minutesToScheduledFlightDeparture"
})
public class FlightLegInfo {

    @JsonProperty("flightNumber")
    private String flightNumber;
    @JsonProperty("departureAirport")
    private DepartureAirport departureAirport;
    @JsonProperty("arrivalAirport")
    private ArrivalAirport arrivalAirport;
    @JsonProperty("marketingAirline")
    private Object marketingAirline;
    @JsonProperty("operatingAirline")
    private String operatingAirline;
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
    private String depEstimated;
    @JsonProperty("depActual")
    private Object depActual;
    @JsonProperty("arrEstimated")
    private Object arrEstimated;
    @JsonProperty("arrActual")
    private Object arrActual;
    @JsonProperty("flightStatus")
    private Object flightStatus;
    @JsonProperty("boardingDateTime")
    private String boardingDateTime;
    @JsonProperty("dcsFlightStatus")
    private Integer dcsFlightStatus;
    @JsonProperty("frequentFlyerNbr")
    private Object frequentFlyerNbr;
    @JsonProperty("frequentFlyerProgram")
    private Object frequentFlyerProgram;
    @JsonProperty("frequentFlyerLevel")
    private Object frequentFlyerLevel;
    @JsonProperty("checkedInStatus")
    private Integer checkedInStatus;
    @JsonProperty("resBookDesigCode")
    private Object resBookDesigCode;
    @JsonProperty("cabinClassName")
    private Object cabinClassName;
    @JsonProperty("apiRequired")
    private Boolean apiRequired;
    @JsonProperty("barcodeData")
    private Object barcodeData;
    @JsonProperty("flightSequenceNumber")
    private Object flightSequenceNumber;
    @JsonProperty("passengerSequenceNumber")
    private Object passengerSequenceNumber;
    @JsonProperty("closed")
    private Boolean closed;
    @JsonProperty("seatNumber")
    private Object seatNumber;
    @JsonProperty("checkedIn")
    private Boolean checkedIn;
    @JsonProperty("boarded")
    private Boolean boarded;
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
    public Object getMarketingAirline() {
        return marketingAirline;
    }

    /**
     * 
     * @param marketingAirline
     *     The marketingAirline
     */
    @JsonProperty("marketingAirline")
    public void setMarketingAirline(Object marketingAirline) {
        this.marketingAirline = marketingAirline;
    }

    /**
     * 
     * @return
     *     The operatingAirline
     */
    @JsonProperty("operatingAirline")
    public String getOperatingAirline() {
        return operatingAirline;
    }

    /**
     * 
     * @param operatingAirline
     *     The operatingAirline
     */
    @JsonProperty("operatingAirline")
    public void setOperatingAirline(String operatingAirline) {
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
    public String getDepEstimated() {
        return depEstimated;
    }

    /**
     * 
     * @param depEstimated
     *     The depEstimated
     */
    @JsonProperty("depEstimated")
    public void setDepEstimated(String depEstimated) {
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
     *     The flightStatus
     */
    @JsonProperty("flightStatus")
    public Object getFlightStatus() {
        return flightStatus;
    }

    /**
     * 
     * @param flightStatus
     *     The flightStatus
     */
    @JsonProperty("flightStatus")
    public void setFlightStatus(Object flightStatus) {
        this.flightStatus = flightStatus;
    }

    /**
     * 
     * @return
     *     The boardingDateTime
     */
    @JsonProperty("boardingDateTime")
    public String getBoardingDateTime() {
        return boardingDateTime;
    }

    /**
     * 
     * @param boardingDateTime
     *     The boardingDateTime
     */
    @JsonProperty("boardingDateTime")
    public void setBoardingDateTime(String boardingDateTime) {
        this.boardingDateTime = boardingDateTime;
    }

    /**
     * 
     * @return
     *     The dcsFlightStatus
     */
    @JsonProperty("dcsFlightStatus")
    public Integer getDcsFlightStatus() {
        return dcsFlightStatus;
    }

    /**
     * 
     * @param dcsFlightStatus
     *     The dcsFlightStatus
     */
    @JsonProperty("dcsFlightStatus")
    public void setDcsFlightStatus(Integer dcsFlightStatus) {
        this.dcsFlightStatus = dcsFlightStatus;
    }

    /**
     * 
     * @return
     *     The frequentFlyerNbr
     */
    @JsonProperty("frequentFlyerNbr")
    public Object getFrequentFlyerNbr() {
        return frequentFlyerNbr;
    }

    /**
     * 
     * @param frequentFlyerNbr
     *     The frequentFlyerNbr
     */
    @JsonProperty("frequentFlyerNbr")
    public void setFrequentFlyerNbr(Object frequentFlyerNbr) {
        this.frequentFlyerNbr = frequentFlyerNbr;
    }

    /**
     * 
     * @return
     *     The frequentFlyerProgram
     */
    @JsonProperty("frequentFlyerProgram")
    public Object getFrequentFlyerProgram() {
        return frequentFlyerProgram;
    }

    /**
     * 
     * @param frequentFlyerProgram
     *     The frequentFlyerProgram
     */
    @JsonProperty("frequentFlyerProgram")
    public void setFrequentFlyerProgram(Object frequentFlyerProgram) {
        this.frequentFlyerProgram = frequentFlyerProgram;
    }

    /**
     * 
     * @return
     *     The frequentFlyerLevel
     */
    @JsonProperty("frequentFlyerLevel")
    public Object getFrequentFlyerLevel() {
        return frequentFlyerLevel;
    }

    /**
     * 
     * @param frequentFlyerLevel
     *     The frequentFlyerLevel
     */
    @JsonProperty("frequentFlyerLevel")
    public void setFrequentFlyerLevel(Object frequentFlyerLevel) {
        this.frequentFlyerLevel = frequentFlyerLevel;
    }

    /**
     * 
     * @return
     *     The checkedInStatus
     */
    @JsonProperty("checkedInStatus")
    public Integer getCheckedInStatus() {
        return checkedInStatus;
    }

    /**
     * 
     * @param checkedInStatus
     *     The checkedInStatus
     */
    @JsonProperty("checkedInStatus")
    public void setCheckedInStatus(Integer checkedInStatus) {
        this.checkedInStatus = checkedInStatus;
    }

    /**
     * 
     * @return
     *     The resBookDesigCode
     */
    @JsonProperty("resBookDesigCode")
    public Object getResBookDesigCode() {
        return resBookDesigCode;
    }

    /**
     * 
     * @param resBookDesigCode
     *     The resBookDesigCode
     */
    @JsonProperty("resBookDesigCode")
    public void setResBookDesigCode(Object resBookDesigCode) {
        this.resBookDesigCode = resBookDesigCode;
    }

    /**
     * 
     * @return
     *     The cabinClassName
     */
    @JsonProperty("cabinClassName")
    public Object getCabinClassName() {
        return cabinClassName;
    }

    /**
     * 
     * @param cabinClassName
     *     The cabinClassName
     */
    @JsonProperty("cabinClassName")
    public void setCabinClassName(Object cabinClassName) {
        this.cabinClassName = cabinClassName;
    }

    /**
     * 
     * @return
     *     The apiRequired
     */
    @JsonProperty("apiRequired")
    public Boolean getApiRequired() {
        return apiRequired;
    }

    /**
     * 
     * @param apiRequired
     *     The apiRequired
     */
    @JsonProperty("apiRequired")
    public void setApiRequired(Boolean apiRequired) {
        this.apiRequired = apiRequired;
    }

    /**
     * 
     * @return
     *     The barcodeData
     */
    @JsonProperty("barcodeData")
    public Object getBarcodeData() {
        return barcodeData;
    }

    /**
     * 
     * @param barcodeData
     *     The barcodeData
     */
    @JsonProperty("barcodeData")
    public void setBarcodeData(Object barcodeData) {
        this.barcodeData = barcodeData;
    }

    /**
     * 
     * @return
     *     The flightSequenceNumber
     */
    @JsonProperty("flightSequenceNumber")
    public Object getFlightSequenceNumber() {
        return flightSequenceNumber;
    }

    /**
     * 
     * @param flightSequenceNumber
     *     The flightSequenceNumber
     */
    @JsonProperty("flightSequenceNumber")
    public void setFlightSequenceNumber(Object flightSequenceNumber) {
        this.flightSequenceNumber = flightSequenceNumber;
    }

    /**
     * 
     * @return
     *     The passengerSequenceNumber
     */
    @JsonProperty("passengerSequenceNumber")
    public Object getPassengerSequenceNumber() {
        return passengerSequenceNumber;
    }

    /**
     * 
     * @param passengerSequenceNumber
     *     The passengerSequenceNumber
     */
    @JsonProperty("passengerSequenceNumber")
    public void setPassengerSequenceNumber(Object passengerSequenceNumber) {
        this.passengerSequenceNumber = passengerSequenceNumber;
    }

    /**
     * 
     * @return
     *     The closed
     */
    @JsonProperty("closed")
    public Boolean getClosed() {
        return closed;
    }

    /**
     * 
     * @param closed
     *     The closed
     */
    @JsonProperty("closed")
    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    /**
     * 
     * @return
     *     The seatNumber
     */
    @JsonProperty("seatNumber")
    public Object getSeatNumber() {
        return seatNumber;
    }

    /**
     * 
     * @param seatNumber
     *     The seatNumber
     */
    @JsonProperty("seatNumber")
    public void setSeatNumber(Object seatNumber) {
        this.seatNumber = seatNumber;
    }

    /**
     * 
     * @return
     *     The checkedIn
     */
    @JsonProperty("checkedIn")
    public Boolean getCheckedIn() {
        return checkedIn;
    }

    /**
     * 
     * @param checkedIn
     *     The checkedIn
     */
    @JsonProperty("checkedIn")
    public void setCheckedIn(Boolean checkedIn) {
        this.checkedIn = checkedIn;
    }

    /**
     * 
     * @return
     *     The boarded
     */
    @JsonProperty("boarded")
    public Boolean getBoarded() {
        return boarded;
    }

    /**
     * 
     * @param boarded
     *     The boarded
     */
    @JsonProperty("boarded")
    public void setBoarded(Boolean boarded) {
        this.boarded = boarded;
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
