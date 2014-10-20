package vn.chodientu.entity.db;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import vn.chodientu.entity.data.CategoryBannerHome;
import vn.chodientu.entity.data.CategoryItemHome;
import vn.chodientu.entity.data.CategoryManufacturerHome;

/**
 * @since Jun 16, 2014
 * @author Phuongdt
 */
@Document
public class FeaturedCategorySub implements Serializable {

    @Id
    private String id;
    private String categorySubId;
    private String categorySubName;
    private String featuredCategororyId;
    private int position;
    private boolean active;
    private List<CategoryBannerHome> categoryBannerHomes;
    private List<CategoryItemHome> categoryItemHomes;
    private List<CategoryManufacturerHome> categoryManufacturerHomes;
    @Transient
    private String template;
    @Transient
    private List<Item> items;
    @Transient
    private List<Model> models;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategorySubId() {
        return categorySubId;
    }

    public void setCategorySubId(String categorySubId) {
        this.categorySubId = categorySubId;
    }

    public String getCategorySubName() {
        return categorySubName;
    }

    public void setCategorySubName(String categorySubName) {
        this.categorySubName = categorySubName;
    }

    public String getFeaturedCategororyId() {
        return featuredCategororyId;
    }

    public void setFeaturedCategororyId(String featuredCategororyId) {
        this.featuredCategororyId = featuredCategororyId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<CategoryBannerHome> getCategoryBannerHomes() {
        return categoryBannerHomes;
    }

    public void setCategoryBannerHomes(List<CategoryBannerHome> categoryBannerHomes) {
        this.categoryBannerHomes = categoryBannerHomes;
    }

    public List<CategoryItemHome> getCategoryItemHomes() {
        return categoryItemHomes;
    }

    public void setCategoryItemHomes(List<CategoryItemHome> categoryItemHomes) {
        this.categoryItemHomes = categoryItemHomes;
    }

    public List<CategoryManufacturerHome> getCategoryManufacturerHomes() {
        return categoryManufacturerHomes;
    }

    public void setCategoryManufacturerHomes(List<CategoryManufacturerHome> categoryManufacturerHomes) {
        this.categoryManufacturerHomes = categoryManufacturerHomes;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

}
