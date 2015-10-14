package test.backend.www.model.hotelbeds.sdk;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.InterceptingClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import test.backend.www.model.hotelbeds.basic.common.SimpleTypes.CancellationFlags;
import test.backend.www.model.hotelbeds.basic.messages.AbstractGenericRequest;
import test.backend.www.model.hotelbeds.basic.messages.AbstractGenericResponse;
import test.backend.www.model.hotelbeds.basic.messages.AvailabilityRQ;
import test.backend.www.model.hotelbeds.basic.messages.AvailabilityRS;
import test.backend.www.model.hotelbeds.basic.messages.BookingCancellationRS;
import test.backend.www.model.hotelbeds.basic.messages.BookingDetailRS;
import test.backend.www.model.hotelbeds.basic.messages.BookingListRS;
import test.backend.www.model.hotelbeds.basic.messages.BookingRQ;
import test.backend.www.model.hotelbeds.basic.messages.BookingRS;
import test.backend.www.model.hotelbeds.basic.messages.CheckRateRQ;
import test.backend.www.model.hotelbeds.basic.messages.CheckRateRS;
import test.backend.www.model.hotelbeds.basic.messages.HotelbedsError;
import test.backend.www.model.hotelbeds.basic.messages.StatusRS;
import test.backend.www.model.hotelbeds.sdk.helpers.Availability;
import test.backend.www.model.hotelbeds.sdk.helpers.Booking;
import test.backend.www.model.hotelbeds.sdk.helpers.BookingCheck;
import test.backend.www.model.hotelbeds.sdk.helpers.LoggingRequestInterceptor;
import test.backend.www.model.hotelbeds.sdk.helpers.RestTemplateSpecificErrorHandler;
import test.backend.www.model.hotelbeds.sdk.types.FilterType;
import test.backend.www.model.hotelbeds.sdk.types.HotelApiPaths;
import test.backend.www.model.hotelbeds.sdk.types.HotelSDKException;

/**
 * Copyright (c) Hotelbeds Technology S.L.U. All rights reserved.
 */
@Slf4j
@Data
public class HotelApiClient
{

  public static final String APIKEY_PROPERTY_NAME = "hotelapi.apikey";
  public static final String SHAREDSECRET_PROPERTY_NAME = "hotelapi.sharedsecret";
  public static final String VERSION_PROPERTY_NAME = "hotelapi.version";
  public static final String SERVICE_PROPERTY_NAME = "hotelapi.service";
  public static final String HOTELAPI_PROPERTIES_FILE_NAME = "hotelapi.properties";
  public static final String DEFAULT_LANGUAGE = "ENG";
  private static final int REST_TEMPLATE_READ_TIME_OUT = 5000;

  private static final String HOTEL_API_URL_PROPERTY = "hotel-api.url";
  private static final String API_KEY_HEADER_NAME = "Api-Key";
  private static final String SIGNATURE_HEADER_NAME = "X-Signature";

  private final String apiKey;
  private final String sharedSecret;
  private final String basePath;
  private Properties properties = null;
  private RestTemplate restTemplate = null;
  private boolean initialised = false;
  private int readTimeout = REST_TEMPLATE_READ_TIME_OUT;
  private int connectTimeout = REST_TEMPLATE_READ_TIME_OUT;
  private int connectionRequestTimeout = REST_TEMPLATE_READ_TIME_OUT;

  public HotelApiClient(String basePath, String apiKey, String sharedSecret)
  {
    this.apiKey = getHotelApiKey(apiKey);
    this.sharedSecret = getHotelApiSharedSecret(sharedSecret);
    if (StringUtils.isBlank(this.apiKey) || StringUtils.isBlank(this.sharedSecret))
    {
      throw new IllegalArgumentException("HotelApiClient cannot be created without specifying an API key and a Shared Secret");
    }
    String customUrl = System.getProperty(HOTEL_API_URL_PROPERTY);
    if (StringUtils.isEmpty(customUrl))
    {
      this.basePath = basePath;
    }
    else
    {
      this.basePath = customUrl;
    }
    properties = new Properties();
  }

  public void init()
  {
    restTemplate = getRestTemplate();
    initialised = true;
  }

  public void setReadTimeout(int readTimeout)
  {
    this.readTimeout = readTimeout;
    if (isInitialised())
    {
      log.warn("HotelAPIClient is already initialised, new timeout will have no effect.");
    }
  }

  public void setConnectTimeout(int connectTimeout)
  {
    this.connectTimeout = connectTimeout;
    if (isInitialised())
    {
      log.warn("HotelAPIClient is already initialised, new timeout will have no effect.");
    }
  }

  public void setConnectionRequestTimeout(int connectionRequestTimeout)
  {
    this.connectionRequestTimeout = connectionRequestTimeout;
    if (isInitialised())
    {
      log.warn("HotelAPIClient is already initialised, new timeout will have no effect.");
    }
  }

