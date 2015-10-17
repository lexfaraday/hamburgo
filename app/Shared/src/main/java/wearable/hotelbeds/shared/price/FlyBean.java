package wearable.hotelbeds.shared.price;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Zavierazo on 17/10/2015.
 */
public class FlyBean implements Serializable {
    private Date departure;
    private Date arrival;
    private String company;

    public FlyBean(Date departure, Date arrival, String company) {
        this.departure = departure;
        this.arrival = arrival;
        this.company = company;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
