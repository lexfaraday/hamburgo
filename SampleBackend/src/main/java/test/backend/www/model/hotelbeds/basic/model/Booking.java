/**
 * Autogenerated code by SdkModelGenerator.
 * Do not edit. Any modification on this file will be removed automatically after project build
 *
 */
package test.backend.www.model.hotelbeds.basic.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import test.backend.www.model.hotelbeds.basic.common.SimpleTypes.BookingStatus;
import test.backend.www.model.hotelbeds.basic.convert.json.CustomDateDeserializer;
import test.backend.www.model.hotelbeds.basic.convert.json.CustomDateSerializer;
import test.backend.www.model.hotelbeds.basic.convert.json.RateSerializer;

@JsonInclude(Include.NON_NULL)
@ToString
@NoArgsConstructor
@Data
public class Booking
{

  private String reference;
  private String cancellationReference;
  private String clientReference;
  @JsonProperty
  @JsonSerialize(using = CustomDateSerializer.class)
  @JsonDeserialize(using = CustomDateDeserializer.class)
  private LocalDate creationDate;
  private BookingStatus status;
  @JsonSerialize(using = RateSerializer.class)
  private BigDecimal agCommision;
  @JsonSerialize(using = RateSerializer.class)
  private BigDecimal commisionVAT;
  private String creationUser;
  private Holder holder;
  private Hotel hotel;
  private String remark;

}