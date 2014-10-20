package vn.chodientu.entity.data;

import java.util.List;
import vn.chodientu.entity.db.Model;
import vn.chodientu.entity.db.ModelProperty;

/**
 * @since May 8, 2014
 * @author Phu
 */
public class ModelMig {

    private Model model;
    private List<ModelProperty> properties;

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public List<ModelProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<ModelProperty> properties) {
        this.properties = properties;
    }

}
