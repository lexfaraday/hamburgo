package test.backend.www.configuration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import test.backend.www.model.sabre.SabreService;

@Configuration
@Slf4j
@Data
public class SabreConfiguration {

	@Value("${sabre.base_url}")
	private String baseUrl;

	@Value("${sabre.key}")
	private String key;

	@Value("${sabre.secret}")
	private String secret;

	@Bean
	public SabreService buildSabreService() {
		log.info("Sabre base url: {}", baseUrl);
		SabreService sabreService = new SabreService(baseUrl, key, secret);
		try {
			sabreService.initSabreOriginDestinationLocations();
		} catch (IOException e) {
			log.error("Error initialising sabre destinations", e);
		}
		return sabreService;
	}
}
