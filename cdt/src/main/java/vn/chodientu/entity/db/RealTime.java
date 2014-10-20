package vn.chodientu.entity.db;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import vn.chodientu.entity.enu.RealTimeEvent;
import vn.chodientu.entity.output.Response;

@Document
public class RealTime implements Serializable {

    @Id
    private String id;
    private long time;
    private String channel;
    private RealTimeEvent event;
    private Response data;
    @Indexed
    private boolean run;
    private long runTime;

    public long getRunTime() {
        return runTime;
    }

    public void setRunTime(long runTime) {
        this.runTime = runTime;
    }

    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public RealTimeEvent getEvent() {
        return event;
    }

    public void setEvent(RealTimeEvent event) {
        this.event = event;
    }

    public Response getData() {
        return data;
    }

    public void setData(Response data) {
        this.data = data;
    }

}
