package vn.chodientu.entity.input;

import java.io.Serializable;

public class SellerSearch implements Serializable {

    private String userId;
    private int pushC;
    private int nlIntegrated;
    private int scIntegrated;
    private int pageIndex;
    private int pageSize;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getNlIntegrated() {
        return nlIntegrated;
    }

    public void setNlIntegrated(int nlIntegrated) {
        this.nlIntegrated = nlIntegrated;
    }

    public int getScIntegrated() {
        return scIntegrated;
    }

    public void setScIntegrated(int scIntegrated) {
        this.scIntegrated = scIntegrated;
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

    public int getPushC() {
        return pushC;
    }

    public void setPushC(int pushC) {
        this.pushC = pushC;
    }

}
