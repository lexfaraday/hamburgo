
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
    "travlerRefNbr",
    "airlineCode",
    "serviceQuantity",
    "status",
    "request",
    "flightRefNbr",
    "ssrcode"
})
public class SSR {

    @JsonProperty("travlerRefNbr")
    private String travlerRefNbr;
    @JsonProperty("airlineCode")
    private String airlineCode;
    @JsonProperty("serviceQuantity")
    private Integer serviceQuantity;
    @JsonProperty("status")
    private String status;
    @JsonProperty("request")
    private String request;
    @JsonProperty("flightRefNbr")
    private String flightRefNbr;
    @JsonProperty("ssrcode")
    private String ssrcode;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The travlerRefNbr
     */
    @JsonProperty("travlerRefNbr")
    public String getTravlerRefNbr() {
        return travlerRefNbr;
    }

    /**
     * 
     * @param travlerRefNbr
     *     The travlerRefNbr
     */
    @JsonProperty("travlerRefNbr")
    public void setTravlerRefNbr(String travlerRefNbr) {
        this.travlerRefNbr = travlerRefNbr;
    }

    /**
     * 
     * @return
     *     The airlineCode
     */
    @JsonProperty("airlineCode")
    public String getAirlineCode() {
        return airlineCode;
    }

    /**
     * 
     * @param airlineCode
     *     The airlineCode
     */
    @JsonProperty("airlineCode")
    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    /**
     * 
     * @return
     *     The serviceQuantity
     */
    @JsonProperty("serviceQuantity")
    public Integer getServiceQuantity() {
        return serviceQuantity;
    }

    /**
     * 
     * @param serviceQuantity
     *     The serviceQuantity
     */
    @JsonProperty("serviceQuantity")
    public void setServiceQuantity(Integer serviceQuantity) {
        this.serviceQuantity = serviceQuantity;
    }

    /**
     * 
     * @return
     *     The status
     */
    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The request
     */
    @JsonProperty("request")
    public String getRequest() {
        return request;
    }

    /**
     * 
     * @param request
     *     The request
     */
    @JsonProperty("request")
    public void setRequest(String request) {
        this.request = request;
    }

    /**
     * 
     * @return
     *     The flightRefNbr
     */
    @JsonProperty("flightRefNbr")
    public String getFlightRefNbr() {
        return flightRefNbr;
    }

    /**
     * 
     * @param flightRefNbr
     *     The flightRefNbr
     */
    @JsonProperty("flightRefNbr")
    public void setFlightRefNbr(String flightRefNbr) {
        this.flightRefNbr = flightRefNbr;
    }

    /**
     * 
     * @return
     *     The ssrcode
     */
    @JsonProperty("ssrcode")
    public String getSsrcode() {
        return ssrcode;
    }

    /**
     * 
     * @param ssrcode
     *     The ssrcode
     */
    @JsonProperty("ssrcode")
    public void setSsrcode(String ssrcode) {
        this.ssrcode = ssrcode;
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
