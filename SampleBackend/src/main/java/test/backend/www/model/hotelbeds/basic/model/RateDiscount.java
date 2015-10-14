/**
 * Autogenerated code by SdkModelGenerator.
 * Do not edit. Any modification on this file will be removed automatically after project build
 *
 */
package test.backend.www.model.hotelbeds.basic.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import test.backend.www.model.hotelbeds.basic.convert.json.RateSerializer;

@JsonInclude(Include.NON_NULL)
@ToString
@NoArgsConstructor
@Data
public class RateDiscount {

	private String code;
	private String name;
	@JsonSerialize(using = RateSerializer.class)
	private BigDecimal amount;


}