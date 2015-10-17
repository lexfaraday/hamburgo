
package test.backend.www.model.sita.domain;

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
    "errorMessage",
    "airports"
})
public class AirportResponse {

    @JsonProperty("processingDurationMillis")
    private Integer processingDurationMillis;
    @JsonProperty("authorisedAPI")
    private Boolean authorisedAPI;
    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("airline")
    private Object airline;
    @JsonProperty("errorMessage")
    private Object errorMessage;
    @JsonProperty("airports")
    private List<Airport> airports = new ArrayList<Airport>();
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
    public Object getAirline() {
        return airline;
    }

    /**
     * 
     * @param airline
     *     The airline
     */
    @JsonProperty("airline")
    public void setAirline(Object airline) {
        this.airline = airline;
    }

    /**
     * 
     * @return
     *     The errorMessage
     */
    @JsonProperty("errorMessage")
    public Object getErrorMessage() {
        return errorMessage;
    }

    /**
     * 
     * @param errorMessage
     *     The errorMessage
     */
    @JsonProperty("errorMessage")
    public void setErrorMessage(Object errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * 
     * @return
     *     The airports
     */
    @JsonProperty("airports")
    public List<Airport> getAirports() {
        return airports;
    }

    /**
     * 
     * @param airports
     *     The airports
     */
    @JsonProperty("airports")
    public void setAirports(List<Airport> airports) {
        this.airports = airports;
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
