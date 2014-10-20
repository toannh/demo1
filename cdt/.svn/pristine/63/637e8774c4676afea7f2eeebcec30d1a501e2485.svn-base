package vn.chodientu.entity.db;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@CompoundIndex(name = "Schedule", def = "{'done':1,'upDay':1,'lock':1,'lastTime':1}")
public class UpSchedule implements Serializable {

    private String id;
    private String sellerId;
    private String itemId;
    private long createTime;
    @Indexed
    private long lastTime;
    private long nextTurn;
    @Indexed
    private List<Integer> upDay;
    @Indexed
    private List<Long> upTime;
    private int quantity;
    private int useQuantity;
    @Indexed
    private boolean done;
    @Indexed
    private boolean run;
    @Indexed
    private boolean lock;

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public long getNextTurn() {
        return nextTurn;
    }

    public void setNextTurn(long nextTurn) {
        this.nextTurn = nextTurn;
    }

    public String getId() {
        return id;
    }

    public long getLastTime() {
        return lastTime;
    }

    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
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

    public List<Integer> getUpDay() {
        return upDay;
    }

    public void setUpDay(List<Integer> upDay) {
        this.upDay = upDay;
    }

    public List<Long> getUpTime() {
        return upTime;
    }

    public void setUpTime(List<Long> upTime) {
        this.upTime = upTime;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUseQuantity() {
        return useQuantity;
    }

    public void setUseQuantity(int useQuantity) {
        this.useQuantity = useQuantity;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }

}
