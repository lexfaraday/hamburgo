package wearable.hotelbeds.shared.price;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Zavierazo on 08/10/2015.
 */
public class PriceInfoBean implements Serializable{
    private int id;
    private BigDecimal totalAmount;
    private String hotelName;
    private int hotelStars;
    private Date flyOut;
    private Date flyIn;
    private String roomInfo;
    private String flyOutAerolineName;
    private String flyInAerolineName;

    public PriceInfoBean() {

    }

    public PriceInfoBean(int id, BigDecimal totalAmount, String hotelName, int hotelStars, Date flyOut, Date flyIn, String roomInfo, String flyOutAerolineName, String flyInAerolineName) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.hotelName = hotelName;
        this.hotelStars = hotelStars;
        this.flyOut = flyOut;
        this.flyIn = flyIn;
        this.roomInfo = roomInfo;
        this.flyOutAerolineName = flyOutAerolineName;
        this.flyInAerolineName = flyInAerolineName;
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

    public Date getFlyOut() {
        return flyOut;
    }

    public void setFlyOut(Date flyOut) {
        this.flyOut = flyOut;
    }

    public Date getFlyIn() {
        return flyIn;
    }

    public void setFlyIn(Date flyIn) {
        this.flyIn = flyIn;
    }

    public String getRoomInfo() {
        return roomInfo;
    }

    public void setRoomInfo(String roomInfo) {
        this.roomInfo = roomInfo;
    }

    public String getFlyOutAerolineName() {
        return flyOutAerolineName;
    }

    public void setFlyOutAerolineName(String flyOutAerolineName) {
        this.flyOutAerolineName = flyOutAerolineName;
    }

    public String getFlyInAerolineName() {
        return flyInAerolineName;
    }

    public void setFlyInAerolineName(String flyInAerolineName) {
        this.flyInAerolineName = flyInAerolineName;
    }
}
