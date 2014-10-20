package vn.chodientu.entity.db;

import java.io.Serializable;
import java.util.List;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @since Jun 2, 2014
 * @author Phu
 */
@Document
public class ShopNewsCategory implements Serializable {

    @Id
    @Length(max = 32, message = "Mã danh mục không được dài quá 32 ký tự")
    private String id;
    @Indexed
    private String userId;
    @Indexed
    @NotBlank(message = "Tên danh mục không được để trống")
    private String name;
    @Length(max = 1024, message = "Mô tả danh mục không được dài quá 1024 ký tự")
    private String description;
    @Indexed
    private int level;
    @Indexed
    @Length(max = 32, message = "Mã danh mục cha không được dài quá 32 ký tự")
    private String parentId;
    @Indexed
    private List<String> path;
    @Indexed
    private boolean active;
    @Indexed
    private int position;
    @Indexed
    private long createTime;
    @Indexed
    private long updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
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

}
