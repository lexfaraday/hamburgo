package test.backend.www.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import test.backend.www.model.iata.IataService;

@Configuration
@Slf4j
@Data
public class IataConfiguration {
	@Value("${iata.base_url}")
	private String baseUrl;

	@Value("${iata.key}")
	private String key;

	@Bean
	public IataService buildIataService() {
		log.info("Connecting to IATA API: {}", baseUrl);
		IataService iataService = new IataService(baseUrl, key);
		return iataService;
	}
}
