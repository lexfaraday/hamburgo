package wearable.hotelbeds.shared.event;

import java.util.Date;

/**
 * Created by Zavierazo on 06/10/2015.
 */
public class EventInfo {

    private String name;
    private Date timeStart;
    private Date timeEnd;

    public EventInfo(String name, Date timeStart, Date timeEnd) {
        this.name = name;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
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
}
