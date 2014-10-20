package vn.chodientu.entity.output;

import java.io.Serializable;
import java.util.List;
import vn.chodientu.entity.db.CategoryProperty;
import vn.chodientu.entity.db.CategoryPropertyValue;
import vn.chodientu.entity.db.Model;

/**
 * @author thanhvv
 */
public class ModelInfo implements Serializable {

    private Model model;
    private List<ModelProperty> propertis;

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public List<ModelProperty> getPropertis() {
        return propertis;
    }

    public void setPropertis(List<ModelProperty> propertis) {
        this.propertis = propertis;
    }

    public static class ModelProperty {

        private CategoryProperty categoryProperty;
        private List<CategoryPropertyValue> categoryPropertyValue;
        private String inputValue;

        public CategoryProperty getCategoryProperty() {
            return categoryProperty;
        }

        public void setCategoryProperty(CategoryProperty categoryProperty) {
            this.categoryProperty = categoryProperty;
        }

        public List<CategoryPropertyValue> getCategoryPropertyValue() {
            return categoryPropertyValue;
        }

        public void setCategoryPropertyValue(List<CategoryPropertyValue> categoryPropertyValue) {
            this.categoryPropertyValue = categoryPropertyValue;
        }

        public String getInputValue() {
            return inputValue;
        }

        public void setInputValue(String inputValue) {
            this.inputValue = inputValue;
        }

    }

}
