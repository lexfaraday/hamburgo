package wearable.hotelbeds.shared.price;

import android.util.Log;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zavierazo on 08/10/2015.
 */
public class PriceUtils {
    public static final DateFormat DATE_FORMATER = new SimpleDateFormat("dd/mm/yyyy");
    public static final DateFormat DATE_FORMATER_HOUR = new SimpleDateFormat("dd/mm/yyyy hh:mm");

    public static List<PriceInfoBean> searchPrices(String eventId) {
        List<PriceInfoBean> prices = new ArrayList<>();
        //Start Dummy
        try {
            prices.add(new PriceInfoBean(1, new BigDecimal("653.5"), "Sunset Flower Palas", 4, DATE_FORMATER_HOUR.parse("14/10/2015 18:30"), DATE_FORMATER_HOUR.parse("17/10/2015 06:00"), "Double", "Rayanair", "Air Europa"));
            prices.add(new PriceInfoBean(1, new BigDecimal("520"), "Supreme Haven Resort", 4, DATE_FORMATER_HOUR.parse("14/10/2015 14:20"), DATE_FORMATER_HOUR.parse("17/10/2015 08:10"), "Apartment", "Vueling", "Air Europa"));
            prices.add(new PriceInfoBean(1, new BigDecimal("465.2"), "Bronze Bay Hotel", 3, DATE_FORMATER_HOUR.parse("14/10/2015 13:45"), DATE_FORMATER_HOUR.parse("17/10/2015 09:40"), "Single", "Air Europa", "Air Europa"));
            prices.add(new PriceInfoBean(1, new BigDecimal("752.5"), "Baroque", 5, DATE_FORMATER_HOUR.parse("13/10/2015 20:00"), DATE_FORMATER_HOUR.parse("17/10/2015 07:45"), "Suite", "Air Berlin", "Vueling"));
            prices.add(new PriceInfoBean(1, new BigDecimal("219.85"), "Crescent", 2, DATE_FORMATER_HOUR.parse("13/10/2015 18:30"), DATE_FORMATER_HOUR.parse("17/10/2015 05:20"), "Shared Room", "Vueling", "Air Europa"));
        } catch (Exception e) {
            Log.e("Traveler", "Error al rellenar dummy " + e.getMessage());
        }
        //End Dummy
        return prices;
    }

    public static ConfirmDataBean confirmBooking(PriceInfoBean price) {
        ConfirmDataBean confirmData;
        //TODO Make confirm
        confirmData = new ConfirmDataBean("265182231566XS");//Dummy
        return confirmData;
    }
}