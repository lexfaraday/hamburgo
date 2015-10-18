package wearable.hotelbeds.shared.price;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import org.json.JSONArray;
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

/**
 * Created by Zavierazo on 08/10/2015.
 */
public class PriceUtils {
    public static final DateFormat DATE_FORMATER = new SimpleDateFormat("dd/MM/yyyy");
    public static final DateFormat DATE_FORMATER_HOUR = new SimpleDateFormat("dd/MM/yyyy hh:mm");
    public static final DateFormat FORMATER_JSON = new SimpleDateFormat("yyyy-MM-dd hh:mm");
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
                prices.add(new PriceInfoBean(1, new BigDecimal("653.5"), generateDummy(), generateDummy(), event, hotels.get(new Random().nextInt(hotels.size()))));
                prices.add(new PriceInfoBean(2, new BigDecimal("520"), generateDummy(), generateDummy(), event, hotels.get(new Random().nextInt(hotels.size()))));
                prices.add(new PriceInfoBean(3, new BigDecimal("465.2"), generateDummy(), generateDummy(), event, hotels.get(new Random().nextInt(hotels.size()))));
                prices.add(new PriceInfoBean(4, new BigDecimal("752.5"), generateDummy(), generateDummy(), event, hotels.get(new Random().nextInt(hotels.size()))));
                prices.add(new PriceInfoBean(5, new BigDecimal("219.85"), generateDummy(), generateDummy(), event, hotels.get(new Random().nextInt(hotels.size()))));
            } catch (Exception e) {
                Log.e("Traveler", "Error al rellenar dummy " + e.getMessage());
            }
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
                flys.add(new FlyBean(DATE_FORMATER_HOUR.parse("14/10/2015 18:30"), DATE_FORMATER_HOUR.parse("14/10/2015 19:20"), "RYN", "PMI", "BCN", new BigDecimal("86.85"), "4223FDS"));
                flys.add(new FlyBean(DATE_FORMATER_HOUR.parse("14/10/2015 20:05"), DATE_FORMATER_HOUR.parse("14/10/2015 21:30"), "AEU", "BCN", "LON", new BigDecimal("86.85"), "4223FDS"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (random == 1) {
            try {
                flys.add(new FlyBean(DATE_FORMATER_HOUR.parse("14/10/2015 18:30"), DATE_FORMATER_HOUR.parse("14/10/2015 19:20"), "ABE", "PMI", "MAD", new BigDecimal("62"), "4223FDS"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            try {
                flys.add(new FlyBean(DATE_FORMATER_HOUR.parse("14/10/2015 06:30"), DATE_FORMATER_HOUR.parse("14/10/2015 08:20"), "Vueling", "LON", "PMI", new BigDecimal("92.5"), "4223FDS"));
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
            "    \"going\" : {\n" +
            "      \"flightNumber\" : \"5378\",\n" +
            "      \"airline\" : \"IB\",\n" +
            "      \"departureAirport\" : \"FRA\",\n" +
            "      \"arrivalAirport\" : \"BCN\",\n" +
            "      \"arrival\" : \"2015-11-10 09:15\",\n" +
            "      \"departure\" : \"2015-11-10 11:20\",\n" +
            "      \"price\" : null,\n" +
            "      \"currency\" : null\n" +
            "    },\n" +
            "    \"coming\" : {\n" +
            "      \"flightNumber\" : \"5380\",\n" +
            "      \"airline\" : \"IB\",\n" +
            "      \"departureAirport\" : \"BCN\",\n" +
            "      \"arrivalAirport\" : \"FRA\",\n" +
            "      \"arrival\" : \"2015-11-12 16:10\",\n" +
            "      \"departure\" : \"2015-11-12 18:35\",\n" +
            "      \"price\" : null,\n" +
            "      \"currency\" : null\n" +
            "    },\n" +
            "    \"price\" : 85.85,\n" +
            "    \"currency\" : \"EUR\"\n" +
            "  }, {\n" +
            "    \"going\" : {\n" +
            "      \"flightNumber\" : \"1124\",\n" +
            "      \"airline\" : \"Kronos Air\",\n" +
            "      \"departureAirport\" : \"FRA\",\n" +
            "      \"arrivalAirport\" : \"BCN\",\n" +
            "      \"arrival\" : \"2015-11-09 23:00\",\n" +
            "      \"departure\" : \"2015-11-09 23:00\",\n" +
            "      \"price\" : 1234.16,\n" +
            "      \"currency\" : \"EUR\"\n" +
            "    },\n" +
            "    \"coming\" : {\n" +
            "      \"flightNumber\" : \"1125\",\n" +
            "      \"airline\" : \"Kronos Air\",\n" +
            "      \"departureAirport\" : \"BCN\",\n" +
            "      \"arrivalAirport\" : \"FRA\",\n" +
            "      \"arrival\" : \"2015-11-11 23:00\",\n" +
            "      \"departure\" : \"2015-11-11 23:00\",\n" +
            "      \"price\" : 1103.16,\n" +
            "      \"currency\" : \"EUR\"\n" +
            "    },\n" +
            "    \"price\" : 2337.32,\n" +
            "    \"currency\" : \"EUR\"\n" +
            "  } ],\n" +
            "  \"hotelDatas\" : [ {\n" +
            "    \"name\" : \"Ramblanova\",\n" +
            "    \"amount\" : \"154.000\",\n" +
            "    \"currency\" : \"EUR\",\n" +
            "    \"checkin\" : \"2015-11-10\",\n" +
            "    \"checkout\" : \"2015-11-12\",\n" +
            "    \"category\" : \"APARTMENT 1ST CATEGORY\"\n" +
            "  }, {\n" +
            "    \"name\" : \"Holiday Inn Express Bcn 22@\",\n" +
            "    \"amount\" : \"123.080\",\n" +
            "    \"currency\" : \"EUR\",\n" +
            "    \"checkin\" : \"2015-11-10\",\n" +
            "    \"checkout\" : \"2015-11-12\",\n" +
            "    \"category\" : \"3 STARS\"\n" +
            "  } ],\n" +
            "  \"event\" : {\n" +
            "    \"title\" : \"November Happy Hour\",\n" +
            "    \"description\" : \" \\nNovember Happy Hour  \\nWednesday, November 11, 2015, 20:00, Hilton Barcelona Hotel, Avinguda Diagonal, 589-591   \\nAre you interested in networking in the English language community? Would you like to meet the ASB Board, find out what's going on in the English speaking community or get the latest information on all the new events the ASB is planning? Then come to our monthly Happy Hour. All are welcome! \\nASB Members: FREE ASB Friends: FREE \\nRegister to Win  \\nWe'll have anther door prize this month and all you have to do to be eligible to win is: \\n1) Register for the event by 16:00, November 11th and your name will automatically be added to the drawing. \\n2) Show up - because if your name is drawn and you're not there, then you can't win!  \\nWin a Prize! \\nHow? Just register & show up. Yep, it's that easy. \\nWe look forward to seeing you there! \\n  \\nThe American Society of Barcelona www.amersoc.com \\ntel: 93.368.4689  \\n     \\n__________________________________________________________________________  \\nProud Partners with: \\n \\n  \\n  \\n  \\n  \\n   \\n  \\n  \\n\",\n" +
            "    \"id\" : \"17722113318\",\n" +
            "    \"startDate\" : \"2015-11-11 19:00\",\n" +
            "    \"endDate\" : \"2015-11-11 22:30\",\n" +
            "    \"capacity\" : \"150\",\n" +
            "    \"price\" : \"free\",\n" +
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
        List<HotelInfo> images = ProviderUtils.generateHotels();
        try {

            JSONObject json = new JSONObject(resp);
            JSONObject eventObject = json.getJSONObject("event");
            EventInfoBean event = new EventInfoBean();
            event.setId(eventObject.getString("id"));
            event.setImageUrl(eventObject.getString("image"));
            event.setName(eventObject.getString("title"));
            if (eventObject.getString("price").equalsIgnoreCase("free")) {
                event.setPrice(BigDecimal.ZERO);
            } else {
                event.setPrice(new BigDecimal(eventObject.getString("price")));
            }
            event.setShortDescription(eventObject.getString("description"));
            event.setTimeStart(FORMATER_JSON.parse(eventObject.getString("startDate")));
            event.setTimeEnd(FORMATER_JSON.parse(eventObject.getString("endDate")));
            JSONArray hotelsArray = json.getJSONArray("hotelDatas");
            JSONObject hotel1Object = hotelsArray.getJSONObject(0);
            HotelInfo hotelInfo1 = new HotelInfo();
            hotelInfo1.setImages(images.get(new Random().nextInt(images.size())).getImages());
            hotelInfo1.setName(hotel1Object.getString("name"));
            hotelInfo1.setPrice(new BigDecimal(hotel1Object.getString("amount")));
            hotelInfo1.setCodHab(images.get(new Random().nextInt(images.size())).getCodHab());
            hotelInfo1.setStars(images.get(new Random().nextInt(images.size())).getStars());
            hotelInfo1.setReg(images.get(new Random().nextInt(images.size())).getReg());


            JSONObject hotel2Object = hotelsArray.getJSONObject(1);
            HotelInfo hotelInfo2 = new HotelInfo();
            hotelInfo2.setImages(images.get(new Random().nextInt(images.size())).getImages());
            hotelInfo2.setName(hotel2Object.getString("name"));
            hotelInfo2.setPrice(new BigDecimal(hotel2Object.getString("amount")));
            hotelInfo2.setCodHab(images.get(new Random().nextInt(images.size())).getCodHab());
            hotelInfo2.setStars(images.get(new Random().nextInt(images.size())).getStars());
            hotelInfo2.setReg(images.get(new Random().nextInt(images.size())).getReg());

            JSONArray flysArray = json.getJSONArray("flights");
            JSONObject fly1Array = flysArray.getJSONObject(0);
            JSONObject fly1DepartureObject = fly1Array.getJSONObject("going");
            FlyBean flyDeparture1 = new FlyBean();
            flyDeparture1.setAmount(new BigDecimal(fly1Array.getString("price")));
            flyDeparture1.setArrival(FORMATER_JSON.parse(fly1DepartureObject.getString("arrival")));
            flyDeparture1.setDeparture(FORMATER_JSON.parse(fly1DepartureObject.getString("departure")));
            flyDeparture1.setArrivalAirport(fly1DepartureObject.getString("arrivalAirport"));
            flyDeparture1.setDepartureAirport(fly1DepartureObject.getString("departureAirport"));
            flyDeparture1.setCompany(fly1DepartureObject.getString("airline"));
            flyDeparture1.setFlightNumber(fly1DepartureObject.getString("flightNumber"));
            JSONObject fly1ArrivalObject = fly1Array.getJSONObject("coming");
            FlyBean flyArrival1 = new FlyBean();
            flyArrival1.setAmount(new BigDecimal(fly1Array.getString("price")));
            flyArrival1.setArrival(FORMATER_JSON.parse(fly1ArrivalObject.getString("arrival")));
            flyArrival1.setDeparture(FORMATER_JSON.parse(fly1ArrivalObject.getString("departure")));
            flyArrival1.setArrivalAirport(fly1ArrivalObject.getString("arrivalAirport"));
            flyArrival1.setDepartureAirport(fly1ArrivalObject.getString("departureAirport"));
            flyArrival1.setCompany(fly1ArrivalObject.getString("airline"));
            flyArrival1.setFlightNumber(fly1ArrivalObject.getString("flightNumber"));


            JSONObject fly2Array = flysArray.getJSONObject(0);
            JSONObject fly2DepartureObject = fly2Array.getJSONObject("going");
            FlyBean flyDeparture2 = new FlyBean();
            flyDeparture2.setAmount(new BigDecimal(fly2Array.getString("price")));
            flyDeparture2.setArrival(FORMATER_JSON.parse(fly2DepartureObject.getString("arrival")));
            flyDeparture2.setDeparture(FORMATER_JSON.parse(fly2DepartureObject.getString("departure")));
            flyDeparture2.setArrivalAirport(fly2DepartureObject.getString("arrivalAirport"));
            flyDeparture2.setDepartureAirport(fly2DepartureObject.getString("departureAirport"));
            flyDeparture2.setCompany(fly2DepartureObject.getString("airline"));
            flyDeparture2.setFlightNumber(fly2DepartureObject.getString("flightNumber"));
            JSONObject fly2ArrivalObject = fly2Array.getJSONObject("coming");
            FlyBean flyArrival2 = new FlyBean();
            flyArrival2.setAmount(new BigDecimal(fly2Array.getString("price")));
            flyArrival2.setArrival(FORMATER_JSON.parse(fly2ArrivalObject.getString("arrival")));
            flyArrival2.setDeparture(FORMATER_JSON.parse(fly2ArrivalObject.getString("departure")));
            flyArrival2.setArrivalAirport(fly2ArrivalObject.getString("arrivalAirport"));
            flyArrival2.setDepartureAirport(fly2ArrivalObject.getString("departureAirport"));
            flyArrival2.setCompany(fly2ArrivalObject.getString("airline"));
            flyArrival2.setFlightNumber(fly2ArrivalObject.getString("flightNumber"));


            //Precio 1
            PriceInfoBean price1 = new PriceInfoBean();
            price1.setEvent(event);
            price1.setId(1);
            price1.setHotelInfo(hotelInfo1);
            List<FlyBean> departure = new ArrayList<FlyBean>();
            departure.add(flyDeparture1);
            price1.setFlyDeparture(departure);
            List<FlyBean> arrival = new ArrayList<FlyBean>();
            arrival.add(flyArrival1);
            price1.setFlyArrival(arrival);
            price1.setTotalAmount(event.getPrice().add(hotelInfo1.getPrice()).add(flyDeparture1.getAmount()).add(flyArrival1.getAmount()));
            prices.add(price1);

            //Precio 2
            PriceInfoBean price2 = new PriceInfoBean();
            price2.setEvent(event);
            price2.setId(2);
            price2.setHotelInfo(hotelInfo1);
            List<FlyBean> departure2 = new ArrayList<FlyBean>();
            departure2.add(flyDeparture2);
            price2.setFlyDeparture(departure2);
            List<FlyBean> arrival2 = new ArrayList<FlyBean>();
            arrival2.add(flyArrival2);
            price2.setFlyArrival(arrival2);
            price2.setTotalAmount(event.getPrice().add(hotelInfo1.getPrice()).add(flyDeparture2.getAmount()).add(flyArrival2.getAmount()));
            prices.add(price2);

            //Precio 3
            PriceInfoBean price3 = new PriceInfoBean();
            price3.setEvent(event);
            price3.setId(3);
            price3.setHotelInfo(hotelInfo2);
            price3.setFlyDeparture(departure2);
            price3.setFlyArrival(arrival2);
            price3.setTotalAmount(event.getPrice().add(hotelInfo2.getPrice()).add(flyDeparture2.getAmount()).add(flyArrival2.getAmount()));
            prices.add(price3);

            //Precio 4
            PriceInfoBean price4 = new PriceInfoBean();
            price4.setEvent(event);
            price4.setId(4);
            price4.setHotelInfo(hotelInfo2);
            price4.setFlyDeparture(departure);
            price4.setFlyArrival(arrival);
            price4.setTotalAmount(event.getPrice().add(hotelInfo2.getPrice()).add(flyDeparture1.getAmount()).add(flyArrival1.getAmount()));
            prices.add(price4);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return prices;
    }


}
