package vn.chodientu.entity.db.report;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ReportLading implements Serializable {

    @Id
    private long time;
    @Indexed
    private long createTime;
    @Indexed
    private long updateTime;
    private long newlading;
    private long stocking;
    private long delivering;
    private long returnLading;
    private long denied;
    private long delivered;
    private long lading;

    public long getLading() {
        return lading;
    }

    public void setLading(long lading) {
        this.lading = lading;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public long getNewlading() {
        return newlading;
    }

    public void setNewlading(long newlading) {
        this.newlading = newlading;
    }

    public long getStocking() {
        return stocking;
    }

    public void setStocking(long stocking) {
        this.stocking = stocking;
    }

    public long getDelivering() {
        return delivering;
    }

    public void setDelivering(long delivering) {
        this.delivering = delivering;
    }

    public long getReturnLading() {
        return returnLading;
    }

    public void setReturnLading(long returnLading) {
        this.returnLading = returnLading;
    }

    public long getDenied() {
        return denied;
    }

    public void setDenied(long denied) {
        this.denied = denied;
    }

    public long getDelivered() {
        return delivered;
    }

    public void setDelivered(long delivered) {
        this.delivered = delivered;
    }

}
