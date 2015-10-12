package wearable.hotelbeds.shared.price;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import wearable.hotelbeds.shared.event.EventInfoBean;
import wearable.hotelbeds.shared.event.EventUtils;

/**
 * Created by Zavierazo on 08/10/2015.
 */
public class PriceUtils {
    public static final DateFormat DATE_FORMATER = new SimpleDateFormat("dd/MM/yyyy");
    public static final DateFormat DATE_FORMATER_HOUR = new SimpleDateFormat("dd/MM/yyyy hh:mm");

    public static List<PriceInfoBean> searchPrices(String eventId, Location location) {
        return searchPrices(EventUtils.searchEventById(eventId), location);
    }

    public static List<PriceInfoBean> searchPrices(EventInfoBean event, Location location) {
        List<PriceInfoBean> prices = new ArrayList<>();
        //TODO Si viene location null poner gps de evento hackaton :-)
        //Start Dummy
        try {
            prices.add(new PriceInfoBean(event, 1, new BigDecimal("653.5"), "Sunset Flower Palas", 4, DATE_FORMATER_HOUR.parse("14/10/2015 18:30"), DATE_FORMATER_HOUR.parse("17/10/2015 06:00"), "Double", "Rayanair", "Air Europa"));
            prices.add(new PriceInfoBean(event, 2, new BigDecimal("520"), "Supreme Haven Resort", 4, DATE_FORMATER_HOUR.parse("14/10/2015 14:20"), DATE_FORMATER_HOUR.parse("17/10/2015 08:10"), "Apartment", "Vueling", "Air Europa"));
            prices.add(new PriceInfoBean(event, 3, new BigDecimal("465.2"), "Bronze Bay Hotel", 3, DATE_FORMATER_HOUR.parse("14/10/2015 13:45"), DATE_FORMATER_HOUR.parse("17/10/2015 09:40"), "Single", "Air Europa", "Air Europa"));
            prices.add(new PriceInfoBean(event, 4, new BigDecimal("752.5"), "Baroque", 5, DATE_FORMATER_HOUR.parse("13/10/2015 20:00"), DATE_FORMATER_HOUR.parse("17/10/2015 07:45"), "Suite", "Air Berlin", "Vueling"));
            prices.add(new PriceInfoBean(event, 5, new BigDecimal("219.85"), "Crescent", 2, DATE_FORMATER_HOUR.parse("13/10/2015 18:30"), DATE_FORMATER_HOUR.parse("17/10/2015 05:20"), "Shared Room", "Vueling", "Air Europa"));
        } catch (Exception e) {
            Log.e("Traveler", "Error al rellenar dummy " + e.getMessage());
        }
        //End Dummy
        return prices;
    }

    public static ConfirmDataBean confirmBooking(Context context, PriceInfoBean price) {
        ConfirmDataBean confirmData;
        //TODO Make confirm
        confirmData = new ConfirmDataBean(true, "265182231566XS", price);//Dummy
        BookingsBean bookings = BookingsBean.load(context);
        bookings.getConfirmDataBeans().add(confirmData);
        bookings.save(context);
        return confirmData;
    }
}
