package vn.chodientu.entity.db;

import java.io.Serializable;
import java.util.List;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Danh mục
 *
 * @author PhuGT
 * @since Jun 14, 2013
 */
@Document
public class Category implements Serializable {

    @Id
    @Length(max = 32, message = "Mã danh mục không được dài quá 32 ký tự")
    private String id;
    @Indexed
    @Length(max = 32, message = "Mã danh mục cha không được dài quá 32 ký tự")
    private String parentId;
    @Indexed
    @NotEmpty(message = "Tên danh mục không được để trống")
    @Length(max = 128, message = "Tên danh mục không được dài quá 128 ký tự")
    private String name;
    @Length(max = 1024, message = "Mô tả danh mục không được dài quá 1024 ký tự")
    private String description;
    private String ebayCategoryId;
    private String ebayKeyword;
    private String metaKeyword;
    private String metaDescription;
    private String icon;
    private int weight;
    @Indexed
    private int level;
    @Indexed
    private List<String> path;
    @Indexed
    private int position;
    @Indexed
    private boolean leaf;
    @Indexed
    private boolean active;
    @Transient
    private List<Category> categoris;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<Category> getCategoris() {
        return categoris;
    }

    public void setCategoris(List<Category> categoris) {
        this.categoris = categoris;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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

    public String getEbayCategoryId() {
        return ebayCategoryId;
    }

    public void setEbayCategoryId(String ebayCategoryId) {
        this.ebayCategoryId = ebayCategoryId;
    }

    public String getEbayKeyword() {
        return ebayKeyword;
    }

    public void setEbayKeyword(String ebayKeyword) {
        this.ebayKeyword = ebayKeyword;
    }

    public String getMetaKeyword() {
        return metaKeyword;
    }

    public void setMetaKeyword(String metaKeyword) {
        this.metaKeyword = metaKeyword;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
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
