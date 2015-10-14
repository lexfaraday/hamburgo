package test.backend.www.model.hotelbeds.sdk.helpers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import lombok.ToString;
import lombok.Value;
import test.backend.www.model.hotelbeds.basic.common.SimpleTypes.Accommodation;
import test.backend.www.model.hotelbeds.basic.common.SimpleTypes.HotelCodeType;
import test.backend.www.model.hotelbeds.basic.common.SimpleTypes.HotelbedsCustomerType;
import test.backend.www.model.hotelbeds.basic.common.SimpleTypes.ReviewsType;
import test.backend.www.model.hotelbeds.basic.common.SimpleTypes.ShowDirectPayment;
import test.backend.www.model.hotelbeds.basic.messages.AvailabilityRQ;
import test.backend.www.model.hotelbeds.basic.model.Boards;
import test.backend.www.model.hotelbeds.basic.model.Destination;
import test.backend.www.model.hotelbeds.basic.model.Filter;
import test.backend.www.model.hotelbeds.basic.model.GeoLocation;
import test.backend.www.model.hotelbeds.basic.model.HotelsFilter;
import test.backend.www.model.hotelbeds.basic.model.KeywordsFilter;
import test.backend.www.model.hotelbeds.basic.model.Occupancy;
import test.backend.www.model.hotelbeds.basic.model.Pax;
import test.backend.www.model.hotelbeds.basic.model.ReviewFilter;
import test.backend.www.model.hotelbeds.basic.model.Rooms;
import test.backend.www.model.hotelbeds.basic.model.Stay;
import test.backend.www.model.hotelbeds.sdk.helpers.AvailRoom.AvailRoomBuilder;
import test.backend.www.model.hotelbeds.sdk.helpers.RoomDetail.GuestType;
import test.backend.www.model.hotelbeds.util.UnitMeasure;

@Builder
@Value
@ToString
public class Availability
{

  public enum Matcher
  {
    ALL, ANY
  }

  public enum Pay
  {
    AT_WEB, AT_HOTEL, INDIFFERENT
  }

  public static interface DelimitedShape
  {
  }

  @Data
  public static class Circle implements DelimitedShape
  {
    @NotNull
    private final String longitude;
    @NotNull
    private final String latitude;
    @Min(value = 0)
    private final long radiusInKilometers;
  }

  @Data
  public static class Square implements DelimitedShape
  {
    @NotNull
    private final String northEastLatitude;
    @NotNull
    private final String northEastLongitude;
    @NotNull
    private final String southWestLatitude;
    @NotNull
    private final String southWestLongitude;
  }

  private String language;

  @NotNull
  private LocalDate checkIn;
  @NotNull
  private LocalDate checkOut;
  private Integer shiftDays;

  private String destination;
  private Integer zone;

  @Singular
  private Set<Integer> matchingKeywords;
  private Matcher keywordsMatcher;

  // TODO improve name
  private Boolean dailyRate;

  private Boolean packaging;

  private Integer minCategory;
  private Integer maxCategory;

  @Singular
  private Set<Accommodation> ofTypes;

  @Singular
  private List<Integer> includeHotels;
  @Singular
  private List<Integer> excludeHotels;
  private Boolean useGiataCodes;

  // Limits
  @Min(value = 1)
  @Max(value = 1500)
  private Integer limitHotelsTo;
  @Min(value = 1)
  private Integer limitRoomsPerHotelTo;
  @Min(value = 0)
  private BigDecimal ratesHigherThan;
  @Min(value = 0)
  private BigDecimal ratesLowerThan;
  @Min(value = 1)
  private Integer limitRatesPerRoomTo;

  // Reviews
  @Min(value = 0)
  @Max(value = 5)
  @Digits(fraction = 1, integer = 1)
  private BigDecimal hbScoreHigherThan;
  @Min(value = 0)
  @Max(value = 5)
  @Digits(fraction = 1, integer = 1)
  private BigDecimal hbScoreLowerThan;
  private Integer numberOfHBReviewsHigherThan;
  //
  @Min(value = 0)
  @Max(value = 5)
  @Digits(fraction = 1, integer = 1)
  private BigDecimal tripAdvisorScoreHigherThan;
  @Min(value = 0)
  @Max(value = 5)
  @Digits(fraction = 1, integer = 1)
  private BigDecimal tripAdvisorScoreLowerThan;
  private Integer numberOfTripAdvisorReviewsHigherThan;

  // GeoLocation filters
  private DelimitedShape withinThis;

