/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.entity.db;

import java.io.Serializable;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import vn.chodientu.entity.enu.PaymentMethod;
import vn.chodientu.entity.enu.ShipmentService;
import vn.chodientu.entity.enu.ShipmentStatus;

/**
 *
 * @author Vu Van Thanh
 */
@Document
public class Lading implements Serializable {

    @Id
    @NotBlank(message = "Mã đơn hàng không được để trống")
    private String orderId;
    @NotBlank(message = "Người liên hệ không được để trống")
    private String sellerName;
    @NotBlank(message = "Số điện thoại người bán không được để trống")
    @Pattern(message = "Số điện thoại người bán chưa đúng định dạng",
            regexp = "^0\\d{7,10}$")
    private String sellerPhone;
    @NotBlank(message = "Địa chỉ nhà người bán không được để trống")
    private String sellerAddress;
    @NotBlank(message = "Địa chỉ tỉnh,thành phố người bán không được để trống")
    private String sellerCityId;
    @NotBlank(message = "Địa chỉ quận,huyện người bán không được để trống")
    private String sellerDistrictId;
    @NotBlank(message = "Địa chỉ email người nhận không được để trống")
    @Pattern(message = "Địa chỉ email người nhận chưa đúng định dạng",
            regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private String receiverEmail;
    @NotBlank(message = "Địa chỉ email người nhận không được để trống")
    private String receiverName;
    @NotBlank(message = "Số điện thoại người nhận không được để trống")
    @Pattern(message = "Số điện thoại người nhận chưa đúng định dạng",
            regexp = "^0\\d{7,10}$")
    private String receiverPhone;
    @NotBlank(message = "Địa chỉ nhà người nhận không được để trống")
    private String receiverAddress;
    @NotBlank(message = "Địa chỉ tỉnh,thành phố người nhận không được để trống")
    private String receiverCityId;
    @NotBlank(message = "Địa chỉ quận,huyện người nhận không được để trống")
    private String receiverDistrictId;
    @NotBlank(message = "Tên hàng hóa không được để trống")
    @Length(max = 170, message = "Tên hàng hóa tối đa 170 ký tự")
    private String name;
    @NotBlank(message = "Mô tả chi tiết không được để trống")
    @Length(max = 500, message = "Mô tả chi tiết tối đa 220 ký tự")
    private String description;
    @Indexed
    private String scId;
    @Indexed
    private long createTime;
    @Indexed
    private long updateTime;
    private boolean protec;
    private int weight;
    private long codPrice;
    private ShipmentService shipmentService;
    private ShipmentStatus shipmentStatus;
    private PaymentMethod type;
    private long shipmentPrice;
    private long shipmentPCod;

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

    public long getShipmentPCod() {
        return shipmentPCod;
    }

    public void setShipmentPCod(long shipmentPCod) {
        this.shipmentPCod = shipmentPCod;
    }

    public long getShipmentPrice() {
        return shipmentPrice;
    }

    public void setShipmentPrice(long shipmentPrice) {
        this.shipmentPrice = shipmentPrice;
    }

    public boolean isProtec() {
        return protec;
    }

    public void setProtec(boolean protec) {
        this.protec = protec;
    }

    public ShipmentStatus getShipmentStatus() {
        return shipmentStatus;
    }

    public void setShipmentStatus(ShipmentStatus shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }

    public String getScId() {
        return scId;
    }

    public void setScId(String scId) {
        this.scId = scId;
    }

    public PaymentMethod getType() {
        return type;
    }

    public void setType(PaymentMethod type) {
        this.type = type;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public ShipmentService getShipmentService() {
        return shipmentService;
    }

    public void setShipmentService(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerPhone() {
        return sellerPhone;
    }

    public void setSellerPhone(String sellerPhone) {
        this.sellerPhone = sellerPhone;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public String getSellerCityId() {
        return sellerCityId;
    }

    public void setSellerCityId(String sellerCityId) {
        this.sellerCityId = sellerCityId;
    }

    public String getSellerDistrictId() {
        return sellerDistrictId;
    }

    public void setSellerDistrictId(String sellerDistrictId) {
        this.sellerDistrictId = sellerDistrictId;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverCityId() {
        return receiverCityId;
    }

    public void setReceiverCityId(String receiverCityId) {
        this.receiverCityId = receiverCityId;
    }

    public String getReceiverDistrictId() {
        return receiverDistrictId;
    }

    public void setReceiverDistrictId(String receiverDistrictId) {
        this.receiverDistrictId = receiverDistrictId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public long getCodPrice() {
        return codPrice;
    }

    public void setCodPrice(long codPrice) {
        this.codPrice = codPrice;
    }

}
