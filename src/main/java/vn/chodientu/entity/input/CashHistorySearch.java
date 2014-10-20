package vn.chodientu.entity.input;

import java.io.Serializable;
import vn.chodientu.entity.enu.CashTransactionType;

public class CashHistorySearch implements Serializable {

    private String userId;
    private CashTransactionType type;
    private String objectId;
    private String cashTransactionId;
    private String admin;
    private boolean fine;

    private long startTime;
    private long endTime;

    private int pageIndex;
    private int pageSize;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public CashTransactionType getType() {
        return type;
    }

    public void setType(CashTransactionType type) {
        this.type = type;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getCashTransactionId() {
        return cashTransactionId;
    }

    public void setCashTransactionId(String cashTransactionId) {
        this.cashTransactionId = cashTransactionId;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public boolean isFine() {
        return fine;
    }

    public void setFine(boolean fine) {
        this.fine = fine;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
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
