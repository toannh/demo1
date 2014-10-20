package vn.chodientu.entity.input;

import java.io.Serializable;

/**
 * @since Jul 22, 2014
 * @author Account
 */
public class SellerFollowSearch implements Serializable {

    private String id;
    private String sellerId;
    private String userId;
    private long createTimeForm;
    private long createTimeTo;
    private int pageIndex;
    private int pageSize;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getCreateTimeForm() {
        return createTimeForm;
    }

    public void setCreateTimeForm(long createTimeForm) {
        this.createTimeForm = createTimeForm;
    }

    public long getCreateTimeTo() {
        return createTimeTo;
    }

    public void setCreateTimeTo(long createTimeTo) {
        this.createTimeTo = createTimeTo;
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
