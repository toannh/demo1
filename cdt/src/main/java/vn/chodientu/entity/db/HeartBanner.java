package vn.chodientu.entity.db;


import java.io.Serializable;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by phugt on 6/10/14.
 */
@Document
public class HeartBanner implements Serializable {
    @Id
    private String id;
    @NotBlank(message = "Nhập tên của Banner")
    private String name;
    @NotBlank(message = "Nhập link của sản phẩm")
    private String url;
    private int position;
    private boolean active;
    @Transient
    private String thumb;
    @Transient
    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    

    public String getThumb() {
        return thumb;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
