
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
    "surname",
    "givenName",
    "email",
    "phoneNumber",
    "paxType",
    "ffqNumber",
    "ffqAirline",
    "dateOfBirth"
})
public class Passenger {

    @JsonProperty("surname")
    private String surname;
    @JsonProperty("givenName")
    private String givenName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    @JsonProperty("paxType")
    private String paxType;
    @JsonProperty("ffqNumber")
    private String ffqNumber;
    @JsonProperty("ffqAirline")
    private String ffqAirline;
    @JsonProperty("dateOfBirth")
    private String dateOfBirth;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The surname
     */
    @JsonProperty("surname")
    public String getSurname() {
        return surname;
    }

    /**
     * 
     * @param surname
     *     The surname
     */
    @JsonProperty("surname")
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * 
     * @return
     *     The givenName
     */
    @JsonProperty("givenName")
    public String getGivenName() {
        return givenName;
    }

    /**
     * 
     * @param givenName
     *     The givenName
     */
    @JsonProperty("givenName")
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    /**
     * 
     * @return
     *     The email
     */
    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email
     *     The email
     */
    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * @return
     *     The phoneNumber
     */
    @JsonProperty("phoneNumber")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 
     * @param phoneNumber
     *     The phoneNumber
     */
    @JsonProperty("phoneNumber")
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * 
     * @return
     *     The paxType
     */
    @JsonProperty("paxType")
    public String getPaxType() {
        return paxType;
    }

    /**
     * 
     * @param paxType
     *     The paxType
     */
    @JsonProperty("paxType")
    public void setPaxType(String paxType) {
        this.paxType = paxType;
    }

    /**
     * 
     * @return
     *     The ffqNumber
     */
    @JsonProperty("ffqNumber")
    public String getFfqNumber() {
        return ffqNumber;
    }

    /**
     * 
     * @param ffqNumber
     *     The ffqNumber
     */
    @JsonProperty("ffqNumber")
    public void setFfqNumber(String ffqNumber) {
        this.ffqNumber = ffqNumber;
    }

    /**
     * 
     * @return
     *     The ffqAirline
     */
    @JsonProperty("ffqAirline")
    public String getFfqAirline() {
        return ffqAirline;
    }

    /**
     * 
     * @param ffqAirline
     *     The ffqAirline
     */
    @JsonProperty("ffqAirline")
    public void setFfqAirline(String ffqAirline) {
        this.ffqAirline = ffqAirline;
    }

    /**
     * 
     * @return
     *     The dateOfBirth
     */
    @JsonProperty("dateOfBirth")
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * 
     * @param dateOfBirth
     *     The dateOfBirth
     */
    @JsonProperty("dateOfBirth")
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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
