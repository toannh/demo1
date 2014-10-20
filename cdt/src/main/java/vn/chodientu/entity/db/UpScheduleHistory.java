package vn.chodientu.entity.db;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UpScheduleHistory implements Serializable {

    private String id;
    @Indexed
    private String sellerId;
    @Indexed
    private String upScheduleId;
    @Indexed
    private String itemId;
    @Indexed
    private long createTime;
    private long scheduleTime;
    @Indexed
    private boolean done;
    @Indexed
    private boolean free;

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public String getUpScheduleId() {
        return upScheduleId;
    }

    public void setUpScheduleId(String upScheduleId) {
        this.upScheduleId = upScheduleId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(long scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
