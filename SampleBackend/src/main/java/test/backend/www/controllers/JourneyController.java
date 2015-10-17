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

		FlightOfferData first = null;
		FlightOfferData second = null;

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

			FlightOfferData firstTemp = null;
			FlightOfferData secondTemp = null;
			for (Airport originDistance : originAirportsSita) {
				String origin = originDistance.getCode();
				for (Airport destinationDistance : destinationAirportsSita) {
					Date fromDate = Date.from(from.atStartOfDay(ZoneId.systemDefault()).toInstant());
					Date toDate = Date.from(to.atStartOfDay(ZoneId.systemDefault()).toInstant());
					AirShoppingRS airShoppingRS = iataService.searchFlights(fromDate, toDate, origin,
							destinationDistance.getCode());
					if (airShoppingRS != null && airShoppingRS.getOffersGroup() != null
							&& airShoppingRS.getOffersGroup().getAirlineOffers() != null
							&& airShoppingRS.getOffersGroup().getAirlineOffers().size() > 0) {
						log.info("{}", airShoppingRS);
						AirlineOffer firstOffer = airShoppingRS.getOffersGroup().getAirlineOffers().get(0)
								.getAirlineOffer().get(0);
						firstTemp = getOfferData(firstOffer);
						if (firstTemp != null
								&& (first == null || first.getPrice().compareTo(firstTemp.getPrice()) > 0)) {
							first = firstTemp;

						}
						if (airShoppingRS.getOffersGroup().getAirlineOffers().size() > 1) {
							AirlineOffer secondOffer = airShoppingRS.getOffersGroup().getAirlineOffers().get(1)
									.getAirlineOffer().get(0);
							secondTemp = getOfferData(secondOffer);
							if (secondTemp != null
									&& (second == null || second.getPrice().compareTo(secondTemp.getPrice()) > 0)) {
								second = secondTemp;
							}

						} else if (airShoppingRS.getOffersGroup().getAirlineOffers().get(0).getAirlineOffer()
								.size() > 1) {
							AirlineOffer secondOffer = airShoppingRS.getOffersGroup().getAirlineOffers().get(0)
									.getAirlineOffer().get(1);
							secondTemp = getOfferData(secondOffer);
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
					result.setHoteldata(new HotelData(cheapestHotel.getName(), cheapestHotel.getMinRate().toString(),
							cheapestHotel.getCurrency(),
							DateTimeFormatter.ISO_DATE.format(availabilityRS.getHotels().getCheckIn()),
							DateTimeFormatter.ISO_DATE.format(availabilityRS.getHotels().getCheckOut()),
							cheapestHotel.getCategoryName()));
					if (availabilityRS.getHotels().getHotels().size() > 1) {
						Hotel priciestHotel = availabilityRS.getHotels().getHotels().get(1);
						log.info("Priciest hotel: {}: {}{}", priciestHotel.getName(), priciestHotel.getMaxRate(),
								priciestHotel.getCurrency());
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
		ListOfFlightSegmentType listOfFlightSegmentType = (ListOfFlightSegmentType) carriers.getSegmentReferences()
				.getValue().get(0);
		int duration = listOfFlightSegmentType.getFlightDetail().getFlightDuration().getValue().getHours() * 60
				+ listOfFlightSegmentType.getFlightDetail().getFlightDuration().getValue().getMinutes();
		Departure departure = listOfFlightSegmentType.getDeparture();
		FlightArrivalType arrival = listOfFlightSegmentType.getArrival();
		String flightID = listOfFlightSegmentType.getMarketingCarrier().getName() + "-"
				+ listOfFlightSegmentType.getMarketingCarrier().getFlightNumber().getValue();
		String departureAirport = departure.getAirportCode().getValue();
		String arrivalAirport = listOfFlightSegmentType.getArrival().getAirportCode().getValue();
		CurrencyAmountOptType firstPrice = firstOffer.getTotalPrice().getDetailCurrencyPrice().getTotal();
		FlightOfferData flightOfferData = new FlightOfferData(
				listOfFlightSegmentType.getMarketingCarrier().getFlightNumber().getValue(),
				listOfFlightSegmentType.getMarketingCarrier().getName(),
				firstPrice.getValue().divide(new BigDecimal(100)), firstPrice.getCode(), departureAirport,
				arrivalAirport, departure.getDate().toGregorianCalendar().getTime(),
				arrival.getDate().toGregorianCalendar().getTime());
		log.info("IATA:{}", flightOfferData);
		log.debug("From {} to {}: Flight {}, duration {}.Price: {}{}", new Object[] { departureAirport, arrivalAirport,
				flightID, duration, firstPrice.getValue(), firstPrice.getCode() });
		return flightOfferData;
	}

	private void processItineraries(SabrePricedItineraries sabrePricedItineraries) {
		for (PricedItinerary pricedItinerary : sabrePricedItineraries.getPricedItineraries()) {
			logItinerary("", pricedItinerary);
		}
	}

	private FlightOfferData logItinerary(String name, PricedItinerary pricedItinerary) {
		log.info("{} itinerary:{} {}",
				new Object[] { name, pricedItinerary.getAirItineraryPricingInfo().getItinTotalFare().getTotalFare()
						.getAmount(),
				pricedItinerary.getAirItineraryPricingInfo().getItinTotalFare().getTotalFare().getCurrencyCode() });
		OriginDestinationOption originDestinationOption = pricedItinerary.getAirItinerary()
				.getOriginDestinationOptions().getOriginDestinationOptions().get(0);
		log.info("Total time: {}", originDestinationOption.getElapsedTime());
		FlightSegment flightSegment = originDestinationOption.getFlightSegments().get(0);
		log.debug("{}-{}: {}-{}",
				new Object[] { flightSegment.getMarketingAirline().getCode(), flightSegment.getFlightNumber(),
						flightSegment.getDepartureAirport().getLocationCode(),
						flightSegment.getArrivalAirport().getLocationCode() });

		FlightOfferData flightOfferData = new FlightOfferData(flightSegment.getFlightNumber(),
				flightSegment.getMarketingAirline().getCode(),
				pricedItinerary.getAirItineraryPricingInfo().getItinTotalFare().getTotalFare().getAmount(),
				pricedItinerary.getAirItineraryPricingInfo().getItinTotalFare().getTotalFare().getCurrencyCode(),
				flightSegment.getDepartureAirport().getLocationCode(),
				flightSegment.getArrivalAirport().getLocationCode(),
				Date.from(
						LocalDateTime.parse(flightSegment.getDepartureDateTime(), DateTimeFormatter.ISO_LOCAL_DATE_TIME)
								.atOffset(ZoneOffset
										.ofHours(Integer.parseInt(flightSegment.getDepartureTimeZone().getGmtOffset())))
						.toInstant()),
				Date.from(LocalDateTime.parse(flightSegment.getArrivalDateTime(), DateTimeFormatter.ISO_LOCAL_DATE_TIME)
						.atOffset(
								ZoneOffset.ofHours(Integer.parseInt(flightSegment.getArrivalTimeZone().getGmtOffset())))
						.toInstant()));
		log.info("Sabre: {}", flightOfferData);
		return flightOfferData;

	}
}
