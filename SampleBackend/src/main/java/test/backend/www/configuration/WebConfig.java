package test.backend.www.configuration;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
@ComponentScan
public class WebConfig extends WebMvcAutoConfigurationAdapter
{
  @Bean(name = "corsFilter")
  public CorsFilter buildCorsFilter()
  {
    return new CorsFilter();
  }

  @Bean
  public Jackson2ObjectMapperBuilder jacksonBuilder()
  {
    final Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
    builder.indentOutput(true);
    builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    return builder;
  }
}
