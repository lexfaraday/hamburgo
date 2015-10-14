/**
 * Autogenerated code by SdkModelGenerator.
 * Do not edit. Any modification on this file will be removed automatically after project build
 *
 */
package test.backend.www.model.hotelbeds.basic.model;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import test.backend.www.model.hotelbeds.basic.common.SimpleTypes.ReviewsType;

@JsonInclude(Include.NON_NULL)
@ToString
@NoArgsConstructor
@Data
public class ReviewFilter {

	@NotNull
	private ReviewsType type;
	@Min(value = 0)
	@Digits(fraction = 1, integer = 1)
	private BigDecimal minRate;
	@Min(value = 0)
	@Digits(fraction = 1, integer = 1)
	private BigDecimal maxRate;
	private Integer minReviewCount;


}