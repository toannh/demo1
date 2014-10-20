package vn.chodientu.entity.form;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

/**
 * @since Jul 17, 2014
 * @author Account
 */
public class LandingForm {
    private String id;
    @NotEmpty(message = "Tên landing không được để trống")
    private String name;
    @NotEmpty(message = "Màu phải được chọn")
    private String color;
    private MultipartFile logo;
    private MultipartFile background;

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    
    public MultipartFile getLogo() {
        return logo;
    }

    public void setLogo(MultipartFile logo) {
        this.logo = logo;
    }

    public MultipartFile getBackground() {
        return background;
    }

    public void setBackground(MultipartFile background) {
        this.background = background;
    }
    
    
}
