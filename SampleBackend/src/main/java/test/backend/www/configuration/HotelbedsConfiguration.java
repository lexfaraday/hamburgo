package test.backend.www.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import test.backend.www.model.hotelbeds.HotelbedsService;

@Configuration
@Slf4j
@Data
public class HotelbedsConfiguration
{
  @Value("${hotelbeds.base_url}")
  private String baseUrl;

  @Value("${hotelbeds.key}")
  private String key;

  @Value("${hotelbeds.secret}")
  private String secret;

  @Bean
  public HotelbedsService buildHotelbedsService()
  {
    log.info("Connecting to Hotelbeds API: {}", baseUrl);
    HotelbedsService hotelbedsService = new HotelbedsService(baseUrl, key, secret);
    return hotelbedsService;
  }
}
