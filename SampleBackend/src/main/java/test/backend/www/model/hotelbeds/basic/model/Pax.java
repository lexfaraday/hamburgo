/**
 * Autogenerated code by SdkModelGenerator.
 * Do not edit. Any modification on this file will be removed automatically after project build
 *
 */
package test.backend.www.model.hotelbeds.basic.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import test.backend.www.model.hotelbeds.basic.common.SimpleTypes.HotelbedsCustomerType;

@JsonInclude(Include.NON_NULL)
@ToString
@NoArgsConstructor
@Data
public class Pax {

	@Min(value = 1)
	private Integer roomId;
	@NotNull
	private HotelbedsCustomerType type;
	@Min(value = 0)
	private Integer age;
	private String name;
	private String surname;


}