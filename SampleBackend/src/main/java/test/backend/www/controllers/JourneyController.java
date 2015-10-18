package test.backend.www.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.iata.ndc.schema.AirShoppingRS;
import org.iata.ndc.schema.AirShoppingRS.OffersGroup.AirlineOffers.AirlineOffer;
import org.iata.ndc.schema.CurrencyAmountOptType;
import org.iata.ndc.schema.DataListType.Flight;
import org.iata.ndc.schema.Departure;
import org.iata.ndc.schema.FlightArrivalType;
import org.iata.ndc.schema.ListOfFlightSegmentType;
import org.iata.ndc.schema.OriginDestination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import test.backend.www.model.AirportLocator;
import test.backend.www.model.FlightJourneyData;
import test.backend.www.model.FlightOfferData;
import test.backend.www.model.HotelData;
import test.backend.www.model.OfferData;
import test.backend.www.model.eventbrite.EventbriteService;
import test.backend.www.model.eventbrite.domain.EventBean;
import test.backend.www.model.hotelbeds.HotelbedsService;
import test.backend.www.model.hotelbeds.basic.messages.AvailabilityRS;
import test.backend.www.model.hotelbeds.basic.model.Hotel;
import test.backend.www.model.iata.IataService;
import test.backend.www.model.sabre.SabrePricedItineraries;
import test.backend.www.model.sabre.SabrePricedItineraries.FlightSegment;
import test.backend.www.model.sabre.SabrePricedItineraries.OriginDestinationOption;
import test.backend.www.model.sabre.SabrePricedItineraries.PricedItinerary;
import test.backend.www.model.sabre.SabreService;
import test.backend.www.model.sita.SitaService;
import test.backend.www.model.sita.domain.Airport;

@Data
@EqualsAndHashCode(callSuper = false)
@Controller
@Slf4j
public class JourneyController {

	private static final int DEFAULT_MAX = 5;

	@Autowired
	HotelbedsService hotelbedsService;

	@Autowired
	EventbriteService eventbriteService;

	@Autowired
	SabreService sabreService;

	@Autowired
	IataService iataService;

	@Autowired
	AirportLocator airportLocator;

	@Autowired
	SitaService sitaService;

