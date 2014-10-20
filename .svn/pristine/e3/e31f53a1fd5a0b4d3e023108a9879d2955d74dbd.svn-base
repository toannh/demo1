package vn.chodientu.entity.db;

import java.io.Serializable;
import java.util.List;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import vn.chodientu.entity.data.CategoryAliasTopic;

/**
 * @since Jun 10, 2014
 * @author Phu
 */
@Document
public class CategoryAlias implements Serializable {

    @Id
    private String id;
    @Indexed
    @NotEmpty(message = "Danh mục không được để trống")
    private String categoryId;
    @NotEmpty(message = "Tiêu đề không được để trống")
    private String title;
    private String subTitle;
    @Indexed
    private List<String> manufacturerIds;
    private String bannerUrl;
    private String bannerUrl1;
    private String bannerUrl2;
    private String bannerUrl3;
    private List<CategoryAliasTopic> topics;
    @Indexed
    private boolean active;
    @Indexed
    private int position;
    @Transient
    private String image;
    @Transient
    private String image1;
    @Transient
    private String image2;
    @Transient
    private String image3;
    @Transient
    private String categoryName;
    @Transient
    private List<Manufacturer> manufacturers;

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

    public List<String> getManufacturerIds() {
        return manufacturerIds;
    }

    public void setManufacturerIds(List<String> manufacturerIds) {
        this.manufacturerIds = manufacturerIds;
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

    public List<CategoryAliasTopic> getTopics() {
        return topics;
    }

    public void setTopics(List<CategoryAliasTopic> topics) {
        this.topics = topics;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Manufacturer> getManufacturers() {
        return manufacturers;
    }

    public void setManufacturers(List<Manufacturer> manufacturers) {
        this.manufacturers = manufacturers;
    }

}
