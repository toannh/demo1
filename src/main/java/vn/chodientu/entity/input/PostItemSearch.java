package vn.chodientu.entity.input;

import java.io.Serializable;

/**
 *
 * @author thanhvv
 */
public class PostItemSearch implements Serializable {

    private String itemId; //mã sản phẩm
    private int done;  //Trạng thái của tin up
    private int run;  //Trạng thái của tin up
    private int type;
    private long startTime;
    private long endTime;
    private String userId;
    private String postId;
    private int oldQuantity;

    
    public int getOldQuantity() {
        return oldQuantity;
    }
    public void setOldQuantity(int oldQuantity) {
        this.oldQuantity = oldQuantity;
    }

    private int pageIndex;
    private int pageSize;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }

    public int getRun() {
        return run;
    }

    public void setRun(int run) {
        this.run = run;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
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
