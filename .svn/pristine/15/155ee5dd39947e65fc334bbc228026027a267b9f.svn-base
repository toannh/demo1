/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.entity.input;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.digest.DigestUtils;
import vn.chodientu.entity.enu.Condition;
import vn.chodientu.entity.enu.ItemSource;
import vn.chodientu.entity.enu.ListingType;
import vn.chodientu.util.UrlUtils;

/**
 *
 * @author ThuNguyen
 */
public class ItemSearch implements Serializable {

    private String id;
    private String keyword;
    private List<String> categoryIds;
    private String categoryId;
    private ListingType listingType;
    private Condition condition;
    private String sellerId;
    private String sellerName;
    private String shopCategoryId;
    /**
     * 0.TIME_DESC--1.PRICE_DESC--2.PRICE_ASC--3.UPDATE_TIME_DESC
     */
    private int orderBy;
    private long priceFrom;
    private long priceTo;
    private boolean onlinePayment;
    private boolean cod;
    private boolean freeShip;
    private boolean promotion;
    private List<PropertySearch> properties;
    private List<String> modelIds;
    private List<String> manufacturerIds;
    private List<String> cityIds;
    /**
     * 0. All, 1. Active, 2. Ended, 3. Out of stock, 4. Disapproved, 5.
     * Deactivated, 6. Uncompleted
     */
    private int status;
    private int pageIndex;
    private int pageSize;
    private int leaf;
    private long createTimeFrom;
    private long createTimeTo;
    private String promotionId;
    private String sellerSku;
    private ItemSource source;

    public int getLeaf() {
        return leaf;
    }

    public void setLeaf(int leaf) {
        this.leaf = leaf;
    }

    public long getCreateTimeFrom() {
        return createTimeFrom;
    }

    public void setCreateTimeFrom(long createTimeFrom) {
        this.createTimeFrom = createTimeFrom;
    }

    public long getCreateTimeTo() {
        return createTimeTo;
    }

    public void setCreateTimeTo(long createTimeTo) {
        this.createTimeTo = createTimeTo;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public List<String> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<String> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getShopCategoryId() {
        return shopCategoryId;
    }

    public void setShopCategoryId(String shopCategoryId) {
        this.shopCategoryId = shopCategoryId;
    }

    public int getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(int orderBy) {
        this.orderBy = orderBy;
    }

    public long getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(long priceFrom) {
        this.priceFrom = priceFrom;
    }

    public long getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(long priceTo) {
        this.priceTo = priceTo;
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

    public boolean isPromotion() {
        return promotion;
    }

    public void setPromotion(boolean promotion) {
        this.promotion = promotion;
    }

    public List<PropertySearch> getProperties() {
        return properties;
    }

    public void setProperties(List<PropertySearch> properties) {
        this.properties = properties;
    }

    public List<String> getModelIds() {
        return modelIds;
    }

    public void setModelIds(List<String> modelIds) {
        this.modelIds = modelIds;
    }

    public List<String> getManufacturerIds() {
        return manufacturerIds;
    }

    public void setManufacturerIds(List<String> manufacturerIds) {
        this.manufacturerIds = manufacturerIds;
    }

    public List<String> getCityIds() {
        return cityIds;
    }

    public void setCityIds(List<String> cityIds) {
        this.cityIds = cityIds;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
    }

    public String getSellerSku() {
        return sellerSku;
    }

    public void setSellerSku(String sellerSku) {
        this.sellerSku = sellerSku;
    }

    public ItemSource getSource() {
        return source;
    }

    public void setSource(ItemSource source) {
        this.source = source;
    }

    public String getKey() throws IOException {
        ItemSearch itemSearch;
        try {
            itemSearch = (ItemSearch) BeanUtils.cloneBean(this);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException ex) {
            Logger.getLogger(UrlUtils.class.getName()).log(Level.SEVERE, null, ex);
            itemSearch = new ItemSearch();
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(itemSearch);
        oos.close();
        return DigestUtils.md5Hex(baos.toByteArray());
    }

}
