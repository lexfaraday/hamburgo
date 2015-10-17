package test.backend.www.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

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
import test.backend.www.model.GeoPoint;
import test.backend.www.model.RelativeDistance;
import test.backend.www.model.hotelbeds.HotelbedsService;
import test.backend.www.model.hotelbeds.basic.messages.AvailabilityRS;
import test.backend.www.model.hotelbeds.basic.model.Hotel;
import test.backend.www.model.sabre.SabrePricedItineraries;
import test.backend.www.model.sabre.SabrePricedItineraries.FlightSegment;
import test.backend.www.model.sabre.SabrePricedItineraries.OriginDestinationOption;
import test.backend.www.model.sabre.SabrePricedItineraries.PricedItinerary;
import test.backend.www.model.sabre.SabreService;

@Data
@EqualsAndHashCode(callSuper = false)
@Controller
// Ej:
// http://localhost:8080/journey/39.577251/2.633764/53.562139/9.956317/2015-10-22/2015-10-25/
@Slf4j
public class JourneyController {

	private static final int DEFAULT_MAX = 5;

	@Autowired
	HotelbedsService hotelbedsService;

	@Autowired
	SabreService sabreService;

	@Autowired
	AirportLocator airportLocator;

	@ResponseBody
	@RequestMapping(value = "/journey/{origin_latitude}/{origin_longitude}/{destination_latitude}/{destination_longitude}/{from}/{to}/", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> journeys(@PathVariable(value = "origin_latitude") String originLatitude,
			@PathVariable(value = "origin_longitude") String originLongitude,
			@PathVariable(value = "destination_latitude") String destinationLatitude,
			@PathVariable(value = "destination_longitude") String destinationLongitude,
			@PathVariable(value = "from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
			@PathVariable(value = "to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
			@RequestParam(value = "limitKm", required = false, defaultValue = "50") long limitKm) {

		// Locate the airports closest to the origin
		List<RelativeDistance> originAirports = airportLocator
				.getClosestAirports(new GeoPoint(originLatitude, originLongitude), DEFAULT_MAX, limitKm, true);

		// Locate the airports closest to the destination
		List<RelativeDistance> destinationAirports = airportLocator.getClosestAirports(
				new GeoPoint(destinationLatitude, destinationLongitude), DEFAULT_MAX, limitKm, true);

		SabrePricedItineraries sabrePricedItineraries = null;

		for (RelativeDistance originDistance : originAirports) {
			String country = originDistance.getAirport().getCountryCode();
			String origin = originDistance.getAirport().getIataFaaCode();
			for (RelativeDistance destinationDistance : destinationAirports) {
				try {
					sabrePricedItineraries = sabreService.getFlights(country, origin,
							destinationDistance.getAirport().getIataFaaCode(), from, to);
					if (sabrePricedItineraries != null && sabrePricedItineraries.getPricedItineraries() != null) {
						log.info("Found itinerary from {} to {}", origin,
								destinationDistance.getAirport().getIataFaaCode());
						break;
					}
				} catch (IOException e) {
					log.warn("No itinerary from {} to {}", origin, destinationDistance.getAirport().getIataFaaCode());
				} catch (Exception e) {
					log.error("Error", e);
				}
			}
		}

		// We found a travel route
		if (sabrePricedItineraries != null && sabrePricedItineraries.getPricedItineraries() != null
				&& !sabrePricedItineraries.getPricedItineraries().isEmpty()) {
			// Cheapest itinerary
			PricedItinerary cheapestItinerary = sabrePricedItineraries.getPricedItineraries().get(0);
			logItinerary("Cheapest", cheapestItinerary);
			// Priciest itinerary
			PricedItinerary priciestItinerary = sabrePricedItineraries.getPricedItineraries()
					.get(sabrePricedItineraries.getPricedItineraries().size() - 1);
			logItinerary("Priciest", priciestItinerary);
			if (log.isDebugEnabled()) {
				processItineraries(sabrePricedItineraries);
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
					if (availabilityRS.getHotels().getHotels().size() > 1) {
						Hotel priciestHotel = availabilityRS.getHotels().getHotels().get(1);
						log.info("Priciest hotel: {}: {}{}", priciestHotel.getName(), priciestHotel.getMaxRate(),
								priciestHotel.getCurrency());
					}

				}
			}

		}
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
	}

	private void processItineraries(SabrePricedItineraries sabrePricedItineraries) {
		for (PricedItinerary pricedItinerary : sabrePricedItineraries.getPricedItineraries()) {
			logItinerary("", pricedItinerary);
		}
	}

	private void logItinerary(String name, PricedItinerary pricedItinerary) {
		log.info("{} itinerary:{} {}",
				new Object[] { name, pricedItinerary.getAirItineraryPricingInfo().getItinTotalFare().getTotalFare()
						.getAmount(),
				pricedItinerary.getAirItineraryPricingInfo().getItinTotalFare().getTotalFare().getCurrencyCode() });
		for (OriginDestinationOption originDestinationOption : pricedItinerary.getAirItinerary()
				.getOriginDestinationOptions().getOriginDestinationOptions()) {
			log.info("Total time: {}", originDestinationOption.getElapsedTime());
			for (FlightSegment flightSegment : originDestinationOption.getFlightSegments()) {
				log.info("{}-{}: {}-{}",
						new Object[] { flightSegment.getMarketingAirline().getCode(), flightSegment.getFlightNumber(),
								flightSegment.getDepartureAirport().getLocationCode(),
								flightSegment.getArrivalAirport().getLocationCode() });
			}
		}
	}
}
