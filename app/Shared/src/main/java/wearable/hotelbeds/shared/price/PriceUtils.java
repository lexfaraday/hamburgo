package wearable.hotelbeds.shared.price;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import wearable.hotelbeds.shared.event.EventInfoBean;
import wearable.hotelbeds.shared.event.EventUtils;
import wearable.hotelbeds.shared.hotel.HotelInfo;
import wearable.hotelbeds.shared.hotel.ProviderUtils;

/**
 * Created by Zavierazo on 08/10/2015.
 */
public class PriceUtils {
    public static final DateFormat DATE_FORMATER = new SimpleDateFormat("dd/MM/yyyy");
    public static final DateFormat DATE_FORMATER_HOUR = new SimpleDateFormat("dd/MM/yyyy hh:mm");
    public static final DateFormat FORMATER_HOUR = new SimpleDateFormat("hh:mm");

    public static List<PriceInfoBean> searchPrices(String eventId, Location location) {
        return searchPrices(EventUtils.searchEventById(eventId), location);
    }

    public static List<PriceInfoBean> searchPrices(EventInfoBean event, Location location) {
        List<PriceInfoBean> prices = new ArrayList<>();
        //TODO Si viene location null poner gps de evento hackaton :-)
        //Start Dummy
        try {
            List<HotelInfo> hotels = ProviderUtils.generateHotels();
            prices.add(new PriceInfoBean(1, new BigDecimal("653.5"), generateDummy(), generateDummy(), event, hotels.get(new Random().nextInt(hotels.size()))));
            prices.add(new PriceInfoBean(2, new BigDecimal("520"), generateDummy(), generateDummy(), event, hotels.get(new Random().nextInt(hotels.size()))));
            prices.add(new PriceInfoBean(3, new BigDecimal("465.2"), generateDummy(), generateDummy(), event, hotels.get(new Random().nextInt(hotels.size()))));
            prices.add(new PriceInfoBean(4, new BigDecimal("752.5"), generateDummy(), generateDummy(), event, hotels.get(new Random().nextInt(hotels.size()))));
            prices.add(new PriceInfoBean(5, new BigDecimal("219.85"), generateDummy(), generateDummy(), event, hotels.get(new Random().nextInt(hotels.size()))));
        } catch (Exception e) {
            Log.e("Traveler", "Error al rellenar dummy " + e.getMessage());
        }
        //End Dummy
        return prices;
    }

    private static List<FlyBean> generateDummy() {
        List<FlyBean> flys = new ArrayList<>();
        Random rand = new Random();
        int random = rand.nextInt(2);
        if (random == 0) {
            try {
                flys.add(new FlyBean(DATE_FORMATER_HOUR.parse("14/10/2015 18:30"), DATE_FORMATER_HOUR.parse("14/10/2015 19:20"), "RYN", "PMI", "BCN"));
                flys.add(new FlyBean(DATE_FORMATER_HOUR.parse("14/10/2015 20:05"), DATE_FORMATER_HOUR.parse("14/10/2015 21:30"), "AEU", "BCN", "LON"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (random == 1) {
            try {
                flys.add(new FlyBean(DATE_FORMATER_HOUR.parse("14/10/2015 18:30"), DATE_FORMATER_HOUR.parse("14/10/2015 19:20"), "ABE", "PMI", "MAD"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            try {
                flys.add(new FlyBean(DATE_FORMATER_HOUR.parse("14/10/2015 06:30"), DATE_FORMATER_HOUR.parse("14/10/2015 08:20"), "Vueling", "LON", "PMI"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return flys;
    }

    public static ConfirmDataBean confirmBooking(Context context, PriceInfoBean price) {
        ConfirmDataBean confirmData;
        //TODO Make confirm
        confirmData = new ConfirmDataBean(true, "265182231566XS", price);//Dummy
        BookingsBean bookings = BookingsBean.load(context);
        if (!bookings.containsBooking(confirmData)) {
            bookings.getConfirmDataBeans().add(confirmData);
            bookings.save(context);
        }
        return confirmData;
    }


}
