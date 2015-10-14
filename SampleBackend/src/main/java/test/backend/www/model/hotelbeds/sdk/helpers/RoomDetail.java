package test.backend.www.model.hotelbeds.sdk.helpers;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoomDetail {

    public enum GuestType {
        ADULT,
        CHILD
    }

    private final GuestType type;
    private final int age;
    private final String name;
    private final String surname;

    public RoomDetail(int age) {
        type = GuestType.CHILD;
        name = null;
        surname = null;
        this.age = age;
    }

}
