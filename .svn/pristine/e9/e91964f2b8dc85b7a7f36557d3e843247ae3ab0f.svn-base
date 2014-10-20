package vn.chodientu.entity.db;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import vn.chodientu.entity.enu.ShopBannerType;

/**
 * @since Jun 4, 2014
 * @author Phu
 */
@Document
public class ShopBanner implements Serializable {

    @Id
    private String id;
    @Indexed
    private String sellerId;
    private String title;
    private String url;
    private String embedCode;
    private ShopBannerType type;
    private boolean active;
    @Indexed
    private int position;
    @Transient
    private String image;

    public ShopBannerType getType() {
        return type;
    }

    public void setType(ShopBannerType type) {
        this.type = type;
    }

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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