  private String getHotelApiProperty(String propertyName)
  {
    if (properties == null)
    {
      try (InputStream hotelApiPropertiesIS = ClassLoader.getSystemResourceAsStream(HOTELAPI_PROPERTIES_FILE_NAME))
      {
        properties = new Properties();
        if (hotelApiPropertiesIS != null)
        {
          properties.load(hotelApiPropertiesIS);
        }
      }
      catch (IOException e)
      {
        log.error("Error loading properties (){}.", HOTELAPI_PROPERTIES_FILE_NAME, e);
      }
    }
    return properties.getProperty(propertyName);
  }

  private String getHotelApiKey(String providedDefault)
  {
    String result = providedDefault;
    String fromProperties = getValueFromProperties("Api Key", APIKEY_PROPERTY_NAME);
    if (fromProperties != null)
    {
      result = fromProperties;
    }
    return result;
  }

  private String getHotelApiSharedSecret(String providedDefault)
  {
    String result = providedDefault;
    String fromProperties = getValueFromProperties("Shared Secret", SHAREDSECRET_PROPERTY_NAME);
    if (fromProperties != null)
    {
      result = fromProperties;
    }
    return result;
  }

  private String getValueFromProperties(String name, String propertyName)
  {
    String apiKey = System.getProperty(propertyName);
    if (apiKey == null)
    {
      apiKey = getHotelApiProperty(propertyName);
      if (apiKey != null)
      {
        log.debug("{} loaded from properties file. {}", name, apiKey);
      }
      else
      {
        log.debug("No {} loaded from properties, value not specified.", name);
      }
    }
    else
    {
      log.debug("{} loaded from system properties. {}", name, apiKey);
    }
    return apiKey;
  }

  // TODO Fix so it does return an object of the proper type, else throw an error if availability failed
  // TODO Documentation pending
  public AvailabilityRS availability(Availability availability) throws HotelSDKException
  {
    AvailabilityRQ availabilityRQ = availability.toAvailabilityRQ();
    AvailabilityRS availabilityRS = doAvailability(availabilityRQ);
    return availabilityRS;
  }

  // TODO Fix so it does return an object of the proper type, else throw an error if availability failed
  // TODO Documentation pending
  public AvailabilityRS doAvailability(final AvailabilityRQ request) throws HotelSDKException
  {
    final ResponseEntity<AvailabilityRS> responseEntity = callRemoteAPI(request, HotelApiPaths.AVAILABILITY);
    final AvailabilityRS response = responseEntity.getBody();
    return response;
  }

  // TODO Fix so it does return an object of the proper type, else throw an error if availability failed
  // TODO Documentation pending
  public BookingListRS list(LocalDate from, LocalDate to, boolean includeCancelled, FilterType filterType) throws HotelSDKException
  {
    final Map<String, String> params = new HashMap<>();
    params.put("from", from.toString());
    params.put("to", to.toString());
    params.put("includeCancelled", Boolean.toString(includeCancelled));
    params.put("filterType", filterType.name());
    final ResponseEntity<BookingListRS> responseEntity = callRemoteAPI(params, HotelApiPaths.BOOKING_LIST);
    return responseEntity.getBody();
  }

  // TODO Fix so it does return an object of the proper type, else throw an error if availability failed
  // TODO Documentation pending
  public BookingDetailRS detail(String bookingId) throws HotelSDKException
  {
    final Map<String, String> params = new HashMap<>();
    params.put("bookingId", bookingId);
    final ResponseEntity<BookingDetailRS> responseEntity = callRemoteAPI(params, HotelApiPaths.BOOKING_DETAIL);
    return responseEntity.getBody();
  }

  // TODO Fix so it does return an object of the proper type, else throw an error if availability failed
  // TODO Documentation pending
  public BookingRS confirm(Booking booking) throws HotelSDKException
  {
    BookingRQ bookingRQ = booking.toBookingRQ();
    BookingRS bookingRS = doBookingConfirm(bookingRQ);
    return bookingRS;
  }

  // TODO Fix so it does return an object of the proper type, else throw an error if availability failed
  // TODO Documentation pending
  public BookingRS doBookingConfirm(BookingRQ request) throws HotelSDKException
  {
    final ResponseEntity<BookingRS> responseEntity = callRemoteAPI(request, HotelApiPaths.BOOKING_CONFIRM);
    final BookingRS response = responseEntity.getBody();
    return response;
  }

  public BookingCancellationRS cancel(String bookingId) throws HotelSDKException
  {
    return cancel(bookingId, false);
  }

  // TODO Fix so it does return an object of the proper type, else throw an error if availability failed
  // TODO Documentation pending
  public BookingCancellationRS cancel(String bookingId, boolean isSimulation) throws HotelSDKException
  {
    final Map<String, String> params = new HashMap<>();
    params.put("bookingId", bookingId);
    params.put("flag", isSimulation ? CancellationFlags.SIMULATION.name() : CancellationFlags.CANCELLATION.name());
    final ResponseEntity<BookingCancellationRS> responseEntity = callRemoteAPI(params, HotelApiPaths.BOOKING_CANCEL);
    return responseEntity.getBody();
  }

