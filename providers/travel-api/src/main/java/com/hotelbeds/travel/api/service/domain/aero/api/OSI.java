
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
    "request"
})
public class OSI {

    @JsonProperty("travlerRefNbr")
    private String travlerRefNbr;
    @JsonProperty("airlineCode")
    private String airlineCode;
    @JsonProperty("request")
    private String request;
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

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
