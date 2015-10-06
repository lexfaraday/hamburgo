package wearable.hotelbeds.shared.event;


import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EventUtils {
    public static final DateFormat DATE_FORMATER = new SimpleDateFormat("dd/mm/yyyy");

    public static List<EventInfo> obtainAllEvent() {
        List<EventInfo> events = generateDummy();
        //TODO Rellenar con api eventos
        return events;
    }

    private static List<EventInfo> generateDummy() {
        List<EventInfo> events = new ArrayList<>();
        try {
            events.add(new EventInfo("THack @ Hamburg travel hackathon", DATE_FORMATER.parse("16/10/2015"), DATE_FORMATER.parse("18/10/2015")));
        } catch (ParseException e) {
            Log.e("Traveler", e.getMessage());
        }
        return events;
    }

    public static List<EventInfo> searchEventByName(String name) {
        List<EventInfo> events = obtainAllEvent();
        //TODO Make search (Si hay dos que coinciden se puede devolver lista)
        return events;
    }


}
