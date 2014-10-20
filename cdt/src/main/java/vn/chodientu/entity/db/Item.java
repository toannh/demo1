package vn.chodientu.entity.db;

import java.util.List;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import vn.chodientu.entity.enu.Condition;
import vn.chodientu.entity.enu.ItemSource;
import vn.chodientu.entity.enu.ListingType;
import vn.chodientu.entity.enu.ShipmentType;

/**
 *
 * @author ThienPhu
 * @since Jul 3, 2013
 */
@Document
public class Item implements java.io.Serializable {

    @Id
    private String id;
    @Indexed
    private String modelId;
    @Indexed
    @NotBlank(message = "Mã danh mục không được để trống")
    private String categoryId;
    @Indexed
    private String manufacturerId;
    private List<String> categoryPath;
    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Length(max = 180, message = "Tên sản phẩm không được dài quá 180 ký tự")
    private String name;
    private long startTime;
    private long endTime;
    private ListingType listingType;
    private Condition condition;
    private int quantity;
    private int soldQuantity;
    private long startPrice;
    private long sellPrice;
    private long highestBid;
    private String highestBider;
    private int bidStep;
    private int bidCount;
    private boolean onlinePayment;
    private boolean cod;
    private ShipmentType shipmentType;
    private int shipmentPrice;
    private int weight;
    private String cityId;
    private String districtId;
    @Indexed
    private String sellerId;
    private String sellerName;
    @Indexed
    private String sellerSku;
    private String shopCategoryId;
    private List<String> shopCategoryPath;
    private String shopName;
    private String shopDescription;
    @Indexed
    private long createTime;
    private long updateTime;
    private long upTime;
    private long viewCount;
    private long viewTime;
    private boolean discount;
    private String promotionId;
    private long discountPrice;
    private int discountPercent;
    private boolean gift;
    private String giftDetail;
    @Indexed
    private boolean completed;
    private boolean active;
    private boolean approved;
    @Indexed
    private ItemSource source;
    private long follow;
    private long review;
    @Transient
    private List<String> images;
    @Transient
    private String modelName;
    @Transient
    private String detail;
    @Transient
    private String categoryName;
    @Transient
    private String manufacturerName;
    @Transient
    private long countItemSamePrice;
    @Transient
    private List<ItemProperty> properties;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public long getFollow() {
        return follow;
    }

    public void setFollow(long follow) {
        this.follow = follow;
    }

    public long getReview() {
        return review;
    }

    public void setReview(long review) {
        this.review = review;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
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

    public ListingType getListingType() {
        return listingType;
    }

    public void setListingType(ListingType listingType) {
        this.listingType = listingType;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(int soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public long getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(long startPrice) {
        this.startPrice = startPrice;
    }

    public long getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(long sellPrice) {
        this.sellPrice = sellPrice;
    }

    public long getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(long highestBid) {
        this.highestBid = highestBid;
    }

    public String getHighestBider() {
        return highestBider;
    }

    public void setHighestBider(String highestBider) {
        this.highestBider = highestBider;
    }

    public int getBidStep() {
        return bidStep;
    }

    public void setBidStep(int bidStep) {
        this.bidStep = bidStep;
    }

    public int getBidCount() {
        return bidCount;
    }

    public void setBidCount(int bidCount) {
        this.bidCount = bidCount;
    }

    public boolean isOnlinePayment() {
        return onlinePayment;
    }

    public void setOnlinePayment(boolean onlinePayment) {
        this.onlinePayment = onlinePayment;
    }

    public boolean isCod() {
        return cod;
    }

    public void setCod(boolean cod) {
        this.cod = cod;
    }

    public ShipmentType getShipmentType() {
        return shipmentType;
    }

    public void setShipmentType(ShipmentType shipmentType) {
        this.shipmentType = shipmentType;
    }

    public int getShipmentPrice() {
        return shipmentPrice;
    }

    public void setShipmentPrice(int shipmentPrice) {
        this.shipmentPrice = shipmentPrice;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerSku() {
        return sellerSku;
    }

    public void setSellerSku(String sellerSku) {
        this.sellerSku = sellerSku;
    }

    public String getShopCategoryId() {
        return shopCategoryId;
    }

    public void setShopCategoryId(String shopCategoryId) {
        this.shopCategoryId = shopCategoryId;
    }

    public List<String> getShopCategoryPath() {
        return shopCategoryPath;
    }

    public void setShopCategoryPath(List<String> shopCategoryPath) {
        this.shopCategoryPath = shopCategoryPath;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopDescription() {
        return shopDescription;
    }

    public void setShopDescription(String shopDescription) {
        this.shopDescription = shopDescription;
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

    public long getUpTime() {
        return upTime;
    }

    public void setUpTime(long upTime) {
        this.upTime = upTime;
    }

    public long getViewCount() {
        return viewCount;
    }

    public void setViewCount(long viewCount) {
        this.viewCount = viewCount;
    }

    public long getViewTime() {
        return viewTime;
    }

    public void setViewTime(long viewTime) {
        this.viewTime = viewTime;
    }

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
    }

    public long getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(long discountPrice) {
        this.discountPrice = discountPrice;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public boolean isGift() {
        return gift;
    }

    public void setGift(boolean gift) {
        this.gift = gift;
    }

    public String getGiftDetail() {
        return giftDetail;
    }

    public void setGiftDetail(String giftDetail) {
        this.giftDetail = giftDetail;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
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

    public ItemSource getSource() {
        return source;
    }

    public void setSource(ItemSource source) {
        this.source = source;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
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

    public List<ItemProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<ItemProperty> properties) {
        this.properties = properties;
    }

    public long getCountItemSamePrice() {
        return countItemSamePrice;
    }

    public void setCountItemSamePrice(long countItemSamePrice) {
        this.countItemSamePrice = countItemSamePrice;
    }

}
