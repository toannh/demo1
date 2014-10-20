package vn.chodientu.entity.form;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;
import vn.chodientu.entity.enu.ShopBannerType;

/**
 * @since Jun 4, 2014
 * @author Phu
 */
public class ShopBannerForm {

    private String id;
    private String sellerId;
    @NotBlank(message = "Tên mô tả không được để trống")
    private String title;
    private String url;
    private String embedCode;
    private String bannerType;
    private ShopBannerType type;
    private int position;
    private MultipartFile image;
    private boolean active;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmbedCode() {
        return embedCode;
    }

    public void setEmbedCode(String embedCode) {
        this.embedCode = embedCode;
    }

    public String getBannerType() {
        return bannerType;
    }

    public void setBannerType(String bannerType) {
        this.bannerType = bannerType;
    }

    public ShopBannerType getType() {
        return type;
    }

    public void setType(ShopBannerType type) {
        this.type = type;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
