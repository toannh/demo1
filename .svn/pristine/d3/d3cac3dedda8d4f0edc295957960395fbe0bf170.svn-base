package vn.chodientu.entity.search;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;
import vn.chodientu.entity.db.Model;
import vn.chodientu.entity.db.ModelProperty;
import vn.chodientu.util.TextUtils;

/**
 * @since May 30, 2014
 * @author Phu
 */
@Document(indexName = "model")
public class ModelSearch {

    @Id
    private String id;
    @Field(type = FieldType.String)
    private String keyword;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String manufacturerId;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private List<String> categoryPath;
    @Field(type = FieldType.Integer, index = FieldIndex.not_analyzed)
    private long price;
    @Field(type = FieldType.Long, index = FieldIndex.not_analyzed)
    private long time;
    @Field(type = FieldType.Integer, index = FieldIndex.not_analyzed)
    private long itemCount;
    @Field(type = FieldType.Integer, index = FieldIndex.not_analyzed)
    private long viewCount;
    @Field(type = FieldType.Boolean, index = FieldIndex.not_analyzed)
    private boolean active;
    @Field(type = FieldType.Boolean, index = FieldIndex.not_analyzed)
    private boolean approved;
    @Field(type = FieldType.Nested)
    private List<PropertySearch> properties;

    public ModelSearch() {
    }

    public ModelSearch(String id, String keyword, String manufacturerId, List<String> categoryPath, long price, long time, long itemCount, long viewCount, boolean active, boolean approved, PropertySearch properties) {
        this.id = id;
        this.keyword = keyword;
        this.manufacturerId = manufacturerId;
        this.categoryPath = categoryPath;
        this.price = price;
        this.time = time;
        this.itemCount = itemCount;
        this.viewCount = viewCount;
        this.active = active;
        this.approved = approved;
    }

    public ModelSearch(Model model) {
        this.id = model.getId();
        this.keyword = "";
        if (model.getCategoryName() != null && !model.getCategoryName().equals("")) {
            this.keyword += model.getCategoryName() + " ";
        }
        if (model.getManufacturerName() != null && !model.getManufacturerName().equals("")) {
            this.keyword += model.getManufacturerName() + " ";
        }
        if (model.getName() != null && !model.getName().equals("")) {
            this.keyword += model.getName() + " ";
        }
        this.keyword = TextUtils.removeDiacritical(this.keyword);
        this.categoryPath = model.getCategoryPath();
        this.manufacturerId = model.getManufacturerId();
        this.price = model.getOldMinPrice() < model.getNewMinPrice() ? model.getOldMinPrice() : model.getOldMaxPrice();
        this.time = model.getUpdateTime();
        this.itemCount = model.getItemCount();
        this.viewCount = model.getViewCount();
        this.active = model.isActive();
        this.approved = model.isApproved();
        this.properties = new ArrayList<>();
        for (ModelProperty modelProperty : model.getProperties()) {
            for (String v : modelProperty.getCategoryPropertyValueIds()) {
                PropertySearch ps = new PropertySearch();
                ps.setName(modelProperty.getCategoryPropertyId());
                ps.setValue(Integer.parseInt(v));
                this.properties.add(ps);
            }
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public List<String> getCategoryPath() {
        return categoryPath;
    }

    public void setCategoryPath(List<String> categoryPath) {
        this.categoryPath = categoryPath;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getItemCount() {
        return itemCount;
    }

    public void setItemCount(long itemCount) {
        this.itemCount = itemCount;
    }

    public long getViewCount() {
        return viewCount;
    }

    public void setViewCount(long viewCount) {
        this.viewCount = viewCount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public List<PropertySearch> getProperties() {
        return properties;
    }

    public void setProperties(List<PropertySearch> properties) {
        this.properties = properties;
    }

}
