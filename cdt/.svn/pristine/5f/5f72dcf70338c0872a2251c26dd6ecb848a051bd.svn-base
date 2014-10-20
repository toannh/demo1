package vn.chodientu.entity.db;

import java.io.Serializable;
import java.util.List;
import org.apache.lucene.search.SearcherLifetimeManager;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;

/**
 * @since Jun 2, 2014
 * @author Phu
 */
public class ShopNews implements Serializable {

    @Id
    private String id;
    @Indexed
    @NotBlank(message = "Tiêu đề tin không được để trống")
    private String title;
    @Indexed
    @NotBlank(message = "Mã danh mục không được để trống")
    private String categoryId;
    @Indexed
    private String userId;
    @NotBlank(message = "Chi tiết bài tin không được để trống")
    private String detail;
    @Indexed
    private boolean active;
    @Indexed
    private long createTime;
    @Indexed
    private long updateTime;
    private List<String> categoryPath;
    @Transient
    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public List<String> getCategoryPath() {
        return categoryPath;
    }

    public void setCategoryPath(List<String> categoryPath) {
        this.categoryPath = categoryPath;
    }

}
