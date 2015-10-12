
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
    "validCreditCards",
    "paypal",
    "outboundOptions"
})
public class Shop {

    @JsonProperty("processingDurationMillis")
    private Integer processingDurationMillis;
    @JsonProperty("authorisedAPI")
    private Boolean authorisedAPI;
    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("airline")
    private String airline;
    @JsonProperty("validCreditCards")
    private List<String> validCreditCards = new ArrayList<String>();
    @JsonProperty("paypal")
    private Boolean paypal;
    @JsonProperty("outboundOptions")
    private List<OutboundOption> outboundOptions = new ArrayList<OutboundOption>();
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
     *     The validCreditCards
     */
    @JsonProperty("validCreditCards")
    public List<String> getValidCreditCards() {
        return validCreditCards;
    }

    /**
     * 
     * @param validCreditCards
     *     The validCreditCards
     */
    @JsonProperty("validCreditCards")
    public void setValidCreditCards(List<String> validCreditCards) {
        this.validCreditCards = validCreditCards;
    }

    /**
     * 
     * @return
     *     The paypal
     */
    @JsonProperty("paypal")
    public Boolean getPaypal() {
        return paypal;
    }

    /**
     * 
     * @param paypal
     *     The paypal
     */
    @JsonProperty("paypal")
    public void setPaypal(Boolean paypal) {
        this.paypal = paypal;
    }

    /**
     * 
     * @return
     *     The outboundOptions
     */
    @JsonProperty("outboundOptions")
    public List<OutboundOption> getOutboundOptions() {
        return outboundOptions;
    }

    /**
     * 
     * @param outboundOptions
     *     The outboundOptions
     */
    @JsonProperty("outboundOptions")
    public void setOutboundOptions(List<OutboundOption> outboundOptions) {
        this.outboundOptions = outboundOptions;
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
