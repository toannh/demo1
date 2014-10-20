package vn.chodientu.entity.db.report;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ReportUser implements Serializable {

    @Id
    private long time;
    @Indexed
    private long createTime;
    @Indexed
    private long updateTime;
    private long totalUser;
    private long newbie;
    private long emailVerified;
    private long phoneVerified;
    private long totalEmailVerified;
    private long totalPhoneVerified;

    public long getTotalEmailVerified() {
        return totalEmailVerified;
    }

    public void setTotalEmailVerified(long totalEmailVerified) {
        this.totalEmailVerified = totalEmailVerified;
    }

    public long getTotalPhoneVerified() {
        return totalPhoneVerified;
    }

    public void setTotalPhoneVerified(long totalPhoneVerified) {
        this.totalPhoneVerified = totalPhoneVerified;
    }

    public long getTotalUser() {
        return totalUser;
    }

    public void setTotalUser(long totalUser) {
        this.totalUser = totalUser;
    }

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

    public long getNewbie() {
        return newbie;
    }

    public void setNewbie(long newbie) {
        this.newbie = newbie;
    }

    public long getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(long emailVerified) {
        this.emailVerified = emailVerified;
    }

    public long getPhoneVerified() {
        return phoneVerified;
    }

    public void setPhoneVerified(long phoneVerified) {
        this.phoneVerified = phoneVerified;
    }

}