  @Singular
  private List<String> includeBoards;
  @Singular
  private List<String> excludeBoards;

  @Singular
  private List<String> includeRoomCodes;
  @Singular
  private List<String> excludeRoomCodes;

  @Singular
  private List<AvailRoom> rooms;

  Pay payed;

  public void validate()
  {

  }

  public AvailabilityRQ toAvailabilityRQ()
  {
    AvailabilityRQ availabilityRQ = new AvailabilityRQ();
    validate();
    //
    availabilityRQ.setLanguage(language);
    //
    availabilityRQ.setStay(new Stay(checkIn, checkOut, shiftDays, true));
    //
    availabilityRQ.setOccupancies(new ArrayList<>());
    for (AvailRoom room : rooms)
    {
      Occupancy occupancy = new Occupancy();
      occupancy.setAdults(room.getAdults());
      occupancy.setChildren(room.getChildren() != null ? room.getChildren() : 0);
      occupancy.setRooms(1);
      if (!CollectionUtils.isEmpty(room.getDetails()))
      {
        occupancy.setPaxes(new ArrayList<>());
        for (RoomDetail detail : room.getDetails())
        {
          Pax pax = new Pax();
          pax.setType(detail.getType() == GuestType.ADULT ? HotelbedsCustomerType.AD : HotelbedsCustomerType.CH);
          pax.setAge(detail.getAge());
          pax.setName(detail.getName());
          pax.setSurname(detail.getSurname());
          occupancy.getPaxes().add(pax);
        }
      }
      availabilityRQ.getOccupancies().add(occupancy);
    }
    //
    if (withinThis != null)
    {
      GeoLocation location = new GeoLocation();
      location.setUnit(UnitMeasure.km);
      if (withinThis instanceof Circle)
      {
        Circle circle = (Circle) withinThis;
        location.setLatitude(new BigDecimal(circle.getLatitude()));
        location.setLongitude(new BigDecimal(circle.getLongitude()));
        location.setRadius(new BigDecimal(circle.getRadiusInKilometers()));
      }
      else if (withinThis instanceof Square)
      {
        Square square = (Square) withinThis;
        location.setLatitude(new BigDecimal(square.getNorthEastLatitude()));
        location.setLongitude(new BigDecimal(square.getNorthEastLongitude()));
        location.setSecondaryLatitude(new BigDecimal(square.getSouthWestLatitude()));
        location.setSecondaryLongitude(new BigDecimal(square.getSouthWestLongitude()));
      }
      availabilityRQ.setGeolocation(location);
    }
    //
    if (StringUtils.isNotBlank(destination))
    {
      Destination dest = new Destination();
      dest.setCode(destination);
      if (zone != null)
      {
        dest.setZone(zone);
      }
      availabilityRQ.setDestination(dest);
    }
    //
    if (!CollectionUtils.isEmpty(matchingKeywords))
    {
      List<Integer> keywords = new ArrayList<>();
      keywords.addAll(matchingKeywords);
      availabilityRQ.setKeywordsFilter(new KeywordsFilter(keywords, Matcher.ALL.equals(keywordsMatcher)));
    }
    //
    if (!CollectionUtils.isEmpty(includeHotels) && !CollectionUtils.isEmpty(excludeHotels))
    {
      includeHotels.removeAll(excludeHotels);
    }
    if (!CollectionUtils.isEmpty(includeHotels) || !CollectionUtils.isEmpty(excludeHotels))
    {
      HotelsFilter hotelsFilter = new HotelsFilter();
      if (!CollectionUtils.isEmpty(includeHotels))
      {
        hotelsFilter.setIncluded(true);
        hotelsFilter.setHotels(includeHotels);
      }
      else
      {
        hotelsFilter.setIncluded(false);
        hotelsFilter.setHotels(excludeHotels);
      }
      hotelsFilter.setType(useGiataCodes != null && useGiataCodes ? HotelCodeType.GIATA : HotelCodeType.HOTELBEDS);
      availabilityRQ.setHotelsFilter(hotelsFilter);
    }
    //
    if (!CollectionUtils.isEmpty(includeBoards) || !CollectionUtils.isEmpty(excludeBoards))
    {
      Boards boardFilter = new Boards();
      if (!CollectionUtils.isEmpty(includeBoards))
      {
        boardFilter.setIncluded(true);
        boardFilter.setBoard(includeBoards);
      }
      else
      {
        boardFilter.setIncluded(false);
        boardFilter.setBoard(excludeBoards);
      }
      availabilityRQ.setBoards(boardFilter);
    }
    //
    //
    if (!CollectionUtils.isEmpty(includeRoomCodes) || !CollectionUtils.isEmpty(excludeRoomCodes))
    {
      Rooms roomFilter = new Rooms();
      if (!CollectionUtils.isEmpty(includeRoomCodes))
      {
        roomFilter.setIncluded(true);
        roomFilter.setRoom(includeRoomCodes);
      }
      else
      {
        roomFilter.setIncluded(false);
        roomFilter.setRoom(excludeRoomCodes);
      }
      availabilityRQ.setRooms(roomFilter);
    }
    //
    availabilityRQ.setDailyRate(dailyRate);
    //
    if (!CollectionUtils.isEmpty(ofTypes))
    {
      List<Accommodation> types = new ArrayList<>();
      for (Accommodation type : ofTypes)
      {
        types.add(Accommodation.valueOf(type.name()));
      }
      availabilityRQ.setAccommodations(types);
    }
    List<ReviewFilter> reviewsFilter = null;
    if (hbScoreHigherThan != null || hbScoreLowerThan != null || numberOfHBReviewsHigherThan != null)
    {
      reviewsFilter = new ArrayList<>();
      ReviewFilter reviewFilter = new ReviewFilter();
      reviewFilter.setMaxRate(hbScoreLowerThan);
      reviewFilter.setMinRate(hbScoreHigherThan);
      reviewFilter.setMinReviewCount(numberOfHBReviewsHigherThan);
      reviewFilter.setType(ReviewsType.HOTELBEDS);
      reviewsFilter.add(reviewFilter);
    }
    if (tripAdvisorScoreHigherThan != null || tripAdvisorScoreLowerThan != null || numberOfTripAdvisorReviewsHigherThan != null)
    {
      if (reviewsFilter == null)
      {
        reviewsFilter = new ArrayList<>();
      }
      ReviewFilter reviewFilter = new ReviewFilter();
      reviewFilter.setMaxRate(tripAdvisorScoreLowerThan);
      reviewFilter.setMinRate(tripAdvisorScoreHigherThan);
      reviewFilter.setMinReviewCount(numberOfTripAdvisorReviewsHigherThan);
      reviewFilter.setType(ReviewsType.TRIPADVISOR);
      reviewsFilter.add(reviewFilter);
    }
    availabilityRQ.setReviewsFilter(reviewsFilter);
    //
    if (limitHotelsTo != null || maxCategory != null || minCategory != null || limitRoomsPerHotelTo != null || limitRatesPerRoomTo != null
        || ratesLowerThan != null || ratesHigherThan != null || packaging != null || payed != null)
    {

      Filter filter = new Filter();
      if (maxCategory != null)
      {
        filter.setMaxCategory(maxCategory);
      }
      if (minCategory != null)
      {
        filter.setMinCategory(minCategory);
      }
      if (packaging != null)
      {
        filter.setPackaging(packaging);
      }
      if (limitHotelsTo != null)
      {
        filter.setMaxHotels(limitHotelsTo);
      }
      if (limitRoomsPerHotelTo != null)
      {
        filter.setMaxRooms(limitRoomsPerHotelTo);
      }
      if (limitRatesPerRoomTo != null)
      {
        filter.setMaxRatesPerRoom(limitRatesPerRoomTo);
      }
      if (ratesLowerThan != null)
      {
        filter.setMaxRate(ratesLowerThan);
      }
      if (ratesHigherThan != null)
      {
        filter.setMinRate(ratesHigherThan);
      }
      if (payed != null)
      {
        switch (payed)
        {
        case AT_HOTEL:
          filter.setPaymentType(ShowDirectPayment.AT_HOTEL);
          break;
        case AT_WEB:
          filter.setPaymentType(ShowDirectPayment.AT_WEB);
          break;
        case INDIFFERENT:
          filter.setPaymentType(ShowDirectPayment.BOTH);
          break;
        }
      }
      availabilityRQ.setFilter(filter);
    }
    //

    return availabilityRQ;
  }

  public static class AvailabilityBuilder
  {

    public AvailabilityBuilder addRoom(AvailRoomBuilder roomBuilder)
    {
      room(roomBuilder.build());
      return this;
    }

    public AvailabilityBuilder matchingAllKeywords()
    {
      keywordsMatcher(Matcher.ALL);
      return this;
    }

    public AvailabilityBuilder matchingAnyKeyword()
    {
      keywordsMatcher(Matcher.ANY);
      return this;
    }
  }
}
