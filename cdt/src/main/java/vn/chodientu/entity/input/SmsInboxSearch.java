package vn.chodientu.entity.input;

import java.io.Serializable;
import vn.chodientu.entity.enu.SmsInboxType;

/**
 *
 * @author thanhvv
 */
public class SmsInboxSearch implements Serializable{

    private long timeFrom;
    private long timeTo;
    private String phone;
    private String receiver;
    private SmsInboxType type;
    private int success;
    private long sentTimeFrom;
    private long sentTimeTo;
    private int pageIndex;
    private int pageSize;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReceiver() {
        return receiver;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public long getTimeFrom() {
        return timeFrom;
    }

    public SmsInboxType getType() {
        return type;
    }

    public void setType(SmsInboxType type) {
        this.type = type;
    }

    public void setTimeFrom(long timeFrom) {
        this.timeFrom = timeFrom;
    }

    public long getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(long timeTo) {
        this.timeTo = timeTo;
    }

    public long getSentTimeFrom() {
        return sentTimeFrom;
    }

    public void setSentTimeFrom(long sentTimeFrom) {
        this.sentTimeFrom = sentTimeFrom;
    }

    public long getSentTimeTo() {
        return sentTimeTo;
    }

    public void setSentTimeTo(long sentTimeTo) {
        this.sentTimeTo = sentTimeTo;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
