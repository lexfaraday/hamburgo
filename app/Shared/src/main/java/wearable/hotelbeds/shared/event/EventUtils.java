package wearable.hotelbeds.shared.event;


import android.util.Log;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EventUtils {
    public static final DateFormat DATE_FORMATER = new SimpleDateFormat("dd/mm/yyyy");
    public static final DateFormat DATE_FORMATER_HOUR = new SimpleDateFormat("dd/mm/yyyy hh:mm");

    public static List<EventInfoBean> obtainAllEvent() {
        List<EventInfoBean> events = generateDummy();
        //TODO Rellenar con api eventos
        return events;
    }

    private static List<EventInfoBean> generateDummy() {
        List<EventInfoBean> events = new ArrayList<>();
        try {
            events.add(new EventInfoBean("Id1", "THack @ Hamburg travel hackathon", DATE_FORMATER_HOUR.parse("16/10/2015 18:00"), DATE_FORMATER_HOUR.parse("18/10/2015 20:00"), BigDecimal.ZERO));
            events.add(new EventInfoBean("Id2", "openDeck | Oktoberfest", DATE_FORMATER_HOUR.parse("14/10/2015 18:30"), DATE_FORMATER_HOUR.parse("14/10/2015 21:00"), new BigDecimal(20)));
            events.add(new EventInfoBean("Id3", "Sunset Booze Cruise/Boat Party Magaluf 2016", DATE_FORMATER_HOUR.parse("16/04/2016 16:30"), DATE_FORMATER_HOUR.parse("16/04/2016 21:30"), new BigDecimal(10)));
        } catch (ParseException e) {
            Log.e("Traveler", e.getMessage());
        }
        return events;
    }

    public static List<EventInfoBean> searchEventByName(String name) {
        List<EventInfoBean> events = obtainAllEvent();
        //TODO Make search (Si hay dos que coinciden se puede devolver lista)
        return events;
    }


}
