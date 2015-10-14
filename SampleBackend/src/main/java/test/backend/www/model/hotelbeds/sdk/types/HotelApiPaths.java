package test.backend.www.model.hotelbeds.sdk.types;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.springframework.http.HttpMethod;

import test.backend.www.model.hotelbeds.basic.messages.AbstractGenericResponse;
import test.backend.www.model.hotelbeds.basic.messages.AvailabilityRS;
import test.backend.www.model.hotelbeds.basic.messages.BookingCancellationRS;
import test.backend.www.model.hotelbeds.basic.messages.BookingDetailRS;
import test.backend.www.model.hotelbeds.basic.messages.BookingListRS;
import test.backend.www.model.hotelbeds.basic.messages.BookingRS;
import test.backend.www.model.hotelbeds.basic.messages.CheckRateRS;
import test.backend.www.model.hotelbeds.basic.messages.StatusRS;

/**
 * Copyright (c) Hotelbeds Technology S.L.U. All rights reserved.
 */
public enum HotelApiPaths
{

  AVAILABILITY("${path}/hotels", HttpMethod.POST, AvailabilityRS.class), BOOKING_LIST(
      "${path}/bookings?from=${from}&to=${to}&includeCancelled=${includeCancelled}&filterType=${filterType}", HttpMethod.GET,
      BookingListRS.class), BOOKING_DETAIL("${path}/bookings/${bookingId}", HttpMethod.GET, BookingDetailRS.class), BOOKING_CONFIRM(
          "${path}/bookings", HttpMethod.POST, BookingRS.class), BOOKING_CANCEL("${path}/bookings/${bookingId}?cancellationFlag=${flag}",
              HttpMethod.DELETE, BookingCancellationRS.class), CHECK_AVAIL("${path}/checkrates", HttpMethod.POST,
                  CheckRateRS.class), STATUS("${path}/status", HttpMethod.GET, StatusRS.class),;

  private final String urlTemplate;
  private final HttpMethod httpMethod;
  private final Class<? extends AbstractGenericResponse> responseClass;

  HotelApiPaths(final String urlTemplate, final HttpMethod httpMethod, Class<? extends AbstractGenericResponse> responseClass)
  {
    this.urlTemplate = urlTemplate;
    this.httpMethod = httpMethod;
    this.responseClass = responseClass;
  }

  public String getUrlTemplate()
  {
    return urlTemplate;
  }

  public Class<? extends AbstractGenericResponse> getResponseClass()
  {
    return responseClass;
  }

  public String getUrl(String basePath)
  {
    return getUrl(basePath, null);
  }

  public String getUrl(String basePath, Map<String, String> params)
  {
    if (params == null)
    {
      params = new HashMap<>();
    }
    params.put("path", basePath);
    StrSubstitutor strSubstitutor = new StrSubstitutor(params);
    return strSubstitutor.replace(urlTemplate);
  }

  public HttpMethod getHttpMethod()
  {
    return httpMethod;
  }
}
