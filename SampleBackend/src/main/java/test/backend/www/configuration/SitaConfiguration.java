package test.backend.www.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import test.backend.www.model.sita.SitaService;

@Configuration
@Slf4j
@Data
public class SitaConfiguration {
	@Value("${sita.base_url}")
	private String baseUrl;

	@Value("${sita.key}")
	private String key;

	@Bean
	public SitaService buildSitaService() {
		log.info("Connecting to SITA API: {}", baseUrl);
		SitaService sitaService = new SitaService(baseUrl, key);
		return sitaService;
	}
}
