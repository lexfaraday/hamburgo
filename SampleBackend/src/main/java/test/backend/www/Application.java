package test.backend.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@EnableAutoConfiguration
@ComponentScan(basePackages =
{ "test.backend.www" })
public class Application extends SpringBootServletInitializer
{

  private static Class<Application> appClass = Application.class;

  public static void main(final String[] args)
  {
    SpringApplication.run(appClass, args);

  }

  @Override
  protected SpringApplicationBuilder configure(final SpringApplicationBuilder application)
  {
    return application.sources(appClass);
  }
}
