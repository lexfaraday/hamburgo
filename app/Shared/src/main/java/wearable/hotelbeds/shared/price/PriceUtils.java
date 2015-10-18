package wearable.hotelbeds.shared.price;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import org.json.JSONObject;

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
import wearable.hotelbeds.shared.visa.VisaBean;

/**
 * Created by Zavierazo on 08/10/2015.
 */
public class PriceUtils {
    public static final DateFormat DATE_FORMATER = new SimpleDateFormat("dd/MM/yyyy");
    public static final DateFormat DATE_FORMATER_HOUR = new SimpleDateFormat("dd/MM/yyyy hh:mm");
    public static final DateFormat FORMATER_HOUR = new SimpleDateFormat("hh:mm");

    public static List<PriceInfoBean> searchPrices(String eventId, Location location, int adultCount) {
        return searchPrices(EventUtils.searchEventById(eventId), location, adultCount);
    }

    public static List<PriceInfoBean> searchPrices(EventInfoBean event, Location location, int adultCount) {
        List<PriceInfoBean> prices = getFromJSONResponse(FAKE_JSON_RESP);
        //Start Dummy
        if (prices == null || prices.size() == 0) {

            try {
            List<HotelInfo> hotels = ProviderUtils.generateHotels();
            List<VisaBean> visas = generateVisaDummy();
            prices.add(new PriceInfoBean(1, new BigDecimal("653.5"), generateDummy(), generateDummy(), event, hotels.get(new Random().nextInt(hotels.size())), visas));
            prices.add(new PriceInfoBean(2, new BigDecimal("520"), generateDummy(), generateDummy(), event, hotels.get(new Random().nextInt(hotels.size())), visas));
            prices.add(new PriceInfoBean(3, new BigDecimal("465.2"), generateDummy(), generateDummy(), event, hotels.get(new Random().nextInt(hotels.size())), visas));
            prices.add(new PriceInfoBean(4, new BigDecimal("752.5"), generateDummy(), generateDummy(), event, hotels.get(new Random().nextInt(hotels.size())), visas));
            prices.add(new PriceInfoBean(5, new BigDecimal("219.85"), generateDummy(), generateDummy(), event, hotels.get(new Random().nextInt(hotels.size())), visas));
        } catch (Exception e) {
            Log.e("Traveler", "Error al rellenar dummy " + e.getMessage());
        }}
        //End Dummy
        return prices;
    }

