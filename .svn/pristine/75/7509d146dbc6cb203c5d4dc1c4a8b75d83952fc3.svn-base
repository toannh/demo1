package vn.chodientu.entity.form;

import java.util.List;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

/**
 * @since Jun 10, 2014
 * @author Phu
 */

public class CategoryAliasForm {

    private String id;
    @NotEmpty(message = "Danh mục không được để trống")
    private String categoryId;
    @NotEmpty(message = "Tiêu đề không được để trống")
    private String title;
    @NotEmpty(message = "Phụ đề không được để trống")
    private String subTitle;
    @NotEmpty(message = "Url banner không được để trống")
    private String bannerUrl;
    @NotEmpty(message = "Url banner không được để trống")
    private String bannerUrl1;
    @NotEmpty(message = "Url banner không được để trống")
    private String bannerUrl2;
    @NotEmpty(message = "Url banner không được để trống")
    private String bannerUrl3;
    private boolean active;
    private MultipartFile image;
    private MultipartFile image1;
    private MultipartFile image2;
    private MultipartFile image3;
    
    private List<String> manufacturerIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getBannerUrl1() {
        return bannerUrl1;
    }

    public void setBannerUrl1(String bannerUrl1) {
        this.bannerUrl1 = bannerUrl1;
    }

    public String getBannerUrl2() {
        return bannerUrl2;
    }

    public void setBannerUrl2(String bannerUrl2) {
        this.bannerUrl2 = bannerUrl2;
    }

    public String getBannerUrl3() {
        return bannerUrl3;
    }

    public void setBannerUrl3(String bannerUrl3) {
        this.bannerUrl3 = bannerUrl3;
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

    public MultipartFile getImage1() {
        return image1;
    }

    public void setImage1(MultipartFile image1) {
        this.image1 = image1;
    }

    public MultipartFile getImage2() {
        return image2;
    }

    public void setImage2(MultipartFile image2) {
        this.image2 = image2;
    }

    public MultipartFile getImage3() {
        return image3;
    }

    public void setImage3(MultipartFile image3) {
        this.image3 = image3;
    }

    public List<String> getManufacturerIds() {
        return manufacturerIds;
    }

    public void setManufacturerIds(List<String> manufacturerIds) {
        this.manufacturerIds = manufacturerIds;
    }

    
}
