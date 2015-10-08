package wearable.hotelbeds.shared.event;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Zavierazo on 06/10/2015.
 */
public class EventInfoBean {

    private String id;
    private String name;
    private Date timeStart;
    private Date timeEnd;
    private BigDecimal price;

    public EventInfoBean() {

    }

    public EventInfoBean(String id, String name, Date timeStart, Date timeEnd, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
