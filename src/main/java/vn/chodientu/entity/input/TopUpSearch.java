package vn.chodientu.entity.input;

import java.io.Serializable;

public class TopUpSearch implements Serializable {

    private String userId;
    private long createTimeFrom;
    private long createTimeTo;
    private String type;
    private String phone;
    private String amount;
    private String requestId;

    private int pageIndex;
    private int pageSize;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getCreateTimeFrom() {
        return createTimeFrom;
    }

    public void setCreateTimeFrom(long createTimeFrom) {
        this.createTimeFrom = createTimeFrom;
    }

    public long getCreateTimeTo() {
        return createTimeTo;
    }

    public void setCreateTimeTo(long createTimeTo) {
        this.createTimeTo = createTimeTo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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
