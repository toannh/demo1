package vn.chodientu.entity.db;

import java.io.Serializable;
import java.util.List;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import vn.chodientu.entity.form.TopSellerBoxItemForm;

/**
 * @since Jun 16, 2014
 * @author Phuongdt
 */
@Document
public class TopSellerBox implements Serializable {

    @Id
    private String id;
    private String sellerId;
    private List<TopSellerBoxItemForm> topSellerBoxItemForms;
    private int position;
    private boolean active;
    @Transient
    private String image;
    @Transient
    private List<Item> items;
    @Transient
    private String sellerName;
    @Transient
    private long countItem;
    @Transient
    private String city;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public List<TopSellerBoxItemForm> getTopSellerBoxItemForms() {
        return topSellerBoxItemForms;
    }

    public void setTopSellerBoxItemForms(List<TopSellerBoxItemForm> topSellerBoxItemForms) {
        this.topSellerBoxItemForms = topSellerBoxItemForms;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public long getCountItem() {
        return countItem;
    }

    public void setCountItem(long countItem) {
        this.countItem = countItem;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