    private static List<FlyBean> generateDummy() {
        List<FlyBean> flys = new ArrayList<>();
        Random rand = new Random();
        int random = rand.nextInt(2);
        if (random == 0) {
            try {
                flys.add(new FlyBean(DATE_FORMATER_HOUR.parse("14/10/2015 18:30"), DATE_FORMATER_HOUR.parse("14/10/2015 19:20"), "RYN", "PMI", "BCN", new BigDecimal("86.85")));
                flys.add(new FlyBean(DATE_FORMATER_HOUR.parse("14/10/2015 20:05"), DATE_FORMATER_HOUR.parse("14/10/2015 21:30"), "AEU", "BCN", "LON", new BigDecimal("86.85")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (random == 1) {
            try {
                flys.add(new FlyBean(DATE_FORMATER_HOUR.parse("14/10/2015 18:30"), DATE_FORMATER_HOUR.parse("14/10/2015 19:20"), "ABE", "PMI", "MAD", new BigDecimal("62")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            try {
                flys.add(new FlyBean(DATE_FORMATER_HOUR.parse("14/10/2015 06:30"), DATE_FORMATER_HOUR.parse("14/10/2015 08:20"), "Vueling", "LON", "PMI", new BigDecimal("92.5")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return flys;
    }

    public static List<VisaBean> generateVisaDummy() {
            List<VisaBean> visas = new ArrayList<>();

            for (int i = 0; i < 2; i++) {
                VisaBean visa = new VisaBean(true, "Frankfurt visa", "This visa is a new visa to protect our passengers, please don't forget to but it.!!!");
                visas.add(visa);
            }
            return visas;
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

    public static BookingsBean getBookings(Context context) {
        return BookingsBean.load(context);
    }

    public static List<ConfirmDataBean> searchEventByName(Context context, String name) {
        BookingsBean bookingBean = getBookings(context);
        List<ConfirmDataBean> bookings = bookingBean.getConfirmDataBeans();
        //TODO Make search (Si hay dos que coinciden se puede devolver lista)
        List<ConfirmDataBean> possible = new ArrayList<>();
        for (ConfirmDataBean book : bookings) {
            if (book.getToken() != null && book.getToken().contains(name)) {
                possible.add(book);
            }
            if (book.getPrice() != null && book.getPrice().getEvent() != null && book.getPrice().getEvent().getName() != null && book.getPrice().getEvent().getName().contains(name)) {
                possible.add(book);
            }
        }
        if (possible.size() > 0) {
            return possible;
        } else {
            return bookings;
        }
    }

    private static final String FAKE_JSON_RESP = "{\n" +
            "  \"flights\" : [ {\n" +
            "    \"flightNumber\" : \"5378\",\n" +
            "    \"airline\" : \"IB\",\n" +
            "    \"price\" : 85.85,\n" +
            "    \"currency\" : \"EUR\",\n" +
            "    \"departureAirport\" : \"FRA\",\n" +
            "    \"arrivalAirport\" : \"BCN\",\n" +
            "    \"arrival\" : \"2015-11-10T09:15:00.000+0000\",\n" +
            "    \"departure\" : \"2015-11-10T11:20:00.000+0000\"\n" +
            "  }, {\n" +
            "    \"flightNumber\" : \"1124\",\n" +
            "    \"airline\" : \"Kronos Air\",\n" +
            "    \"price\" : 1234.16,\n" +
            "    \"currency\" : \"EUR\",\n" +
            "    \"departureAirport\" : \"FRA\",\n" +
            "    \"arrivalAirport\" : \"BCN\",\n" +
            "    \"arrival\" : \"2015-11-09T23:00:00.000+0000\",\n" +
            "    \"departure\" : \"2015-11-09T23:00:00.000+0000\"\n" +
            "  } ],\n" +
            "  \"hoteldata\" : {\n" +
            "    \"name\" : \"Ramblanova\",\n" +
            "    \"amount\" : \"154.000\",\n" +
            "    \"currency\" : \"EUR\",\n" +
            "    \"checkin\" : \"2015-11-10\",\n" +
            "    \"checkout\" : \"2015-11-12\",\n" +
            "    \"category\" : \"APARTMENT 1ST CATEGORY\"\n" +
            "  },\n" +
            "  \"event\" : {\n" +
            "    \"title\" : \"November Happy Hour\",\n" +
            "    \"description\" : \" \\nNovember Happy Hour  \\nWednesday, November 11, 2015, 20:00, Hilton Barcelona Hotel, Avinguda Diagonal, 589-591   \\nAre you interested in networking in the English language community? Would you like to meet the ASB Board, find out what's going on in the English speaking community or get the latest information on all the new events the ASB is planning? Then come to our monthly Happy Hour. All are welcome! \\nASB Members: FREE ASB Friends: FREE \\nRegister to Win  \\nWe'll have anther door prize this month and all you have to do to be eligible to win is: \\n1) Register for the event by 16:00, November 11th and your name will automatically be added to the drawing. \\n2) Show up - because if your name is drawn and you're not there, then you can't win!  \\nWin a Prize! \\nHow? Just register & show up. Yep, it's that easy. \\nWe look forward to seeing you there! \\n  \\nThe American Society of Barcelona www.amersoc.com \\ntel: 93.368.4689  \\n     \\n__________________________________________________________________________  \\nProud Partners with: \\n \\n  \\n  \\n  \\n  \\n   \\n  \\n  \\n\",\n" +
            "    \"id\" : \"17722113318\",\n" +
            "    \"startDate\" : \"14/10/2015 18:30\",\n" +
            "    \"endDate\" : \"14/10/2015 18:30\",\n" +
            "    \"capacity\" : \"150\",\n" +
            "    \"currency\" : \"USD\",\n" +
            "    \"image\" : \"https://img.evbuc.com/http%3A%2F%2Fcdn.evbuc.com%2Fimages%2F14263633%2F24084179334%2F1%2Foriginal.jpg?h=200&w=450&s=c7624b4fd35027e69fd9ebc3ec22809c\",\n" +
            "    \"venue\" : {\n" +
            "      \"name\" : \"Hilton Barcelona Hotel\",\n" +
            "      \"latitude\" : \"41.3888162\",\n" +
            "      \"longitude\" : \"2.13166590000003\"\n" +
            "    }\n" +
            "  }\n" +
            "}";

    private static List<PriceInfoBean> getFromJSONResponse(String resp) {
        List<PriceInfoBean> prices = new ArrayList<>();
        try {
            PriceInfoBean price = new PriceInfoBean();
            JSONObject json = new JSONObject(resp);
            JSONObject eventObject = json.getJSONObject("event");
            EventInfoBean event = new EventInfoBean();
            event.setId(eventObject.getString("id"));
            event.setImageUrl(eventObject.getString("image"));
            event.setName(eventObject.getString("title"));
            event.setPrice(new BigDecimal(eventObject.getString("price")));
            event.setShortDescription(eventObject.getString("description"));
            event.setTimeStart(DATE_FORMATER_HOUR.parse(eventObject.getString("startDate")));
            event.setTimeEnd(DATE_FORMATER_HOUR.parse(eventObject.getString("endDate")));
            price.setEvent(event);
            prices.add(price);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prices;
    }


}
