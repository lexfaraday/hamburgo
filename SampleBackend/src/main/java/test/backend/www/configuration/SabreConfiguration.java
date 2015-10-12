package test.backend.www.configuration;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import test.backend.www.model.sabre.SabreService;

@Configuration
@Slf4j
@Data
public class SabreConfiguration
{

  @Value("${sabre.base_url}")
  private String baseUrl;

  @Value("${sabre.key}")
  private String key;

  @Value("${sabre.secret}")
  private String secret;

  @Bean
  public SabreService buildSabreService()
  {
    log.info("Sabre base url: {}", new Object[]
    { baseUrl });
    SabreService sabreService = new SabreService(baseUrl, key, secret);
    sabreService.initSabreOriginDestinationLocations();
    return sabreService;
  }
}
