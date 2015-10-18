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

            if (infoBean.getTotalAmount().stripTrailingZeros().scale() <= 0) {
                mPrice = infoBean.getTotalAmount().toString() + " €";
            } else {
                mPrice = infoBean.getTotalAmount().setScale(2, BigDecimal.ROUND_UP).toString() + " €";
            }
            mCalendar = PriceUtils.DATE_FORMATER.format(infoBean.getFlyDeparture().get(0).getDeparture()) + " - " + PriceUtils.DATE_FORMATER.format(infoBean.getFlyArrival().get(0).getDeparture());
            mHotel = infoBean.getHotelInfo().getName() + " " + infoBean.getHotelInfo().getCodHab() + " " + infoBean.getHotelInfo().getReg() + " ";
            for (int i = 0; i < infoBean.getHotelInfo().getStars(); i++) {
                mHotel += "*";
            }
            mFlyOut = infoBean.getFlyDeparture().get(0).getCompany() + " " + PriceUtils.DATE_FORMATER_HOUR.format(infoBean.getFlyDeparture().get(0).getDeparture());
            mFlyIn = infoBean.getFlyArrival().get(0).getCompany() + " " + PriceUtils.DATE_FORMATER_HOUR.format(infoBean.getFlyArrival().get(0).getDeparture());
        }
    }

    public PriceInfoBean getInfoBean() {
        return infoBean;
    }

    public void setInfoBean(PriceInfoBean infoBean) {
        this.infoBean = infoBean;
    }
}

