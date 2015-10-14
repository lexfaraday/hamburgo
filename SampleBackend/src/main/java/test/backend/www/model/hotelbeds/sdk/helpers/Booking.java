package test.backend.www.model.hotelbeds.sdk.helpers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;

import lombok.Builder;
import lombok.Singular;
import lombok.ToString;
import lombok.Value;
import test.backend.www.model.hotelbeds.basic.common.SimpleTypes.HotelbedsCustomerType;
import test.backend.www.model.hotelbeds.basic.messages.BookingRQ;
import test.backend.www.model.hotelbeds.basic.model.BookingRoom;
import test.backend.www.model.hotelbeds.basic.model.Holder;
import test.backend.www.model.hotelbeds.basic.model.Pax;
import test.backend.www.model.hotelbeds.basic.model.PaymentCard;
import test.backend.www.model.hotelbeds.basic.model.PaymentContactData;
import test.backend.www.model.hotelbeds.basic.model.PaymentData;
import test.backend.www.model.hotelbeds.sdk.helpers.ConfirmRoom.ConfirmRoomBuilder;
import test.backend.www.model.hotelbeds.sdk.helpers.RoomDetail.GuestType;

@Builder
@Value
@ToString
public class Booking
{

  @NotNull
  private Holder holder;

  @NotNull
  @Size(min = 1, max = 20)
  private String clientReference;

  @Size(max = 2000)
  private String remark;

  private String cardType;
  private String cardNumber;
  private String cardHolderName;
  private String expiryDate;
  private String cardCVC;
  private String email;
  private String phoneNumber;

  @Singular
  private List<ConfirmRoom> rooms;

  public void validate()
  {

  }

  public BookingRQ toBookingRQ()
  {
    validate();
    BookingRQ bookingRQ = new BookingRQ();
    //
    bookingRQ.setHolder(holder);
    //
    bookingRQ.setClientReference(clientReference);
    //
    bookingRQ.setRemark(remark);
    //
    if (StringUtils.isNoneBlank(cardType, cardNumber, cardHolderName, expiryDate, cardCVC) || StringUtils.isNoneBlank(email, phoneNumber))
    {
      PaymentData paymentData = new PaymentData();
      if (StringUtils.isNoneBlank(cardType, cardNumber, cardHolderName, expiryDate, cardCVC))
      {
        paymentData.setPaymentCard(new PaymentCard(cardType, cardNumber, cardHolderName, expiryDate, cardCVC));
      }
      if (StringUtils.isNoneBlank(email, phoneNumber))
      {
        paymentData.setContactData(new PaymentContactData(email, phoneNumber));
      }
      bookingRQ.setPaymentData(paymentData);
    }
    //
    bookingRQ.setRooms(new ArrayList<>());
    for (ConfirmRoom room : rooms)
    {
      BookingRoom bookingRoom = new BookingRoom();
      bookingRoom.setRateKey(room.getRatekey());
      bookingRoom.setPaxes(new ArrayList<>());
      for (RoomDetail detail : room.getDetails())
      {
        Pax pax = new Pax();
        pax.setType(detail.getType() == GuestType.ADULT ? HotelbedsCustomerType.AD : HotelbedsCustomerType.CH);
        pax.setAge(detail.getAge());
        pax.setName(detail.getName());
        pax.setSurname(detail.getSurname());
        bookingRoom.getPaxes().add(pax);
      }
      bookingRQ.getRooms().add(bookingRoom);
    }
    return bookingRQ;
  }

  public static class BookingBuilder
  {

    public BookingBuilder withHolder(String name, String surname)
    {
      Holder holder = new Holder();
      holder.setName(name);
      holder.setSurname(surname);
      holder(holder);
      return this;
    }

    public BookingBuilder addRoom(String ratekey, ConfirmRoomBuilder roomBuilder)
    {
      room(roomBuilder.ratekey(ratekey).build());
      return this;
    }
  }
}
