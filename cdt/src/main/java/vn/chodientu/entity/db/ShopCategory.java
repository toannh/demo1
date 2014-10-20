package vn.chodientu.entity.db;

import java.io.Serializable;
import java.util.List;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ShopCategory implements Serializable {

    @Id
    @Length(max = 32, message = "Mã danh mục không được dài quá 32 ký tự")
    private String id;
    @Indexed
    @Length(max = 32, message = "Mã danh mục cha không được dài quá 32 ký tự")
    private String parentId;
    @Indexed
    private String userId;
    @NotEmpty(message = "Tên danh mục không được để trống")
    @Length(max = 128, message = "Tên danh mục không được dài quá 128 ký tự")
    private String name;
    @Length(max = 1024, message = "Mô tả danh mục không được dài quá 1024 ký tự")
    private String description;
    @Indexed
    private int level;
    private int weight;
    @Indexed
    private List<String> path;
    @Indexed
    private int position;
    @Indexed
    private boolean leaf;
    @Indexed
    private boolean active;
    @Indexed
    private boolean home;

    public String getId() {
        return id;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public boolean isHome() {
        return home;
    }

    public void setHome(boolean home) {
        this.home = home;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
