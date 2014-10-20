package vn.chodientu.entity.db;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Bid implements Serializable {

    @Id
    private String id;
    @Indexed
    private String itemId;
    @Indexed
    private String biderId;
    @Indexed
    private long bid;
    @Indexed
    private long time;
    @Indexed
    private boolean auto;
    private boolean autoBiding;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getBiderId() {
        return biderId;
    }

    public void setBiderId(String biderId) {
        this.biderId = biderId;
    }

    public long getBid() {
        return bid;
    }

    public void setBid(long bid) {
        this.bid = bid;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isAuto() {
        return auto;
    }

    public void setAuto(boolean auto) {
        this.auto = auto;
    }

    public boolean isAutoBiding() {
        return autoBiding;
    }

    public void setAutoBiding(boolean autoBiding) {
        this.autoBiding = autoBiding;
    }

}
