package vn.chodientu.entity.db;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import vn.chodientu.entity.enu.ItemHistoryType;
import vn.chodientu.entity.enu.ItemSource;

/**
 * @since May 9, 2014
 * @author Phu
 */
@Document
public class ItemHistory implements Serializable {

    @Id
    private String id;
    @Indexed
    private String itemId;
    private String itemName;
    @Indexed
    private String sellerId;
    private String sellerName;
    private String sellerSku;
    private ItemSource source;
    @Indexed
    private String adminEmail;
    @Indexed
    private ItemHistoryType itemHistoryType;
    @Indexed
    private long createTime;
    private boolean admin;
    private String ip;

    public ItemSource getSource() {
        return source;
    }

    public void setSource(ItemSource source) {
        this.source = source;
    }

    public String getSellerSku() {
        return sellerSku;
    }

    public void setSellerSku(String sellerSku) {
        this.sellerSku = sellerSku;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public ItemHistoryType getItemHistoryType() {
        return itemHistoryType;
    }

    public void setItemHistoryType(ItemHistoryType itemHistoryType) {
        this.itemHistoryType = itemHistoryType;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}
