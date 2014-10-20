package vn.chodientu.entity.data;

import java.util.List;
import vn.chodientu.entity.db.Category;
import vn.chodientu.entity.db.CategoryProperty;
import vn.chodientu.entity.db.CategoryPropertyValue;

/**
 * @since May 8, 2014
 * @author Phu
 */
public class CategoryMig {

    private Category category;
    private List<CategoryProperty> categoryProperty;
    private List<CategoryPropertyValue> categoryPropertyValue;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<CategoryProperty> getCategoryProperty() {
        return categoryProperty;
    }

    public void setCategoryProperty(List<CategoryProperty> categoryProperty) {
        this.categoryProperty = categoryProperty;
    }

    public List<CategoryPropertyValue> getCategoryPropertyValue() {
        return categoryPropertyValue;
    }

    public void setCategoryPropertyValue(List<CategoryPropertyValue> categoryPropertyValue) {
        this.categoryPropertyValue = categoryPropertyValue;
    }

}
