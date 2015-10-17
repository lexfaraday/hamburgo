package wearable.hotelbeds.shared.price;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import wearable.hotelbeds.shared.event.EventInfoBean;
import wearable.hotelbeds.shared.hotel.HotelInfo;

/**
 * Created by Zavierazo on 08/10/2015.
 */
public class PriceInfoBean implements Serializable {
    private int id;
    private BigDecimal totalAmount;
    private HotelInfo hotelInfo;
    private List<FlyBean> flyDeparture;
    private List<FlyBean> flyArrival;
    private EventInfoBean event;

    public PriceInfoBean() {

    }

    public PriceInfoBean(int id, BigDecimal totalAmount, List<FlyBean> flyDeparture, List<FlyBean> flyArrival, EventInfoBean event, HotelInfo hotelInfo) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.flyDeparture = flyDeparture;
        this.flyArrival = flyArrival;
        this.event = event;
        this.hotelInfo = hotelInfo;
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

    public HotelInfo getHotelInfo() {
        return hotelInfo;
    }

    public void setHotelInfo(HotelInfo hotelInfo) {
        this.hotelInfo = hotelInfo;
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

    public EventInfoBean getEvent() {
        return event;
    }

    public void setEvent(EventInfoBean event) {
        this.event = event;
    }
}
