package vn.chodientu.entity.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by TheHoa
 */
public class ShopForm {

    private String userId;
    @NotBlank(message = "Địa chỉ shop không được để trống!")
    private String alias;
    @NotBlank(message = "Tên shop bắt buộc phải nhập!")
    private String title;
    @NotBlank(message = "Địa chỉ shop bắt buộc phải nhập!")
    private String address;
    @NotBlank(message = "Số điện thoại liên hệ của shop không được để trống!")
    private String phone;
    @NotBlank(message = "Địa chỉ email liên hệ của shop không được để trống!")
    @Email(message = "Email không đúng định dạng")
    private String email;
    private String cityId;
    private String districtId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
