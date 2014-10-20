package vn.chodientu.entity.db;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import vn.chodientu.entity.data.OrderSubItem;
import vn.chodientu.entity.enu.ShipmentType;

/**
 *
 * @author Phu
 */
@Document
public class OrderItem implements Serializable {

    @Id
    private String id;
    @Indexed
    private String orderId;
    @Indexed
    private String itemId;
    @Indexed
    private List<String> categoryPath;
    private String itemName;
    @Indexed
    private long itemPrice;
    private long itemStartPrice;
    private int quantity;
    private int weight;
    private boolean aution;
    private String giftDetail;
    private long discountPrice;
    private int discountPercent;
    private ShipmentType shipmentType;
    private int shipmentPrice;
    private List<OrderSubItem> subItem;
    @Transient
    private List<String> images;
    @Transient
    private String nameCategory;

    public List<OrderSubItem> getSubItem() {
        return subItem;
    }

    public void setSubItem(List<OrderSubItem> subItem) {
        this.subItem = subItem;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public List<String> getCategoryPath() {
        return categoryPath;
    }

    public void setCategoryPath(List<String> categoryPath) {
        this.categoryPath = categoryPath;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public long getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(long itemPrice) {
        this.itemPrice = itemPrice;
    }

    public long getItemStartPrice() {
        return itemStartPrice;
    }

    public void setItemStartPrice(long itemStartPrice) {
        this.itemStartPrice = itemStartPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isAution() {
        return aution;
    }

    public void setAution(boolean aution) {
        this.aution = aution;
    }

    public String getGiftDetail() {
        return giftDetail;
    }

    public void setGiftDetail(String giftDetail) {
        this.giftDetail = giftDetail;
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

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

}
