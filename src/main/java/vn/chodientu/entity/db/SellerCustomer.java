package vn.chodientu.entity.db;

import java.io.Serializable;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author thanhvv
 */
@Document
public class SellerCustomer implements Serializable {

    @Id
    private String id;
    @NotBlank(message = "Tên khách hàng không được để trống")
    @Indexed
    private String name;
    private String username;
    @Indexed
    private String userId;
    @NotBlank(message = "Địa chỉ email không được để trống")
    @Pattern(message = "Địa chỉ email chưa đúng định dạng", regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    @Indexed
    private String email;
    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(message = "Số điện thoại chưa đúng định dạng", regexp = "^0\\d{7,10}$")
    @Indexed
    private String phone;
    private String address;
    private int order;
    private int orderPayment;
    private long orderPrice;
    private long orderPricePayment;
    @Indexed
    private long createTime;
    @Indexed
    private long updateTime;
    @Indexed
    private boolean emailVerified;
    @Indexed
    private boolean phoneVerified;
    @Indexed
    private boolean remove;
    @Indexed
    private String sellerId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public boolean isRemove() {
        return remove;
    }

    public void setRemove(boolean remove) {
        this.remove = remove;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getOrderPayment() {
        return orderPayment;
    }

    public void setOrderPayment(int orderPayment) {
        this.orderPayment = orderPayment;
    }

    public long getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(long orderPrice) {
        this.orderPrice = orderPrice;
    }

    public long getOrderPricePayment() {
        return orderPricePayment;
    }

    public void setOrderPricePayment(long orderPricePayment) {
        this.orderPricePayment = orderPricePayment;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public boolean isPhoneVerified() {
        return phoneVerified;
    }

    public void setPhoneVerified(boolean phoneVerified) {
        this.phoneVerified = phoneVerified;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

}
