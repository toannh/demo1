package vn.chodientu.entity.form;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Phuongdt
 */
public class ShopContactForm {

    @NotBlank(message = "Họ tên không được để trống")
    private String name;
    @NotBlank(message = "Bạn chưa nhập số điện thoại")
    private String phone;
    @NotBlank(message = "Bạn chưa nhập email")
    private String email;
    @NotBlank(message = "Bạn chưa nhập nội dung")
    private String content;
    private String alias;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}
