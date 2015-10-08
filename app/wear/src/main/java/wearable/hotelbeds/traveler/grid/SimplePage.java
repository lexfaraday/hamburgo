package wearable.hotelbeds.traveler.grid;

import java.math.BigDecimal;

import wearable.hotelbeds.shared.price.PriceInfoBean;
import wearable.hotelbeds.shared.price.PriceUtils;

/**
 * Created by Zavierazo on 07/10/2015.
 */
public class SimplePage {

    public String mPrice;
    public String mCalendar;
    public String mHotel;
    public String mFlyOut;
    public String mFlyIn;
    private PriceInfoBean infoBean;

    public SimplePage(String price, String calendar, String hotel, String flyout, String flyin, PriceInfoBean infoBean) {
        this.mPrice = price;
        this.mCalendar = calendar;
        this.mHotel = hotel;
        this.mFlyOut = flyout;
        this.mFlyIn = flyin;
        this.infoBean = infoBean;
    }

    public SimplePage(PriceInfoBean infoBean) {
        this.infoBean = infoBean;
        if (infoBean != null) {
            mPrice = infoBean.getTotalAmount().setScale(2, BigDecimal.ROUND_UP).toString()+" €";
            mCalendar = PriceUtils.DATE_FORMATER.format(infoBean.getFlyOut()) + " - " + PriceUtils.DATE_FORMATER.format(infoBean.getFlyIn());
            mHotel = infoBean.getHotelName() + " " + infoBean.getRoomInfo() + " ";
            for (int i = 0; i < infoBean.getHotelStars(); i++) {
                mHotel += "*";
            }
            mFlyOut = infoBean.getFlyOutAerolineName() + " " + PriceUtils.DATE_FORMATER_HOUR.format(infoBean.getFlyOut());
            mFlyIn = infoBean.getFlyInAerolineName() + " " + PriceUtils.DATE_FORMATER_HOUR.format(infoBean.getFlyIn());
        }
    }

    public PriceInfoBean getInfoBean() {
        return infoBean;
    }

    public void setInfoBean(PriceInfoBean infoBean) {
        this.infoBean = infoBean;
    }
}

