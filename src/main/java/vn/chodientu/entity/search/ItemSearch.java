package vn.chodientu.entity.search;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.ItemProperty;
import vn.chodientu.entity.enu.ListingType;
import vn.chodientu.entity.enu.ShipmentType;
import vn.chodientu.util.TextUtils;

/**
 * @since Jun 2, 2014
 * @author Phu
 */
@Document(indexName = "item")
public class ItemSearch {

    @Id
    private String id;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String modelId;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String manufacturerId;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private List<String> categoryPath;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String cityId;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String districtId;
    @Field(type = FieldType.String, index = FieldIndex.analyzed)
    private String keyword;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String sellerId;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private List<String> shopCategoryPath;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String listingType;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String condition;
    @Field(type = FieldType.Boolean, index = FieldIndex.not_analyzed)
    private boolean onlinePayment;
    @Field(type = FieldType.Boolean, index = FieldIndex.not_analyzed)
    private boolean cod;
    @Field(type = FieldType.Boolean, index = FieldIndex.not_analyzed)
    private boolean freeShip;
    @Field(type = FieldType.Boolean, index = FieldIndex.not_analyzed)
    private boolean discount;
    @Field(type = FieldType.Boolean, index = FieldIndex.not_analyzed)
    private boolean gift;
    @Field(type = FieldType.Long, index = FieldIndex.not_analyzed)
    private long price;
    @Field(type = FieldType.Integer, index = FieldIndex.not_analyzed)
    private int quantity;
    @Field(type = FieldType.Integer, index = FieldIndex.not_analyzed)
    private int soldQuantity;
    @Field(type = FieldType.Long, index = FieldIndex.not_analyzed)
    private long startTime;
    @Field(type = FieldType.Long, index = FieldIndex.not_analyzed)
    private long endTime;
    @Field(type = FieldType.Long, index = FieldIndex.not_analyzed)
    private long createTime;
    @Field(type = FieldType.Long, index = FieldIndex.not_analyzed)
    private long updateTime;
    @Field(type = FieldType.Long, index = FieldIndex.not_analyzed)
    private long upTime;
    @Field(type = FieldType.Long, index = FieldIndex.not_analyzed)
    private long viewTime;
    @Field(type = FieldType.Long, index = FieldIndex.not_analyzed)
    private long viewCount;
    @Field(type = FieldType.Boolean, index = FieldIndex.not_analyzed)
    private boolean active;
    @Field(type = FieldType.Boolean, index = FieldIndex.not_analyzed)
    private boolean completed;
    @Field(type = FieldType.Boolean, index = FieldIndex.not_analyzed)
    private boolean approved;
    @Field(type = FieldType.Nested)
    private List<PropertySearch> properties;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String promotionId;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String source;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String sellerSku;

    public ItemSearch() {
    }

    public ItemSearch(Item item) {
        this.id = item.getId();
        this.modelId = item.getModelId();
        this.manufacturerId = item.getManufacturerId();
        this.categoryPath = item.getCategoryPath();
        this.cityId = item.getCityId();
        this.districtId = item.getDistrictId();
        this.keyword = "";
        if (item.getCategoryName() != null && !item.getCategoryName().equals("")) {
            this.keyword += item.getCategoryName() + " ";
        }
        if (item.getManufacturerName() != null && !item.getManufacturerName().equals("")) {
            this.keyword += item.getManufacturerName() + " ";
        }
        if (item.getModelName() != null && !item.getModelName().equals("")) {
            this.keyword += item.getModelName() + " ";
        }
        if (item.getName() != null && !item.getName().equals("")) {
            this.keyword += item.getName() + " ";
        }
        this.keyword = TextUtils.removeDiacritical(this.keyword);
        this.sellerId = item.getSellerId();
        this.shopCategoryPath = item.getShopCategoryPath();
        this.listingType = item.getListingType() == null ? "" : item.getListingType().toString();
        this.condition = item.getCondition() == null ? "" : item.getCondition().toString();
        this.onlinePayment = item.isOnlinePayment();
        this.cod = item.isCod();
        this.freeShip = ((item.getShipmentType() == ShipmentType.FIXED) && (item.getShipmentPrice() == 0));
        this.discount = item.isDiscount();
        this.gift = item.isGift();
        if (item.getListingType() == ListingType.BUYNOW) {
            this.price = item.getSellPrice();
        } else {
            if (item.getSellPrice() != 0) {
                this.price = item.getSellPrice();
            } else {
                this.price = item.getHighestBid();
            }
        }
        this.quantity = item.getQuantity();
        this.soldQuantity = item.getSoldQuantity();
        this.startTime = item.getStartTime();
        this.endTime = item.getEndTime();
        this.createTime = item.getCreateTime();
        this.updateTime = item.getUpdateTime();
        this.upTime = item.getUpTime();
        this.viewTime = item.getViewTime();
        this.viewCount = item.getViewCount();
        this.active = item.isActive();
        this.completed = item.isCompleted();
        this.approved = item.isApproved();
        this.properties = new ArrayList<>();
        if (item.getProperties() != null) {
            for (ItemProperty itemProperty : item.getProperties()) {
                if (itemProperty.getCategoryPropertyValueIds() != null) {
                    for (String v : itemProperty.getCategoryPropertyValueIds()) {
                        PropertySearch ps = new PropertySearch();
                        ps.setName(itemProperty.getCategoryPropertyId());
                        ps.setValue(Integer.parseInt(v));
                        this.properties.add(ps);
                    }
                }
            }
        }
        this.promotionId = item.getPromotionId();
        if (item.getSellerSku() != null && !item.getSellerSku().equals("")) {
            this.sellerSku = DigestUtils.md5Hex(item.getSellerSku());
        }
        this.source = item.getSource() == null ? "" : item.getSource().toString();
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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public List<String> getShopCategoryPath() {
        return shopCategoryPath;
    }

    public void setShopCategoryPath(List<String> shopCategoryPath) {
        this.shopCategoryPath = shopCategoryPath;
    }

    public String getListingType() {
        return listingType;
    }

    public void setListingType(String listingType) {
        this.listingType = listingType;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
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

    public boolean isFreeShip() {
        return freeShip;
    }

    public void setFreeShip(boolean freeShip) {
        this.freeShip = freeShip;
    }

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    public boolean isGift() {
        return gift;
    }

    public void setGift(boolean gift) {
        this.gift = gift;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
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

    public long getViewTime() {
        return viewTime;
    }

    public void setViewTime(long viewTime) {
        this.viewTime = viewTime;
    }

    public long getViewCount() {
        return viewCount;
    }

    public void setViewCount(long viewCount) {
        this.viewCount = viewCount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
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

    public List<PropertySearch> getProperties() {
        return properties;
    }

    public void setProperties(List<PropertySearch> properties) {
        this.properties = properties;
    }

    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSellerSku() {
        return sellerSku;
    }

    public void setSellerSku(String sellerSku) {
        this.sellerSku = sellerSku;
    }

}
