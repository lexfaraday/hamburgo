/**
 * Autogenerated code by SdkModelGenerator.
 * Do not edit. Any modification on this file will be removed automatically after project build
 *
 */
package test.backend.www.model.hotelbeds.basic.model;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import test.backend.www.model.hotelbeds.basic.annotation.validators.FutureForLocalDate;
import test.backend.www.model.hotelbeds.basic.convert.json.DateSerializer;

@JsonInclude(Include.NON_NULL)
@ToString
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Stay {

	@JsonProperty
	@JsonSerialize(using = DateSerializer.class)
	@NotNull
	@FutureForLocalDate
	private LocalDate checkIn;
	@JsonProperty
	@JsonSerialize(using = DateSerializer.class)
	@NotNull
	private LocalDate checkOut;
	@Min(value = 1)
	private Integer shiftDays;
	private Boolean allowOnlyShift;


}