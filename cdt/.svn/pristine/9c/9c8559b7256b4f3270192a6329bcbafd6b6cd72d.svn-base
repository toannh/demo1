package vn.chodientu.entity.db;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import vn.chodientu.entity.enu.PromotionTarget;
import vn.chodientu.entity.enu.PromotionType;

/**
 *
 * @author ThienPhu
 */
@Document
@CompoundIndex(name = "Publish", def = "{'active':1,'published':1,'startTime':1,'endTime':1}")
public class Promotion implements java.io.Serializable {

    @Id
    private String id;
    private String name;
    @Indexed
    private String sellerId;
    @Indexed
    private long startTime;
    @Indexed
    private long endTime;
    private PromotionType type;
    private PromotionTarget target;
    private boolean oldPromotion;
    @Indexed
    private boolean active;
    @Indexed
    private boolean published;
    //Thunt sửa thêm điều kiện áp dụng khuyến mại cho giá trị nhỏ nhất của hóa đơn 
    private double minOrderPrice;
    @Transient
    private List<PromotionCategory> categories;
    @Transient
    private List<PromotionItem> items;
    @Transient
    private String sellerName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public long getStartTime() {
        return startTime;
    }

    public PromotionType getType() {
        return type;
    }

    public void setType(PromotionType type) {
        this.type = type;
    }

    public PromotionTarget getTarget() {
        return target;
    }

    public void setTarget(PromotionTarget target) {
        this.target = target;
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

    public boolean isOldPromotion() {
        return oldPromotion;
    }

    public void setOldPromotion(boolean oldPromotion) {
        this.oldPromotion = oldPromotion;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public List<PromotionCategory> getCategories() {
        return categories;
    }

    public double getMinOrderPrice() {
        return minOrderPrice;
    }

    public void setMinOrderPrice(double minOrderPrice) {
        this.minOrderPrice = minOrderPrice;
    }

    public void setCategories(List<PromotionCategory> categories) {
        this.categories = categories;
    }

    public List<PromotionItem> getItems() {
        return items;
    }

    public void setItems(List<PromotionItem> items) {
        this.items = items;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

}
