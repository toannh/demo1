package vn.chodientu.entity.db;

import java.util.List;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Thương hiệu / nhà sản xuất
 *
 * @author PhuGT
 * @since Jun 14, 2013
 */
@Document
public class Manufacturer implements java.io.Serializable {

    @Id
    private String id;
    @Indexed
    @NotEmpty(message = "Tên thương hiệu không được để trống")
    @Length(max = 128, message = "Tên thương hiệu không được dài quá 128 ký tự")
    private String name;
    @Indexed
    private boolean active;
    @Indexed
    private boolean global;
    @Length(max = 1024, message = "Mô tả thương hiệu không được dài quá 1024 ký tự")
    private String description;
    @Transient
    private String imageUrl;
    @Transient
    private List<String> MappedCategoryIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isGlobal() {
        return global;
    }

    public void setGlobal(boolean global) {
        this.global = global;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<String> getMappedCategoryIds() {
        return MappedCategoryIds;
    }

    public void setMappedCategoryIds(List<String> MappedCategoryIds) {
        this.MappedCategoryIds = MappedCategoryIds;
    }

}
