package vn.chodientu.entity.form;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Phuongdt
 */
public class HeartBannerForm {

    private String id;
    @NotBlank(message = "Nhập tên của Banner")
    private String name;
    @NotBlank(message = "Nhập link của sản phẩm")
    private String url;
    private int position;
    private boolean active;
    private MultipartFile image;
    private MultipartFile imageThumb;

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public MultipartFile getImageThumb() {
        return imageThumb;
    }

    public void setImageThumb(MultipartFile imageThumb) {
        this.imageThumb = imageThumb;
    }

}
