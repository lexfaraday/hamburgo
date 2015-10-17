package wearable.hotelbeds.shared.hotel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lexfaraday on 14/10/15.
 */
public class ProviderUtils {

    public static ArrayList<HotelInfo> generateHotels() {
        ArrayList<HotelInfo> hotels = new ArrayList<>();
        hotels.add(new HotelInfo("TrypBosq", "23.3", dummyHotelUrls(), 4, "All Included", "Double"));
        hotels.add(new HotelInfo("Hilton Luxury", "199", dummyHotelUrls(), 5, "All Included", "Suite"));
        hotels.add(new HotelInfo("Riu palace", "43", dummyHotelUrls(), 3, "Half Board", "Double"));
        hotels.add(new HotelInfo("Plaza resort", "56", dummyHotelUrls(), 4, "Half Board", "Apartment"));
        hotels.add(new HotelInfo("NH Hotel", "32", dummyHotelUrls(), 4, "Breakfast", "Individual"));
        return hotels;
    }

    private static List<String> dummyHotelUrls() {
        List<String> images = new ArrayList<>();
        images.add("http://www.omnihotels.com/-/media/images/hotels/ausctr/pool/ausctr-omni-austin-hotel-downtown-evening-pool.jpg");
        images.add("http://luxurycomm.com/wp-content/uploads/2015/07/vaciones-de-lujo-InterContinental.jpg");
        images.add("http://www.mayfairhotelandspa.com/_images/_design/headers/rooftop2.jpg");
        images.add("http://www.minihotelpms.com/wp-content/uploads/2013/01/hotel.jpg");
        return images;
    }
}
