
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
    "cardType",
    "cardCode",
    "cardNumber",
    "expireDate_MMyyyy",
    "cardHolderName",
    "ccv"
})
public class CreditCardPaymentInfo {

    @JsonProperty("cardType")
    private Integer cardType;
    @JsonProperty("cardCode")
    private String cardCode;
    @JsonProperty("cardNumber")
    private String cardNumber;
    @JsonProperty("expireDate_MMyyyy")
    private String expireDateMMyyyy;
    @JsonProperty("cardHolderName")
    private String cardHolderName;
    @JsonProperty("ccv")
    private String ccv;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The cardType
     */
    @JsonProperty("cardType")
    public Integer getCardType() {
        return cardType;
    }

    /**
     * 
     * @param cardType
     *     The cardType
     */
    @JsonProperty("cardType")
    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    /**
     * 
     * @return
     *     The cardCode
     */
    @JsonProperty("cardCode")
    public String getCardCode() {
        return cardCode;
    }

    /**
     * 
     * @param cardCode
     *     The cardCode
     */
    @JsonProperty("cardCode")
    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    /**
     * 
     * @return
     *     The cardNumber
     */
    @JsonProperty("cardNumber")
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * 
     * @param cardNumber
     *     The cardNumber
     */
    @JsonProperty("cardNumber")
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * 
     * @return
     *     The expireDateMMyyyy
     */
    @JsonProperty("expireDate_MMyyyy")
    public String getExpireDateMMyyyy() {
        return expireDateMMyyyy;
    }

    /**
     * 
     * @param expireDateMMyyyy
     *     The expireDate_MMyyyy
     */
    @JsonProperty("expireDate_MMyyyy")
    public void setExpireDateMMyyyy(String expireDateMMyyyy) {
        this.expireDateMMyyyy = expireDateMMyyyy;
    }

    /**
     * 
     * @return
     *     The cardHolderName
     */
    @JsonProperty("cardHolderName")
    public String getCardHolderName() {
        return cardHolderName;
    }

    /**
     * 
     * @param cardHolderName
     *     The cardHolderName
     */
    @JsonProperty("cardHolderName")
    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    /**
     * 
     * @return
     *     The ccv
     */
    @JsonProperty("ccv")
    public String getCcv() {
        return ccv;
    }

    /**
     * 
     * @param ccv
     *     The ccv
     */
    @JsonProperty("ccv")
    public void setCcv(String ccv) {
        this.ccv = ccv;
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
