package wearable.hotelbeds.shared.event;


import android.util.Log;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EventUtils {
    public static final DateFormat DATE_FORMATER = new SimpleDateFormat("dd/MM/yyyy");
    public static final DateFormat DATE_FORMATER_HOUR = new SimpleDateFormat("dd/MM/yyyy hh:mm");

    public static ArrayList<EventInfoBean> obtainAllEvent() {
        ArrayList<EventInfoBean> events = generateDummy();
        //TODO Rellenar con api eventos
        return events;
    }

    private static ArrayList<EventInfoBean> generateDummy() {
        ArrayList<EventInfoBean> events = new ArrayList<>();
        try {
            String image = "https://img.evbuc.com/http%3A%2F%2Fcdn.evbuc.com%2Fimages%2F12769652%2F22216633386%2F1%2Foriginal.jpg?h=200&w=450&s=c4ffbeb3bdebd4835b6cd8ab108f391f";
            events.add(new EventInfoBean("Id1", "THack @ Hamburg travel hackathon", DATE_FORMATER_HOUR.parse("16/10/2015 18:00"), DATE_FORMATER_HOUR.parse("18/10/2015 20:00"), BigDecimal.ZERO, image));
            image = "https://img.evbuc.com/http%3A%2F%2Fcdn.evbuc.com%2Fimages%2F15423149%2F36867327350%2F1%2Foriginal.jpg?h=200&w=450&s=7c71ecacfa7c9a5e2f5df92fa4ed5c63";
            events.add(new EventInfoBean("Id2", "openDeck | Oktoberfest", DATE_FORMATER_HOUR.parse("14/10/2015 18:30"), DATE_FORMATER_HOUR.parse("14/10/2015 21:00"), new BigDecimal(20), image));
            image = "https://img.evbuc.com/http%3A%2F%2Fcdn.evbuc.com%2Fimages%2F13722825%2F525633860%2F1%2Foriginal.jpg?h=200&w=450&s=3b6439c770642f5083a474331b307745";
            events.add(new EventInfoBean("Id3", "Sunset Booze Cruise/Boat Party Magaluf 2016", DATE_FORMATER_HOUR.parse("16/04/2016 16:30"), DATE_FORMATER_HOUR.parse("16/04/2016 21:30"), new BigDecimal(10), image));
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

    public static EventInfoBean searchEventById(String id) {
        List<EventInfoBean> events = obtainAllEvent();
        //TODO Make search by id.
        for (EventInfoBean event : events) {
            if (event != null && event.getId().equals(id)) {
                return event;
            }
        }
        return events != null ? events.get(0) : null;
    }

}
