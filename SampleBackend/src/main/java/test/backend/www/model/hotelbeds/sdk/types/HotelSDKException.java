package test.backend.www.model.hotelbeds.sdk.types;

import lombok.Data;
import lombok.EqualsAndHashCode;
import test.backend.www.model.hotelbeds.basic.messages.HotelbedsError;

@Data
@EqualsAndHashCode(callSuper = false)
public class HotelSDKException extends Exception {

    public static final long serialVersionUID = 1L;

    private final HotelbedsError error;

    public HotelSDKException(HotelbedsError error) {
        super();
        this.error = error;
    }

    public HotelSDKException(HotelbedsError error, Throwable throwable) {
        super(throwable);
        this.error = error;
    }


}
