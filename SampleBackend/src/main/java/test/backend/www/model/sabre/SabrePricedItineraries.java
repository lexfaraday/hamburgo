package test.backend.www.model.sabre;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class SabrePricedItineraries
{

  @Data
  public static class TicketingInfo
  {
    @JsonProperty("TicketType")
    String ticketType;
  }

  @Data
  public static class Flight
  {
    @JsonProperty("FlightNumber")
    String flightNumber;
    @JsonProperty("Code")
    String code;
  }

  @Data
  public static class Equipment
  {
    @JsonProperty("AirEquipType")
    String airEquipType;
  }

  @Data
  public static class ETicket
  {
    @JsonProperty("Ind")
    boolean ind;
  }

  @Data
  public static class DivideInParty
  {
    @JsonProperty("Indicator")
    boolean indicator;
  }

  @Data
  public static class Cabin
  {
    @JsonProperty("Cabin")
    String cabin;
  }

  @Data
  public static class ValidatingCarrier
  {
    @JsonProperty("Code")
    String code;
  }

  @Data
  public static class SeatsRemaining
  {
    @JsonProperty("BelowMin")
    boolean belowMin;
    @JsonProperty("Number")
    int number;
  }

  @Data
  public static class Extensions
  {
    ETicket eTicket;

    @JsonProperty("ValidatingCarrier")
    ValidatingCarrier validatingCarrier;

    @JsonProperty("Cabin")
    Cabin cabin;

    @JsonProperty("SeatsRemaining")
    SeatsRemaining seatsRemaining;

    @JsonProperty("DivideInParty")
    DivideInParty divideInParty;
  }

  @Data
  public static class TimeZone
  {
    @JsonProperty("GMTOffset")
    String gmtOffset;
  }

  @Data
  public static class Aiport
  {
    @JsonProperty("LocationCode")
    String locationCode;
  }

  @Data
  public static class Airline
  {
    @JsonProperty("Code")
    String code;
  }

  @Data
  public static class FlightSegment
  {
    @JsonProperty("DepartureAirport")
    Aiport departureAirport;
    @JsonProperty("ArrivalAirport")
    Aiport arrivalAirport;
    @JsonProperty("MarketingAirline")
    Airline marketingAirline;
    @JsonProperty("ArrivalTimeZone")
    TimeZone arrivalTimeZone;
    @JsonProperty("TPA_Extensions")
    Extensions tpaExtensions;
    @JsonProperty("StopQuantity")
    int stopQuantity;
    @JsonProperty("ElapsedTime")
    int elapsedTime;
    @JsonProperty("ResBookDesigCode")
    String resBookDesigCode;
    @JsonProperty("MarriageGrp")
    String marriageGrp;
    @JsonProperty("Equipment")
    Equipment equipment;

    @JsonProperty("DepartureDateTime")
    String departureDateTime;
    @JsonProperty("ArrivalDateTime")
    String arrivalDateTime;
    @JsonProperty("FlightNumber")
    String flightNumber;
    @JsonProperty("OperatingAirline")
    Flight operatingAirline;
    @JsonProperty("DepartureTimeZone")
    TimeZone departureTimeZone;
    @JsonProperty("DisclosureAirline")
    Flight disclosureAirline;
  }

  @Data
  public static class OriginDestinationOption
  {
    @JsonProperty("FlightSegment")
    List<FlightSegment> flightSegments;
    @JsonProperty("ElapsedTime")
    int elapsedTime;
  }

  @Data
  public static class OriginDestinationOptions
  {
    @JsonProperty("OriginDestinationOption")
    List<OriginDestinationOption> originDestinationOptions;
  }

  @Data
  public static class AirItinerary
  {
    @JsonProperty("OriginDestinationOptions")
    OriginDestinationOptions originDestinationOptions;

    @JsonProperty("DirectionInd")
    String DirectionInd;

  }

  @Data
  @EqualsAndHashCode(callSuper = false)
  public static class Tax extends Fare
  {
    @JsonProperty("TaxCode")
    String taxCode;
  }

  @Data
  public static class Fare
  {
    @JsonProperty("CurrencyCode")
    String currencyCode;
    @JsonProperty("DecimalPlaces")
    int decimalPlaces;
    @JsonProperty("Amount")
    BigDecimal amount;
  }

  @Data
  public static class FareInfo
  {
    @JsonProperty("TPA_Extensions")
    Extensions tpaExtensions;

    @JsonProperty("FareReference")
    String fareReference;
  }

  @Data
  public static class FareInfos
  {
    @JsonProperty("FareInfo")
    List<FareInfo> fareInfos;
  }

  @Data
  public static class FareBreakdowns
  {
    @JsonProperty("PTC_FareBreakdown")
    FareBreakdown fareBreakdown;
  }

  @Data
  public static class FareBreakdown
  {
    @JsonProperty("FareBasisCodes")
    FareBasisCodes fareBasisCodes;

    @JsonProperty("PassengerTypeQuantity")
    PassengerTypeQuantity passengerTypeQuantity;

    @JsonProperty("PassengerFare")
    TotalFare passengerFare;
  }

  @Data
  public static class FareBasisCodes
  {
    @JsonProperty("FareBasisCode")
    List<FareBasisCode> fareBasisCodes;
  }

  @Data
  public static class FareBasisCode
  {
    @JsonProperty("BookingCode")
    String bookingCode;
    @JsonProperty("DepartureAirportCode")
    String departureAirportCode;
    @JsonProperty("AvailabilityBreak")
    String availabilityBreak;
    @JsonProperty("ArrivalAirportCode")
    String arrivalAirportCode;
    @JsonProperty("content")
    String content;
  }

  @Data
  public static class Taxes
  {
    @JsonProperty("TotalTax")
    Tax totalTax;
    @JsonProperty("Tax")
    List<Tax> taxes;
  }

  @Data
  public static class PassengerTypeQuantity
  {
    @JsonProperty("Quantity")
    int quantity;
    @JsonProperty("Code")
    String code;
  }

  @Data
  public static class TotalFare
  {
    @JsonProperty("FareConstruction")
    Fare fareConstruction;
    @JsonProperty("TotalFare")
    Fare totalFare;
    @JsonProperty("Taxes")
    Taxes taxes;
    @JsonProperty("BaseFare")
    Fare baseFare;
    @JsonProperty("EquivFare")
    Fare equivFare;
  }

  @Data
  public static class AirItineraryPricingInfo
  {
    @JsonProperty("FareInfos")
    FareInfos fareInfos;

    @JsonProperty("PTC_FareBreakdowns")
    FareBreakdowns fareBreakdowns;

    @JsonProperty("TPA_Extensions")
    Extensions tpaExtensions;

    @JsonProperty("ItinTotalFare")
    TotalFare itinTotalFare;

  }

  @Data
  public static class PricedItinerary
  {
    @JsonProperty("AirItinerary")
    AirItinerary airItinerary;

    @JsonProperty("TPA_Extensions")
    Extensions tpaExtensions;

    @JsonProperty("SequenceNumber")
    int sequenceNumber;

    @JsonProperty("AirItineraryPricingInfo")
    AirItineraryPricingInfo airItineraryPricingInfo;

    @JsonProperty("TicketingInfo")
    TicketingInfo ticketingInfo;
  }

  @JsonProperty("PricedItineraries")
  List<PricedItinerary> pricedItineraries;

  @JsonProperty("ReturnDateTime")
  String returnDateTime;

  @JsonProperty("DepartureDateTime")
  String departureDateTime;

  @JsonProperty("DestinationLocation")
  String DestinationLocation;

  @JsonProperty("OriginLocation")
  String originLocation;

  @JsonProperty("Links")
  private List<SabreLink> links;
}
