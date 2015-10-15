package test.backend.www.model.hotelbeds;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import test.backend.www.model.hotelbeds.basic.messages.StatusRS;
import test.backend.www.model.hotelbeds.sdk.HotelApiClient;
import test.backend.www.model.hotelbeds.sdk.helpers.AvailRoom;
import test.backend.www.model.hotelbeds.sdk.helpers.Availability;
import test.backend.www.model.hotelbeds.sdk.helpers.Availability.DelimitedShape;
import test.backend.www.model.hotelbeds.sdk.types.HotelSDKException;

@Data
@Slf4j
public class HotelbedsService
{
  public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

  private final HotelApiClient hotelApiClient;

  public HotelbedsService(String baseUrl, String key, String secret)
  {
    this.hotelApiClient = new HotelApiClient(baseUrl, key, secret);
    try
    {
      this.hotelApiClient.init();
      StatusRS statusRS = this.hotelApiClient.status();
      log.info("Hotelbeds API connected, status: {}", statusRS.getStatus());
    }
    catch (HotelSDKException e)
    {
      log.error("Error testing Hotelbeds API status", e);
    }

  }

  public Object getAvailability(String latitude, String longitude, LocalDate from, LocalDate to, long limitKm)
  {
    try
    {
      DelimitedShape withinThis = new Availability.Circle(longitude, latitude, limitKm);
      Availability availability = Availability
                                              .builder()
                                              .checkIn(from)
                                              .addRoom(AvailRoom.builder().adults(1))
                                              .checkOut(to)
                                              .withinThis(withinThis)
                                              .build();
      return hotelApiClient.availability(availability);
    }
    catch (HotelSDKException e)
    {
      log.error("Error getting availability", e);
      return e.getError();
    }
  }

}
