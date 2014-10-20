/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.entity.form;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author thanhvv
 */
public class ModelForm {

    public final static int TRUE = 1;
    public final static int FALSE = 2;

    private String id;
    private String categoryId;
    private String manufacturerId;
    private String name;
    private String ebayKeyword;
    private int active;
    private int approved;

    private MultipartFile image;    // sử dụng cho upload ảnh
    private String imageUrl; // sử dụng cho download ảnh từ web khác

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEbayKeyword() {
        return ebayKeyword;
    }

    public void setEbayKeyword(String ebayKeyword) {
        this.ebayKeyword = ebayKeyword;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getApproved() {
        return approved;
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    

}
