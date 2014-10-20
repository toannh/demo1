package vn.chodientu.entity.db;

import java.util.List;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Model sản phẩm
 *
 * @author PhuGT
 * @since Jun 17, 2013
 */
@Document
public class Model implements java.io.Serializable {

    @Id
    private String id;
    @Indexed
    @Length(max = 32, message = "Mã danh mục không được dài quá 32 ký tự")
    private String categoryId;
    @Indexed
    @Length(max = 32, message = "Mã thương hiệu không được dài quá 32 ký tự")
    private String manufacturerId;
    private List<String> categoryPath;
    @NotBlank(message = "Tên model không được để trống")
    @Length(max = 128, message = "Tên model không được dài quá 128 ký tự")
    private String name;
    private String ebayKeyword;
    private long oldMinPrice;
    private long oldMaxPrice;
    private long newMinPrice;
    private long newMaxPrice;
    private long itemCount;
    private long viewCount;
    private long reviewCount;
    private long recommendedCount;
    private float reviewScore;
    private long createTime;
    private long updateTime;
    private long viewTime;
    private boolean active;
    private boolean approved;
    @Transient
    private List<String> images;
    @Transient
    private String categoryName;
    @Transient
    private String manufacturerName;
    @Transient
    private List<ModelProperty> properties;
    @Transient
    private long referencePrice;
    @Transient
    private long countShop;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public List<String> getCategoryPath() {
        return categoryPath;
    }

    public void setCategoryPath(List<String> categoryPath) {
        this.categoryPath = categoryPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEbayKeyword() {
        return ebayKeyword;
    }

    public void setEbayKeyword(String ebayKeyword) {
        this.ebayKeyword = ebayKeyword;
    }

    public long getOldMinPrice() {
        return oldMinPrice;
    }

    public void setOldMinPrice(long oldMinPrice) {
        this.oldMinPrice = oldMinPrice;
    }

    public long getOldMaxPrice() {
        return oldMaxPrice;
    }

    public void setOldMaxPrice(long oldMaxPrice) {
        this.oldMaxPrice = oldMaxPrice;
    }

    public long getNewMinPrice() {
        return newMinPrice;
    }

    public void setNewMinPrice(long newMinPrice) {
        this.newMinPrice = newMinPrice;
    }

    public long getNewMaxPrice() {
        return newMaxPrice;
    }

    public void setNewMaxPrice(long newMaxPrice) {
        this.newMaxPrice = newMaxPrice;
    }

    public long getItemCount() {
        return itemCount;
    }

    public void setItemCount(long itemCount) {
        this.itemCount = itemCount;
    }

    public long getViewCount() {
        return viewCount;
    }

    public void setViewCount(long viewCount) {
        this.viewCount = viewCount;
    }

    public long getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(long reviewCount) {
        this.reviewCount = reviewCount;
    }

    public long getRecommendedCount() {
        return recommendedCount;
    }

    public void setRecommendedCount(long recommendedCount) {
        this.recommendedCount = recommendedCount;
    }

    public float getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(float reviewScore) {
        this.reviewScore = reviewScore;
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

    public long getViewTime() {
        return viewTime;
    }

    public void setViewTime(long viewTime) {
        this.viewTime = viewTime;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public List<ModelProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<ModelProperty> properties) {
        this.properties = properties;
    }

    public long getReferencePrice() {
        return referencePrice;
    }

    public void setReferencePrice(long referencePrice) {
        this.referencePrice = referencePrice;
    }

    public long getCountShop() {
        return countShop;
    }

    public void setCountShop(long countShop) {
        this.countShop = countShop;
    }


}
