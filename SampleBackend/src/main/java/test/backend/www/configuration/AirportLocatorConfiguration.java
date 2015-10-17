package test.backend.www.configuration;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import test.backend.www.model.Airport;
import test.backend.www.model.AirportLocator;

@Configuration
@Slf4j
public class AirportLocatorConfiguration {
	// Source: http://openflights.org/data.html
	final static String COUNTRIES_FILE_NAME = "countries.txt";
	final static String AIRPORT_FILE_NAME = "airports.dat";

	@Bean
	public AirportLocator buildAirportLocator() {
		log.info("Building country-codes list");
		Map<String, String> countries = new HashMap<>();
		try (InputStream inputStream = ClassLoader.getSystemResourceAsStream(COUNTRIES_FILE_NAME);
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				countries.put(parts[0], parts[1]);
			}
		} catch (Exception e) {
			log.error("Error initialising countries from file", e);
		}
		log.info("Building airport locator...");
		Map<Integer, Airport> airports = new HashMap<>();
		try (InputStream inputStream = ClassLoader.getSystemResourceAsStream(AIRPORT_FILE_NAME);
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				Airport airport = new Airport(line);
				if (countries.containsKey(airport.getCountry())) {
					airport.setCountryCode(countries.get(airport.getCountry()));
				} else {
					log.debug("No country code found for {}", airport.getCountry());
				}

				airports.put(airport.getId(), airport);
			}
		} catch (Exception e) {
			log.error("Error initialising airports from file", e);
		}
		log.info("Airport locator configured with {} airports.", airports.size());
		return new AirportLocator(airports);
	}
}
