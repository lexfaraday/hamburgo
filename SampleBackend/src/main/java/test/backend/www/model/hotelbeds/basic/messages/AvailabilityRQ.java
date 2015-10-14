/**
 * Autogenerated code by SdkModelGenerator.
 * Do not edit. Any modification on this file will be removed automatically after project build
 *
 */
package test.backend.www.model.hotelbeds.basic.messages;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import test.backend.www.model.hotelbeds.basic.annotation.validators.ValidLimitFilter;
import test.backend.www.model.hotelbeds.basic.annotation.validators.ValidOccupancies;
import test.backend.www.model.hotelbeds.basic.annotation.validators.ValidReviewFilter;
import test.backend.www.model.hotelbeds.basic.annotation.validators.ValidStay;
import test.backend.www.model.hotelbeds.basic.common.SimpleTypes.Accommodation;
import test.backend.www.model.hotelbeds.basic.model.Boards;
import test.backend.www.model.hotelbeds.basic.model.Destination;
import test.backend.www.model.hotelbeds.basic.model.Filter;
import test.backend.www.model.hotelbeds.basic.model.GeoLocation;
import test.backend.www.model.hotelbeds.basic.model.HotelsFilter;
import test.backend.www.model.hotelbeds.basic.model.KeywordsFilter;
import test.backend.www.model.hotelbeds.basic.model.Occupancy;
import test.backend.www.model.hotelbeds.basic.model.ReviewFilter;
import test.backend.www.model.hotelbeds.basic.model.Rooms;
import test.backend.www.model.hotelbeds.basic.model.Source;
import test.backend.www.model.hotelbeds.basic.model.Stay;

@JsonInclude(Include.NON_NULL)
@ToString
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class AvailabilityRQ extends AbstractGenericRequest {

	@NotNull
	@Valid
	@ValidStay(maxDaysRange = 30)
	private Stay stay;
	@Valid
	@ValidOccupancies(maxRooms = 5)
	private List<Occupancy> occupancies;
	@Valid
	private GeoLocation geolocation;
	@Valid
	private Destination destination;
	@JsonProperty("keywords")
	@Valid
	private KeywordsFilter keywordsFilter;
	@JsonProperty("hotels")
	@Valid
	private HotelsFilter hotelsFilter;
	@JsonProperty("reviews")
	@Valid
	@ValidReviewFilter
	private List<ReviewFilter> reviewsFilter;
	@Valid
	@ValidLimitFilter
	private Filter filter;
	@Valid
	private Boards boards;
	@Valid
	private Rooms rooms;
	private Boolean dailyRate;
	private String sourceMarket;
	@Valid
	private List<Accommodation> accommodations;
	@Valid
	private Source source;


}