	@ResponseBody
	@RequestMapping(value = "/journey/{origin_latitude}/{origin_longitude}/{event_id}/", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	// Ejemplo Frankfurt, Barcelona :
	// http://localhost:8080/journey/50.031061/8.843783/17722113318/
	public ResponseEntity<OfferData> journeysFromEvent(@PathVariable(value = "origin_latitude") String originLatitude,
			@PathVariable(value = "origin_longitude") String originLongitude,
			@PathVariable(value = "event_id") String eventId,
			@RequestParam(value = "limitKm", required = false, defaultValue = "50") long limitKm) throws Exception {
		EventBean event = eventbriteService.getEventById(eventId);
		if (event != null && event.getVenue() != null) {
			LocalDate startDate = Instant.parse(event.getStartDate()).atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate endDate = Instant.parse(event.getEndDate()).atZone(ZoneId.systemDefault()).toLocalDate();
			log.info("Found event: {} ({}-{} :: {},{})", new Object[] { event.getTitle(), startDate, endDate,
					event.getVenue().getLatitude(), event.getVenue().getLongitude() });
			OfferData findJourneyData = findJourneyData(originLatitude, originLongitude, event.getVenue().getLatitude(),
					event.getVenue().getLongitude(), startDate.minusDays(1), endDate.plusDays(1), limitKm);
			findJourneyData.setEvent(event);
			return new ResponseEntity<>(findJourneyData, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/journey/{origin_latitude}/{origin_longitude}/{destination_latitude}/{destination_longitude}/{from}/{to}/", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	// Ejemplo:
	// http://localhost:8080/journey/39.577251/2.633764/53.562139/9.956317/2015-10-22/2015-10-25/
	public ResponseEntity<OfferData> journeys(@PathVariable(value = "origin_latitude") String originLatitude,
			@PathVariable(value = "origin_longitude") String originLongitude,
			@PathVariable(value = "destination_latitude") String destinationLatitude,
			@PathVariable(value = "destination_longitude") String destinationLongitude,
			@PathVariable(value = "from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
			@PathVariable(value = "to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
			@RequestParam(value = "limitKm", required = false, defaultValue = "50") long limitKm) throws Exception {

		return new ResponseEntity<>(findJourneyData(originLatitude, originLongitude, destinationLatitude,
				destinationLongitude, from, to, limitKm), HttpStatus.OK);
	}

	private OfferData findJourneyData(String originLatitude, String originLongitude, String destinationLatitude,
			String destinationLongitude, LocalDate from, LocalDate to, long limitKm) throws Exception {
		OfferData result = new OfferData();
		// Locate the airports closest to the origin
		List<Airport> originAirportsSita = sitaService.findAirportsByGeoPos(originLatitude, originLongitude,
				DEFAULT_MAX);
		// Locate the airports closest to the destination
		List<Airport> destinationAirportsSita = sitaService.findAirportsByGeoPos(destinationLatitude,
				destinationLongitude, DEFAULT_MAX);

		FlightJourneyData first = null;
		FlightJourneyData second = null;

		SabrePricedItineraries sabrePricedItineraries = null;

		for (Airport originDistance : originAirportsSita) {
			String country = airportLocator.getCountryCode(originDistance.getCountry());
			String origin = originDistance.getCode();
			for (Airport destinationDistance : destinationAirportsSita) {
				try {
					sabrePricedItineraries = sabreService.getFlights(country, origin, destinationDistance.getCode(),
							from, to);
					if (sabrePricedItineraries != null && sabrePricedItineraries.getPricedItineraries() != null) {
						log.info("Found itinerary from {} to {}", origin, destinationDistance.getCode());
						break;
					}
				} catch (IOException e) {
					log.info("No itinerary from {} to {}", origin, destinationDistance.getCode());
				} catch (Exception e) {
					log.error("Error", e);
				}
			}
			if (sabrePricedItineraries != null) {
				break;
			}
		}

		// We found a travel route
		if (sabrePricedItineraries != null && sabrePricedItineraries.getPricedItineraries() != null
				&& !sabrePricedItineraries.getPricedItineraries().isEmpty()) {
			// Cheapest itinerary
			PricedItinerary cheapestItinerary = sabrePricedItineraries.getPricedItineraries().get(0);
			first = logItinerary("Cheapest", cheapestItinerary);
			// Priciest itinerary
			PricedItinerary priciestItinerary = sabrePricedItineraries.getPricedItineraries()
					.get(sabrePricedItineraries.getPricedItineraries().size() - 1);
			logItinerary("Priciest", priciestItinerary);
			if (log.isDebugEnabled()) {
				processItineraries(sabrePricedItineraries);
			}

			FlightJourneyData firstTemp = null;
			FlightJourneyData secondTemp = null;
			for (Airport originDistance : originAirportsSita) {
				String origin = originDistance.getCode();
				for (Airport destinationDistance : destinationAirportsSita) {
					Date fromDate = Date.from(from.atStartOfDay(ZoneId.systemDefault()).toInstant());
					Date toDate = Date.from(to.atStartOfDay(ZoneId.systemDefault()).toInstant());
					AirShoppingRS goingAirShoppingRS = iataService.searchFlights(fromDate, origin,
							destinationDistance.getCode());
					AirShoppingRS comingAirShoppingRS = iataService.searchFlights(toDate, destinationDistance.getCode(),
							origin);
					if (goingAirShoppingRS != null && goingAirShoppingRS.getOffersGroup() != null
							&& goingAirShoppingRS.getOffersGroup().getAirlineOffers() != null
							&& goingAirShoppingRS.getOffersGroup().getAirlineOffers().size() > 0) {
						log.info("{}", goingAirShoppingRS);
						AirlineOffer firstOffer = goingAirShoppingRS.getOffersGroup().getAirlineOffers().get(0)
								.getAirlineOffer().get(0);
						FlightOfferData going = getOfferData(
								goingAirShoppingRS.getOffersGroup().getAirlineOffers().get(0).getAirlineOffer().get(0));
						FlightOfferData coming = getOfferData(comingAirShoppingRS.getOffersGroup().getAirlineOffers()
								.get(0).getAirlineOffer().get(0));
						firstTemp = new FlightJourneyData(going, coming, going.getPrice().add(coming.getPrice()),
								going.getCurrency());
						if (firstTemp != null
								&& (first == null || first.getPrice().compareTo(firstTemp.getPrice()) > 0)) {
							first = firstTemp;
						}
						if (goingAirShoppingRS.getOffersGroup().getAirlineOffers().size() > 1) {
							FlightOfferData goingTwo = getOfferData(goingAirShoppingRS.getOffersGroup()
									.getAirlineOffers().get(1).getAirlineOffer().get(0));
							FlightOfferData comingTwo = getOfferData(comingAirShoppingRS.getOffersGroup()
									.getAirlineOffers().get(1).getAirlineOffer().get(0));
							secondTemp = new FlightJourneyData(goingTwo, comingTwo,
									goingTwo.getPrice().add(comingTwo.getPrice()), goingTwo.getCurrency());
							if (secondTemp != null
									&& (second == null || second.getPrice().compareTo(secondTemp.getPrice()) > 0)) {
								second = secondTemp;
							}

						} else if (goingAirShoppingRS.getOffersGroup().getAirlineOffers().get(0).getAirlineOffer()
								.size() > 1) {
							FlightOfferData goingTwo = getOfferData(goingAirShoppingRS.getOffersGroup()
									.getAirlineOffers().get(0).getAirlineOffer().get(1));
							FlightOfferData comingTwo = getOfferData(comingAirShoppingRS.getOffersGroup()
									.getAirlineOffers().get(0).getAirlineOffer().get(1));
							secondTemp = new FlightJourneyData(goingTwo, comingTwo,
									goingTwo.getPrice().add(comingTwo.getPrice()), goingTwo.getCurrency());
							if (secondTemp != null
									&& (second == null || second.getPrice().compareTo(secondTemp.getPrice()) > 0)) {
								second = secondTemp;
							}
						}
						break;
					}
				}
				if (firstTemp != null) {
					break;
				}
			}

			// Find the hotel rates
			Object response = hotelbedsService.getAvailability(destinationLatitude, destinationLongitude, from, to,
					limitKm);

			// Find the cheapest, closest and highest quality
			if (response instanceof AvailabilityRS) {
				AvailabilityRS availabilityRS = (AvailabilityRS) response;
				if (availabilityRS.getHotels() != null && availabilityRS.getHotels().getHotels() != null) {
					Hotel cheapestHotel = availabilityRS.getHotels().getHotels().get(0);
					log.info("Cheapest hotel: {}: {}{}", cheapestHotel.getName(), cheapestHotel.getMinRate(),
							cheapestHotel.getCurrency());
					result.getHotelDatas()
							.add(new HotelData(cheapestHotel.getName(), cheapestHotel.getMinRate().toString(),
									cheapestHotel.getCurrency(),
									DateTimeFormatter.ISO_DATE.format(availabilityRS.getHotels().getCheckIn()),
									DateTimeFormatter.ISO_DATE.format(availabilityRS.getHotels().getCheckOut()),
									cheapestHotel.getCategoryName()));
					if (availabilityRS.getHotels().getHotels().size() > 1) {
						Hotel priciestHotel = availabilityRS.getHotels().getHotels().get(1);
						log.info("Priciest hotel: {}: {}{}", priciestHotel.getName(), priciestHotel.getMaxRate(),
								priciestHotel.getCurrency());
						result.getHotelDatas()
								.add(new HotelData(priciestHotel.getName(), priciestHotel.getMinRate().toString(),
										priciestHotel.getCurrency(),
										DateTimeFormatter.ISO_DATE.format(availabilityRS.getHotels().getCheckIn()),
										DateTimeFormatter.ISO_DATE.format(availabilityRS.getHotels().getCheckOut()),
										priciestHotel.getCategoryName()));
					}

				}
			}
			if (first != null) {
				result.getFlights().add(first);
			}
			if (second != null) {
				result.getFlights().add(second);
			}
		}
		return result;
	}

	private FlightOfferData getOfferData(AirlineOffer firstOffer) {
		OriginDestination originDestination = (OriginDestination) firstOffer.getPricedOffer().getAssociations().get(0)
				.getApplicableFlight().getOriginDestinationReferences().get(0);
		Flight carriers = (Flight) originDestination.getFlightReferences().getValue().get(0);
		ListOfFlightSegmentType goingListOfFlightSegmentType = (ListOfFlightSegmentType) carriers.getSegmentReferences()
				.getValue().get(0);
		int duration = goingListOfFlightSegmentType.getFlightDetail().getFlightDuration().getValue().getHours() * 60
				+ goingListOfFlightSegmentType.getFlightDetail().getFlightDuration().getValue().getMinutes();
		Departure goingDeparture = goingListOfFlightSegmentType.getDeparture();
		FlightArrivalType goingArrival = goingListOfFlightSegmentType.getArrival();
		String flightID = goingListOfFlightSegmentType.getMarketingCarrier().getName() + "-"
				+ goingListOfFlightSegmentType.getMarketingCarrier().getFlightNumber().getValue();
		String departureAirport = goingDeparture.getAirportCode().getValue();
		String arrivalAirport = goingListOfFlightSegmentType.getArrival().getAirportCode().getValue();
		CurrencyAmountOptType firstPrice = firstOffer.getTotalPrice().getDetailCurrencyPrice().getTotal();
		FlightOfferData flightOfferData = new FlightOfferData(
				goingListOfFlightSegmentType.getMarketingCarrier().getFlightNumber().getValue(),
				goingListOfFlightSegmentType.getMarketingCarrier().getName(), departureAirport, arrivalAirport,
				goingDeparture.getDate().toGregorianCalendar().getTime(),
				goingArrival.getDate().toGregorianCalendar().getTime());
		log.info("IATA going :{}", flightOfferData);
		log.debug("Going grom {} to {}: Flight {}, duration {}.Price: {}{}", new Object[] { departureAirport,
				arrivalAirport, flightID, duration, firstPrice.getValue(), firstPrice.getCode() });
		//
		flightOfferData.setCurrency(firstPrice.getCode());
		flightOfferData.setPrice(firstPrice.getValue().divide(new BigDecimal(100)));
		return flightOfferData;
	}

	private void processItineraries(SabrePricedItineraries sabrePricedItineraries) {
		for (PricedItinerary pricedItinerary : sabrePricedItineraries.getPricedItineraries()) {
			logItinerary("", pricedItinerary);
		}
	}

	private FlightJourneyData logItinerary(String name, PricedItinerary pricedItinerary) {
		log.info("{} itinerary:{} {}",
				new Object[] { name, pricedItinerary.getAirItineraryPricingInfo().getItinTotalFare().getTotalFare()
						.getAmount(),
				pricedItinerary.getAirItineraryPricingInfo().getItinTotalFare().getTotalFare().getCurrencyCode() });
		OriginDestinationOption goingOriginDestinationOption = pricedItinerary.getAirItinerary()
				.getOriginDestinationOptions().getOriginDestinationOptions().get(0);
		FlightSegment goingFlightSegment = goingOriginDestinationOption.getFlightSegments().get(0);
		log.info("Total time going: {}", goingOriginDestinationOption.getElapsedTime());
		log.debug("Going: {}-{}: {}-{}",
				new Object[] { goingFlightSegment.getMarketingAirline().getCode(), goingFlightSegment.getFlightNumber(),
						goingFlightSegment.getDepartureAirport().getLocationCode(),
						goingFlightSegment.getArrivalAirport().getLocationCode() });
		FlightOfferData goingFlightOfferData = new FlightOfferData(goingFlightSegment.getFlightNumber(),
				goingFlightSegment.getMarketingAirline().getCode(),
				goingFlightSegment.getDepartureAirport().getLocationCode(),
				goingFlightSegment.getArrivalAirport().getLocationCode(),
				Date.from(LocalDateTime
						.parse(goingFlightSegment.getDepartureDateTime(), DateTimeFormatter.ISO_LOCAL_DATE_TIME)
						.atOffset(ZoneOffset
								.ofHours(Integer.parseInt(goingFlightSegment.getDepartureTimeZone().getGmtOffset())))
						.toInstant()),
				Date.from(LocalDateTime
						.parse(goingFlightSegment.getArrivalDateTime(), DateTimeFormatter.ISO_LOCAL_DATE_TIME)
						.atOffset(ZoneOffset
								.ofHours(Integer.parseInt(goingFlightSegment.getArrivalTimeZone().getGmtOffset())))
						.toInstant()));

		OriginDestinationOption comingOriginDestinationOption = pricedItinerary.getAirItinerary()
				.getOriginDestinationOptions().getOriginDestinationOptions().get(1);
		FlightSegment comingFlightSegment = comingOriginDestinationOption.getFlightSegments().get(0);
		log.info("Total time coming: {}", comingOriginDestinationOption.getElapsedTime());
		log.debug("Coming: {}-{}: {}-{}",
				new Object[] { comingFlightSegment.getMarketingAirline().getCode(),
						comingFlightSegment.getFlightNumber(),
						comingFlightSegment.getDepartureAirport().getLocationCode(),
						comingFlightSegment.getArrivalAirport().getLocationCode() });

		FlightOfferData comingFlightOfferData = new FlightOfferData(comingFlightSegment.getFlightNumber(),
				comingFlightSegment.getMarketingAirline().getCode(),
				comingFlightSegment.getDepartureAirport().getLocationCode(),
				comingFlightSegment.getArrivalAirport().getLocationCode(),
				Date.from(LocalDateTime
						.parse(comingFlightSegment.getDepartureDateTime(), DateTimeFormatter.ISO_LOCAL_DATE_TIME)
						.atOffset(ZoneOffset
								.ofHours(Integer.parseInt(comingFlightSegment.getDepartureTimeZone().getGmtOffset())))
						.toInstant()),
				Date.from(LocalDateTime
						.parse(comingFlightSegment.getArrivalDateTime(), DateTimeFormatter.ISO_LOCAL_DATE_TIME)
						.atOffset(ZoneOffset
								.ofHours(Integer.parseInt(comingFlightSegment.getArrivalTimeZone().getGmtOffset())))
						.toInstant()));

		FlightJourneyData flightJourneyData = new FlightJourneyData(goingFlightOfferData, comingFlightOfferData,
				pricedItinerary.getAirItineraryPricingInfo().getItinTotalFare().getTotalFare().getAmount(),
				pricedItinerary.getAirItineraryPricingInfo().getItinTotalFare().getTotalFare().getCurrencyCode());
		log.info("Sabre: {}", flightJourneyData);
		return flightJourneyData;

	}
}
