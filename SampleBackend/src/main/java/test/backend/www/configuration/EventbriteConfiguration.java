package test.backend.www.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import test.backend.www.model.eventbrite.EventbriteService;

@Configuration
@Slf4j
@Data
public class EventbriteConfiguration {
	@Value("${eventbrite.base_url}")
	private String baseUrl;

	@Value("${eventbrite.token}")
	private String token;

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate(clientHttpRequestFactory());
	}

	private ClientHttpRequestFactory clientHttpRequestFactory() {
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setReadTimeout(7000);
		factory.setConnectTimeout(7000);
		return factory;
	}

	@Bean
	public EventbriteService buildEventbriteService() {
		log.info("Connecting to Eventbrite API: {}", baseUrl);
		EventbriteService eventbriteService = new EventbriteService(baseUrl, token);
		return eventbriteService;
	}
}
