package vn.chodientu.entity.db;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import vn.chodientu.entity.enu.Gender;

@Document
public class User implements Serializable {

    @Id
    private String id;
    @Indexed
    private String username;
    private String password;
    private String salt;
    private String name;
    @Indexed
    private String email;
    @Indexed
    private String phone;
    private String address;
    @Indexed
    private String cityId;
    @Indexed
    private String districtId;
    private Gender gender;
    private long dob;
    @Indexed
    private long joinTime;
    @Indexed
    private long updateTime;
    @Indexed
    private long emailVerifiedTime;
    @Indexed
    private long phoneVerifiedTime;
    @Indexed
    private boolean active;
    @Indexed
    private boolean emailVerified;
    @Indexed
    private boolean phoneVerified;
    private String yahoo;
    private String skype;
    @Indexed
    private String rememberKey;
    @Indexed
    private boolean pushC;
    @Transient
    private String avatar;
    @Transient
    private long balance;
    @Transient
    private Shop shop;

    public boolean isPushC() {
        return pushC;
    }

    public void setPushC(boolean pushC) {
        this.pushC = pushC;
    }

    public long getEmailVerifiedTime() {
        return emailVerifiedTime;
    }

    public void setEmailVerifiedTime(long emailVerifiedTime) {
        this.emailVerifiedTime = emailVerifiedTime;
    }

    public long getPhoneVerifiedTime() {
        return phoneVerifiedTime;
    }

    public void setPhoneVerifiedTime(long phoneVerifiedTime) {
        this.phoneVerifiedTime = phoneVerifiedTime;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public boolean isPhoneVerified() {
        return phoneVerified;
    }

    public void setPhoneVerified(boolean phoneVerified) {
        this.phoneVerified = phoneVerified;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRememberKey() {
        return rememberKey;
    }

    public void setRememberKey(String rememberKey) {
        this.rememberKey = rememberKey;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public long getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(long joinTime) {
        this.joinTime = joinTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public long getDob() {
        return dob;
    }

    public void setDob(long dob) {
        this.dob = dob;
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

}
