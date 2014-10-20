package vn.chodientu.entity.input;

import java.io.Serializable;

/**
 * @since Jul 22, 2014
 * @author Account
 */
public class UserItemFollowSearch implements Serializable {

    private String id;
    private String itemId;
    private String keyword;
    private String userId;
    private long createTimeForm;
    private long createTimeTo;
    private int pageIndex;
    private int pageSize;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
