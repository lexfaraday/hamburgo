package wearable.hotelbeds.shared.hotel;

import android.util.Log;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import wearable.hotelbeds.shared.event.EventInfoBean;

/**
 * Created by lexfaraday on 14/10/15.
 */
public class ProviderUtils {

    public static ArrayList<HotelInfo> generateHotels() {
        ArrayList<HotelInfo> hotels = new ArrayList<>();

        List<String> images = new ArrayList<>();
        images.add("http://www.omnihotels.com/-/media/images/hotels/ausctr/pool/ausctr-omni-austin-hotel-downtown-evening-pool.jpg");

        hotels.add(new HotelInfo("TrypBosq", "23.3", images));
        hotels.add(new HotelInfo("Hilton Luxury","199", images));
        hotels.add(new HotelInfo("Riu palace","43", images));
        hotels.add(new HotelInfo("Plaza resort","56", images));
        hotels.add(new HotelInfo("NH Hotel","32", images));

        return hotels;
    }
}
