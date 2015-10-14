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
public class AvailRoom
{

  private final Integer adults;

  private final Integer children;

  @Singular
  private List<RoomDetail> details;

  public void validate()
  {

  }

  public static class AvailRoomBuilder
  {
    public AvailRoomBuilder detailed(GuestType type, int age, String name, String surname)
    {
      detail(new RoomDetail(type, age, name, surname));
      return this;
    }

    public AvailRoomBuilder adultOf(int age)
    {
      detail(new RoomDetail(GuestType.ADULT, age, null, null));
      return this;
    }

    public AvailRoomBuilder childOf(int age)
    {
      detail(new RoomDetail(age));
      return this;
    }
  }
}
