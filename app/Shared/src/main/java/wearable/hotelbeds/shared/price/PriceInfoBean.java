package wearable.hotelbeds.shared.price;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import wearable.hotelbeds.shared.event.EventInfoBean;

/**
 * Created by Zavierazo on 08/10/2015.
 */
public class PriceInfoBean implements Serializable {
    private int id;
    private BigDecimal totalAmount;
    private String hotelName;
    private int hotelStars;
    private List<String> hotelImages;
    private List<FlyBean> flyDeparture;
    private List<FlyBean> flyArrival;
    private String roomInfo;
    private EventInfoBean event;

    public PriceInfoBean() {

    }

    public PriceInfoBean(int id, BigDecimal totalAmount, String hotelName, int hotelStars, List<String> hotelImages, List<FlyBean> flyDeparture, List<FlyBean> flyArrival, String roomInfo, EventInfoBean event) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.hotelName = hotelName;
        this.hotelStars = hotelStars;
        this.hotelImages = hotelImages;
        this.flyDeparture = flyDeparture;
        this.flyArrival = flyArrival;
        this.roomInfo = roomInfo;
        this.event = event;
    }

    public EventInfoBean getEvent() {
        return event;
    }

    public void setEvent(EventInfoBean event) {
        this.event = event;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getHotelStars() {
        return hotelStars;
    }

    public void setHotelStars(int hotelStars) {
        this.hotelStars = hotelStars;
    }

    public List<String> getHotelImages() {
        return hotelImages;
    }

    public void setHotelImages(List<String> hotelImages) {
        this.hotelImages = hotelImages;
    }

    public List<FlyBean> getFlyDeparture() {
        return flyDeparture;
    }

    public void setFlyDeparture(List<FlyBean> flyDeparture) {
        this.flyDeparture = flyDeparture;
    }

    public List<FlyBean> getFlyArrival() {
        return flyArrival;
    }

    public void setFlyArrival(List<FlyBean> flyArrival) {
        this.flyArrival = flyArrival;
    }

    public String getRoomInfo() {
        return roomInfo;
    }

    public void setRoomInfo(String roomInfo) {
        this.roomInfo = roomInfo;
    }
}
