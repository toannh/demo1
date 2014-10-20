package vn.chodientu.entity.db;

import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import vn.chodientu.entity.enu.PaymentMethod;
import vn.chodientu.entity.enu.PaymentStatus;
import vn.chodientu.entity.enu.PaymentType;
import vn.chodientu.entity.enu.ShipmentService;
import vn.chodientu.entity.enu.ShipmentStatus;

@Document
@CompoundIndex(name = "UpdateShipment", def = "{'shipmentCreateTime':1,'shipmentUpdateTime':1,'scId':1,'shipmentStatus':1}")
public class Order implements Cloneable, Serializable {

    @Id
    private String id;
    @Indexed
    private String sellerId;
    @Indexed
    private String buyerId;
    @Indexed
    @NotBlank(message = "Địa chỉ email người mua không được để trống")
    @Pattern(message = "Địa chỉ email người mua chưa đúng định dạng",
            regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private String buyerEmail;
    @NotBlank(message = "Tên người mua không được để trống")
    private String buyerName;
    @NotBlank(message = "Số điện thoại người mua không được để trống")
    @Pattern(message = "Số điện thoại người mua chưa đúng định dạng",
            regexp = "^0\\d{7,10}$")
    private String buyerPhone;
    @NotBlank(message = "Địa chỉ nhà người mua không được để trống")
    private String buyerAddress;
    @Indexed
    @NotBlank(message = "Địa chỉ tỉnh,thành phố người mua không được để trống")
    private String buyerCityId;
    @Indexed
    @NotBlank(message = "Địa chỉ quận,huyện người mua không được để trống")
    private String buyerDistrictId;
    @NotBlank(message = "Tên người nhận không được để trống")
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
    @Indexed
    @NotBlank(message = "Địa chỉ tỉnh,thành phố người nhận không được để trống")
    private String receiverCityId;
    @Indexed
    @NotBlank(message = "Địa chỉ quận,huyện người nhận không được để trống")
    private String receiverDistrictId;
    private long totalPrice;
    private long couponPrice;
    private String couponId;
    private long finalPrice;
    private long codPrice;
    private PaymentMethod paymentMethod;
    private PaymentType paymentType;
    @Indexed
    private boolean refundStatus;
    @Indexed
    private PaymentStatus paymentStatus;
    @Indexed
    private ShipmentStatus shipmentStatus;
    @Indexed
    private ShipmentService shipmentService;
    @Indexed
    private String nlId;
    @Indexed
    private String nlEmail;
    @Indexed
    private String scId;
    @Indexed
    private long createTime;
    @Indexed
    private long updateTime;
    @Indexed
    private long paymentCreateTime;
    @Indexed
    private long paidTime;
    @Indexed
    private long shipmentCreateTime;
    @Indexed
    private long shipmentUpdateTime;
    private long shipmentPrice;
    private long shipmentPCod;
    private String shipmentMessage;
    @Indexed
    private long deliveredTime;
    @Indexed
    private boolean remove;
    @Indexed
    private boolean customer;
    @Indexed
    private boolean ignoreCustomer;
    private PaymentStatus markBuyerPayment;
    private ShipmentStatus markBuyerShipment;
    private PaymentStatus markSellerPayment;
    private ShipmentStatus markSellerShipment;
    private String note;
    private String sellerNote;
    private long cdtDiscountShipment;
    @Indexed
    private boolean pushC;
    @Indexed
    private boolean pushPayment;

    @Transient
    private List<OrderItem> items;
    @Transient
    private User user;
    @Transient
    private Shop shop;
    @Transient
    private SellerReview sellerReview;

    public boolean isPushPayment() {
        return pushPayment;
    }

    public void setPushPayment(boolean pushPayment) {
        this.pushPayment = pushPayment;
    }

    @Override
    public Order clone() throws CloneNotSupportedException {
        return (Order) super.clone();
    }

    public boolean isPushC() {
        return pushC;
    }

    public void setPushC(boolean pushC) {
        this.pushC = pushC;
    }

    public boolean isIgnoreCustomer() {
        return ignoreCustomer;
    }

    public long getCdtDiscountShipment() {
        return cdtDiscountShipment;
    }

    public void setCdtDiscountShipment(long cdtDiscountShipment) {
        this.cdtDiscountShipment = cdtDiscountShipment;
    }

    public void setIgnoreCustomer(boolean ignoreCustomer) {
        this.ignoreCustomer = ignoreCustomer;
    }

    public boolean isCustomer() {
        return customer;
    }

    public void setCustomer(boolean customer) {
        this.customer = customer;
    }

    public long getShipmentPCod() {
        return shipmentPCod;
    }

    public void setShipmentPCod(long shipmentPCod) {
        this.shipmentPCod = shipmentPCod;
    }

    public String getShipmentMessage() {
        return shipmentMessage;
    }

    public void setShipmentMessage(String shipmentMessage) {
        this.shipmentMessage = shipmentMessage;
    }

    public long getShipmentUpdateTime() {
        return shipmentUpdateTime;
    }

    public void setShipmentUpdateTime(long shipmentUpdateTime) {
        this.shipmentUpdateTime = shipmentUpdateTime;
    }

    public String getId() {
        return id;
    }

    public long getCodPrice() {
        return codPrice;
    }

    public void setCodPrice(long codPrice) {
        this.codPrice = codPrice;
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

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public String getBuyerCityId() {
        return buyerCityId;
    }

    public void setBuyerCityId(String buyerCityId) {
        this.buyerCityId = buyerCityId;
    }

    public String getBuyerDistrictId() {
        return buyerDistrictId;
    }

    public void setBuyerDistrictId(String buyerDistrictId) {
        this.buyerDistrictId = buyerDistrictId;
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

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public long getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(long couponPrice) {
        this.couponPrice = couponPrice;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public long getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(long finalPrice) {
        this.finalPrice = finalPrice;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public boolean isRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(boolean refundStatus) {
        this.refundStatus = refundStatus;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public ShipmentStatus getShipmentStatus() {
        return shipmentStatus;
    }

    public void setShipmentStatus(ShipmentStatus shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }

    public ShipmentService getShipmentService() {
        return shipmentService;
    }

    public void setShipmentService(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    public String getNlId() {
        return nlId;
    }

    public void setNlId(String nlId) {
        this.nlId = nlId;
    }

    public String getNlEmail() {
        return nlEmail;
    }

    public void setNlEmail(String nlEmail) {
        this.nlEmail = nlEmail;
    }

    public String getScId() {
        return scId;
    }

    public void setScId(String scId) {
        this.scId = scId;
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

    public long getPaymentCreateTime() {
        return paymentCreateTime;
    }

    public void setPaymentCreateTime(long paymentCreateTime) {
        this.paymentCreateTime = paymentCreateTime;
    }

    public long getPaidTime() {
        return paidTime;
    }

    public void setPaidTime(long paidTime) {
        this.paidTime = paidTime;
    }

    public long getShipmentCreateTime() {
        return shipmentCreateTime;
    }

    public void setShipmentCreateTime(long shipmentCreateTime) {
        this.shipmentCreateTime = shipmentCreateTime;
    }

    public long getShipmentPrice() {
        return shipmentPrice;
    }

    public void setShipmentPrice(long shipmentPrice) {
        this.shipmentPrice = shipmentPrice;
    }

    public long getDeliveredTime() {
        return deliveredTime;
    }

    public void setDeliveredTime(long deliveredTime) {
        this.deliveredTime = deliveredTime;
    }

    public boolean isRemove() {
        return remove;
    }

    public void setRemove(boolean remove) {
        this.remove = remove;
    }

    public PaymentStatus getMarkBuyerPayment() {
        return markBuyerPayment;
    }

    public void setMarkBuyerPayment(PaymentStatus markBuyerPayment) {
        this.markBuyerPayment = markBuyerPayment;
    }

    public ShipmentStatus getMarkBuyerShipment() {
        return markBuyerShipment;
    }

    public void setMarkBuyerShipment(ShipmentStatus markBuyerShipment) {
        this.markBuyerShipment = markBuyerShipment;
    }

    public PaymentStatus getMarkSellerPayment() {
        return markSellerPayment;
    }

    public void setMarkSellerPayment(PaymentStatus markSellerPayment) {
        this.markSellerPayment = markSellerPayment;
    }

    public ShipmentStatus getMarkSellerShipment() {
        return markSellerShipment;
    }

    public void setMarkSellerShipment(ShipmentStatus markSellerShipment) {
        this.markSellerShipment = markSellerShipment;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSellerNote() {
        return sellerNote;
    }

    public void setSellerNote(String sellerNote) {
        this.sellerNote = sellerNote;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public SellerReview getSellerReview() {
        return sellerReview;
    }

    public void setSellerReview(SellerReview sellerReview) {
        this.sellerReview = sellerReview;
    }

}
