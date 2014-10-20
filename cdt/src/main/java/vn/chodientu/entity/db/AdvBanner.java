package vn.chodientu.entity.db;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import vn.chodientu.entity.enu.AdvBannerType;

/**
 * @since Aug 14, 2014
 * @author Account
 */
public class AdvBanner implements Serializable{
    @Id
    private String id;
    private String link;
    private String title;
    @Indexed
    private AdvBannerType position;
    @Indexed
    private String categoryId;
    @Indexed
    private boolean active;
    @Indexed
    private long createTime;
    
    @Transient
    private String banner;
    @Transient
    private String categoryName;

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

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
