package vn.chodientu.entity.db;

import java.io.Serializable;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class SellerStock implements Serializable {

    @Id
    private String id;
    @Indexed
    private String sellerId;
    @Indexed
    @NotEmpty(message = "Tên kho hàng không được để trống")
    private String name;
    @NotEmpty(message = "Địa chỉ kho hàng không được để trống")
    private String address;
    @NotEmpty(message = "Địa chỉ thành phố kho hàng không được để trống")
    private String cityId;
    @NotEmpty(message = "Địa chỉ quận huyện kho hàng không được để trống")
    private String districtId;
    @NotEmpty(message = "Số điện thoại liên hệ kho hàng không được để trống")
    private String phone;
    @NotEmpty(message = "Tên người liên hệ kho hàng không được để trống")
    private String sellerName;
    private int order;
    @Indexed
    private boolean active;
    /*
     0- bình thường, 1- kho của user, 2- kho của shop
     */
    @Indexed
    private int type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
