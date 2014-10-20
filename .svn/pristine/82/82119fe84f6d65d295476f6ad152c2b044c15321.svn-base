package vn.chodientu.entity.db;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import vn.chodientu.entity.data.CategorySubHome;

/**
 * @since Jun 16, 2014
 * @author Phuongdt
 */
@Document
public class FeaturedCategory implements Serializable {

    @Id
    private String id;
    private String categoryId;
    private int position;
    private String template;
    private boolean active;
    private String categoryName;
    @Transient
    private List<FeaturedCategorySub> categorySubs;
    @Transient
    private List<Item> items;
    @Transient
    private List<Model> models;
    @Transient
    private List<CategorySubHome> categorySubHome;
    @Transient
    private boolean leaf;

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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<FeaturedCategorySub> getCategorySubs() {
        return categorySubs;
    }

    public void setCategorySubs(List<FeaturedCategorySub> categorySubs) {
        this.categorySubs = categorySubs;
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

    public List<CategorySubHome> getCategorySubHome() {
        return categorySubHome;
    }

    public void setCategorySubHome(List<CategorySubHome> categorySubHome) {
        this.categorySubHome = categorySubHome;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

   

}
