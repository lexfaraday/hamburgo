package test.backend.www.model.hotelbeds.sdk.helpers;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Singular;
import lombok.ToString;
import lombok.Value;
import test.backend.www.model.hotelbeds.basic.common.SimpleTypes.HotelbedsCustomerType;
import test.backend.www.model.hotelbeds.basic.messages.CheckRateRQ;
import test.backend.www.model.hotelbeds.basic.model.BookingRoom;
import test.backend.www.model.hotelbeds.basic.model.Pax;
import test.backend.www.model.hotelbeds.sdk.helpers.ConfirmRoom.ConfirmRoomBuilder;
import test.backend.www.model.hotelbeds.sdk.helpers.RoomDetail.GuestType;

@Builder
@Value
@ToString
public class BookingCheck {

    @Singular
    private List<ConfirmRoom> rooms;

    public void validate() {

    }

    public CheckRateRQ toCheckRateRQ() {
        validate();
        CheckRateRQ checkRateRQ = new CheckRateRQ();
        //
        checkRateRQ.setRooms(new ArrayList<>());
        for (ConfirmRoom room : rooms) {
            BookingRoom bookingRoom = new BookingRoom();
            bookingRoom.setRateKey(room.getRatekey());
            bookingRoom.setPaxes(new ArrayList<>());
            for (RoomDetail detail : room.getDetails()) {
                Pax pax = new Pax();
                pax.setType(detail.getType() == GuestType.ADULT ? HotelbedsCustomerType.AD : HotelbedsCustomerType.CH);
                pax.setAge(detail.getAge());
                pax.setName(detail.getName());
                pax.setSurname(detail.getSurname());
                bookingRoom.getPaxes().add(pax);
            }
            checkRateRQ.getRooms().add(bookingRoom);
        }
        return checkRateRQ;
    }

    public static class BookingCheckBuilder {

        public BookingCheckBuilder addRoom(String ratekey, ConfirmRoomBuilder roomBuilder) {
            room(roomBuilder.ratekey(ratekey).build());
            return this;
        }
    }
}
