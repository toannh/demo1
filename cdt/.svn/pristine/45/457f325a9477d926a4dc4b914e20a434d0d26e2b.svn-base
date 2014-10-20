package vn.chodientu.entity.db;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import vn.chodientu.entity.data.SellerPolicy;
import vn.chodientu.entity.enu.ShipmentType;

@Document
public class Seller implements Serializable {

    @Id
    private String userId;
    private boolean nlIntegrated;
    private boolean scIntegrated;
    private String nlId;
    private String nlEmail;
    private String scId;
    private String scEmail;
    private ShipmentType shipmentType;
    private int shipmentPrice;
    //Show quảng cáo ở trang detail
    private boolean closeAdv;
    //Hiển thị marketing sms và email
    private boolean marketing;
    //Đăng bán nhanh
    private boolean quickSubmitItem;
    private List<SellerPolicy> salesPolicy;
    @Indexed
    private boolean pushC;
    @Indexed
    private boolean pushIntegrated;

    public boolean isPushIntegrated() {
        return pushIntegrated;
    }

    public void setPushIntegrated(boolean pushIntegrated) {
        this.pushIntegrated = pushIntegrated;
    }

    public boolean isPushC() {
        return pushC;
    }

    public void setPushC(boolean pushC) {
        this.pushC = pushC;
    }

    public boolean isCloseAdv() {
        return closeAdv;
    }

    public void setCloseAdv(boolean closeAdv) {
        this.closeAdv = closeAdv;
    }

    public boolean isMarketing() {
        return marketing;
    }

    public void setMarketing(boolean marketing) {
        this.marketing = marketing;
    }

    public List<SellerPolicy> getSalesPolicy() {
        return salesPolicy;
    }

    public void setSalesPolicy(List<SellerPolicy> salesPolicy) {
        this.salesPolicy = salesPolicy;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isNlIntegrated() {
        return nlIntegrated;
    }

    public void setNlIntegrated(boolean nlIntegrated) {
        this.nlIntegrated = nlIntegrated;
    }

    public boolean isScIntegrated() {
        return scIntegrated;
    }

    public void setScIntegrated(boolean scIntegrated) {
        this.scIntegrated = scIntegrated;
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

    public String getScEmail() {
        return scEmail;
    }

    public void setScEmail(String scEmail) {
        this.scEmail = scEmail;
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

    public boolean isQuickSubmitItem() {
        return quickSubmitItem;
    }

    public void setQuickSubmitItem(boolean quickSubmitItem) {
        this.quickSubmitItem = quickSubmitItem;
    }

}
