package vn.chodientu.entity.form;

import java.io.Serializable;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;
import vn.chodientu.entity.enu.AdvBannerType;

/**
 * @since Aug 15, 2014
 * @author Account
 */
public class AdvBannerForm implements Serializable{
    private String id;
    @NotEmpty(message = "Link banner không được để trống")
    private String link;
    private String title;
    private AdvBannerType position;
    private String categoryId;
    private boolean active;
    private MultipartFile banner;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AdvBannerType getPosition() {
        return position;
    }

    public void setPosition(AdvBannerType position) {
        this.position = position;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public MultipartFile getBanner() {
        return banner;
    }

    public void setBanner(MultipartFile banner) {
        this.banner = banner;
    }
    
}
