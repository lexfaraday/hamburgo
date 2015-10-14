package test.backend.www.model.hotelbeds.sdk.helpers;

import java.util.List;

import lombok.Builder;
import lombok.Singular;
import lombok.ToString;
import lombok.Value;
import test.backend.www.model.hotelbeds.sdk.helpers.RoomDetail.GuestType;

@Builder
@Value
@ToString
public class ConfirmRoom {

    private final String ratekey;

    @Singular
    private List<RoomDetail> details;

    public void validate() {

    }

    public static class ConfirmRoomBuilder {
        public ConfirmRoomBuilder detailed(GuestType type, int age, String name, String surname) {
            detail(new RoomDetail(type, age, name, surname));
            return this;
        }

        public ConfirmRoomBuilder adultOf(int age) {
            detail(new RoomDetail(GuestType.ADULT, age, null, null));
            return this;
        }

        public ConfirmRoomBuilder childOf(int age) {
            detail(new RoomDetail(age));
            return this;
        }
    }
}
