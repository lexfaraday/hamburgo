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
    private String departureAirport;
    private String arrivalAirport;


    public FlyBean(Date departure, Date arrival, String company, String departureAirport, String arrivalAirport) {
        this.departure = departure;
        this.arrival = arrival;
        this.company = company;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
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

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }
}
