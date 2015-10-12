
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
    "stopOvers",
    "directFlight",
    "flights"
})
public class OriginDestinationOption {

    @JsonProperty("stopOvers")
    private List<Object> stopOvers = new ArrayList<Object>();
    @JsonProperty("directFlight")
    private Boolean directFlight;
    @JsonProperty("flights")
    private List<Flight> flights = new ArrayList<Flight>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The stopOvers
     */
    @JsonProperty("stopOvers")
    public List<Object> getStopOvers() {
        return stopOvers;
    }

    /**
     * 
     * @param stopOvers
     *     The stopOvers
     */
    @JsonProperty("stopOvers")
    public void setStopOvers(List<Object> stopOvers) {
        this.stopOvers = stopOvers;
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

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
