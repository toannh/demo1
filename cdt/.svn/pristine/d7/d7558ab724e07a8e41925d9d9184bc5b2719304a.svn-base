package vn.chodientu.entity.db.report;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ReportCash implements Serializable {

    @Id
    private long time;
    @Indexed
    private long createTime;
    @Indexed
    private long updateTime;
    /**
     * sử dụng xèng
     */
    private long useBalance;
    /**
     * Nạp xèng
     */
    private long topup;
    /**
     * kiếm xèng
     */
    private long reward;
    private long upSchedule;
    private long vipItem;
    private long activeQuickBooking;
    private long activeCustomer;

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

    public long getUseBalance() {
        return useBalance;
    }

    public void setUseBalance(long useBalance) {
        this.useBalance = useBalance;
    }

    public long getTopup() {
        return topup;
    }

    public void setTopup(long topup) {
        this.topup = topup;
    }

    public long getReward() {
        return reward;
    }

    public void setReward(long reward) {
        this.reward = reward;
    }

    public long getUpSchedule() {
        return upSchedule;
    }

    public void setUpSchedule(long upSchedule) {
        this.upSchedule = upSchedule;
    }

    public long getVipItem() {
        return vipItem;
    }

    public void setVipItem(long vipItem) {
        this.vipItem = vipItem;
    }

    public long getActiveQuickBooking() {
        return activeQuickBooking;
    }

    public void setActiveQuickBooking(long activeQuickBooking) {
        this.activeQuickBooking = activeQuickBooking;
    }

    public long getActiveCustomer() {
        return activeCustomer;
    }

    public void setActiveCustomer(long activeCustomer) {
        this.activeCustomer = activeCustomer;
    }

}