  // TODO Fix so it does return an object of the proper type, else throw an error if availability failed
  // TODO Documentation pending
  public CheckRateRS check(BookingCheck bookingCheck) throws HotelSDKException
  {
    CheckRateRQ bookingCheckRQ = bookingCheck.toCheckRateRQ();
    CheckRateRS bookingCheckRS = doCheckRate(bookingCheckRQ);
    return bookingCheckRS;
  }

  // TODO Fix so it does return an object of the proper type, else throw an error if availability failed
  // TODO Documentation pending
  public CheckRateRS doCheckRate(CheckRateRQ request) throws HotelSDKException
  {
    final ResponseEntity<CheckRateRS> responseEntity = callRemoteAPI(request, HotelApiPaths.CHECK_AVAIL);
    final CheckRateRS response = responseEntity.getBody();
    return response;
  }

  // TODO Fix so it does return an object of the proper type, else throw an error if availability failed
  // TODO Documentation pending
  public StatusRS status() throws HotelSDKException
  {
    final ResponseEntity<StatusRS> responseEntity = callRemoteAPI(HotelApiPaths.STATUS);
    return responseEntity.getBody();
  }

  private <T extends AbstractGenericResponse> ResponseEntity<T> callRemoteAPI(HotelApiPaths path) throws HotelSDKException
  {
    return callRemoteAPI(null, null, path);
  }

  private <T extends AbstractGenericResponse> ResponseEntity<T> callRemoteAPI(final Map<String, String> params, HotelApiPaths path)
      throws HotelSDKException
  {
    return callRemoteAPI(null, params, path);
  }

  private <T extends AbstractGenericResponse> ResponseEntity<T> callRemoteAPI(final AbstractGenericRequest request, HotelApiPaths path)
      throws HotelSDKException
  {
    return callRemoteAPI(request, null, path);
  }

  private <T extends AbstractGenericResponse> ResponseEntity<T> callRemoteAPI(final AbstractGenericRequest request, final Map<String, String> params,
      HotelApiPaths path) throws HotelSDKException
  {
    if (isInitialised())
    {
      final HttpMethod httpMethod = path.getHttpMethod();
      final String url = path.getUrl(basePath, params);
      HttpEntity<?> httpEntity = null;
      if (httpMethod.equals(HttpMethod.POST) && request != null)
      {
        httpEntity = new HttpEntity<>(request, getHeaders(httpMethod));
      }
      else
      {
        httpEntity = new HttpEntity<>(getHeaders(httpMethod));
      }
      try
      {
        @SuppressWarnings("unchecked")
        final ResponseEntity<T> responseEntity = (ResponseEntity<T>) restTemplate.exchange(url, httpMethod, httpEntity, path.getResponseClass());
        if (responseEntity.getBody().getError() != null)
        {
          throw new HotelSDKException(responseEntity.getBody().getError());
        }
        return responseEntity;
      }
      catch (HotelSDKException e)
      {
        throw e;
      }
      catch (ResourceAccessException e)
      {
        if (e.getCause() != null && e.getCause() instanceof SocketTimeoutException)
        {
          throw new HotelSDKException(new HotelbedsError("Timeout", e.getCause().getMessage()));
        }
        else
        {
          throw new HotelSDKException(new HotelbedsError("Error accessing API", e.getCause().getMessage()));
        }
      }
      catch (Exception e)
      {
        throw new HotelSDKException(new HotelbedsError(e.getClass().getName(), e.getMessage()), e);
      }
    }
    else
    {
      throw new HotelSDKException(
                                  new HotelbedsError(
                                                     "HotelAPIClient not initialised",
                                                     "You have to call init() first, to be able to use this object."));
    }
  }

  private MultiValueMap<String, String> getHeaders(HttpMethod httpMethod)
  {
    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    headers.add(API_KEY_HEADER_NAME, apiKey);
    // Hash the Api Key + Shared Secret + Current timestamp in seconds
    String signature = org.apache.commons.codec.digest.DigestUtils.sha256Hex(apiKey + sharedSecret + System.currentTimeMillis() / 1000);
    headers.add(SIGNATURE_HEADER_NAME, signature);
    switch (httpMethod)
    {
    case GET:
    case DELETE:
      headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
      break;
    case POST:
    case PUT:
      headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
      break;
    default:
      break;
    }
    return headers;
  }

  private RestTemplate getRestTemplate()
  {
    final LoggingRequestInterceptor loggingRequestInterceptor = new LoggingRequestInterceptor();
    final List<ClientHttpRequestInterceptor> arrayClientHttpRequestInterceptor = new ArrayList<ClientHttpRequestInterceptor>();
    arrayClientHttpRequestInterceptor.add(loggingRequestInterceptor);
    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
    factory.setReadTimeout(readTimeout);
    factory.setConnectTimeout(connectTimeout);
    factory.setConnectionRequestTimeout(connectionRequestTimeout);
    final RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(factory));
    restTemplate.setInterceptors(arrayClientHttpRequestInterceptor);
    restTemplate.setRequestFactory(new InterceptingClientHttpRequestFactory(restTemplate.getRequestFactory(), arrayClientHttpRequestInterceptor));
    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
    restTemplate.setErrorHandler(new RestTemplateSpecificErrorHandler());
    return restTemplate;
  }
}
