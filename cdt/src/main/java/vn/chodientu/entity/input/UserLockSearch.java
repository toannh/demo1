package vn.chodientu.entity.input;

import java.io.Serializable;

/**
 *
 * @author TheHoa
 */
public class UserLockSearch implements Serializable {

    private String userId;
    private int run;
    private int done;
    private long createTimeFrom;
    private long createTimeTo;

    private long updateTimeFrom;
    private long updateTimeTo;

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

    public int getRun() {
        return run;
    }

    public void setRun(int run) {
        this.run = run;
    }

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
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

    public long getUpdateTimeFrom() {
        return updateTimeFrom;
    }

    public void setUpdateTimeFrom(long updateTimeFrom) {
        this.updateTimeFrom = updateTimeFrom;
    }

    public long getUpdateTimeTo() {
        return updateTimeTo;
    }

    public void setUpdateTimeTo(long updateTimeTo) {
        this.updateTimeTo = updateTimeTo;
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

    
}
