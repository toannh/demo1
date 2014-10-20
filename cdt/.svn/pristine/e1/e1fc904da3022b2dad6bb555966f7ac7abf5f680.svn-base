package vn.chodientu.entity.db.report;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import vn.chodientu.entity.enu.ItemSource;

@Document
public class ReportItem implements Serializable {

    @Id
    private long time;
    @Indexed
    private long createTime;
    @Indexed
    private long updateTime;
    private long seller;
    private long crawl;
    private long api;
    private long total;
    private long unapproved;
    private long outDate;
    private long outOfStock;
    private long uncompleted;
    private long recycle;
    private long selling;

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

    public long getSeller() {
        return seller;
    }

    public void setSeller(long seller) {
        this.seller = seller;
    }

    public long getCrawl() {
        return crawl;
    }

    public void setCrawl(long crawl) {
        this.crawl = crawl;
    }

    public long getApi() {
        return api;
    }

    public void setApi(long api) {
        this.api = api;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getUnapproved() {
        return unapproved;
    }

    public void setUnapproved(long unapproved) {
        this.unapproved = unapproved;
    }

    public long getOutDate() {
        return outDate;
    }

    public void setOutDate(long outDate) {
        this.outDate = outDate;
    }

    public long getOutOfStock() {
        return outOfStock;
    }

    public void setOutOfStock(long outOfStock) {
        this.outOfStock = outOfStock;
    }

    public long getUncompleted() {
        return uncompleted;
    }

    public void setUncompleted(long uncompleted) {
        this.uncompleted = uncompleted;
    }

    public long getRecycle() {
        return recycle;
    }

    public void setRecycle(long recycle) {
        this.recycle = recycle;
    }

    public long getSelling() {
        return selling;
    }

    public void setSelling(long selling) {
        this.selling = selling;
    }

}
