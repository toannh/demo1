package vn.chodientu.entity.db;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ItemProperty implements Serializable {

    @Id
    private String id;
    @Indexed
    private String itemId;
    @Indexed
    private String categoryPropertyId;
    @Indexed
    private List<String> categoryPropertyValueIds;
    private String inputValue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getCategoryPropertyId() {
        return categoryPropertyId;
    }

    public void setCategoryPropertyId(String categoryPropertyId) {
        this.categoryPropertyId = categoryPropertyId;
    }

    public List<String> getCategoryPropertyValueIds() {
        return categoryPropertyValueIds;
    }

    public void setCategoryPropertyValueIds(List<String> categoryPropertyValueIds) {
        this.categoryPropertyValueIds = categoryPropertyValueIds;
    }

    public String getInputValue() {
        return inputValue;
    }

    public void setInputValue(String inputValue) {
        this.inputValue = inputValue;
    }

    

}
