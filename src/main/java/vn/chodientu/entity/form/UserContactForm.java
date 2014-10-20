package vn.chodientu.entity.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Phuongdt
 */
public class UserContactForm {

    private String id;
    @NotBlank(message = "Họ tên không được để trống")
    private String title;
    @NotBlank(message = "Bạn chưa nhập phone")
    private String phone;
    @NotBlank(message = "Bạn chưa nhập email")
    @Email(message = "Email không đúng định dạng")
    private String email;
    private String yahoo;
    private String skype;
    private String sellerId;

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYahoo() {
        return yahoo;
    }

    public void setYahoo(String yahoo) {
        this.yahoo = yahoo;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
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
}
