package vn.chodientu.entity.db;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import vn.chodientu.entity.enu.PropertyOperator;
import vn.chodientu.entity.enu.PropertyType;

/**
 *
 * @author PhuGT
 * @since Jun 18, 2013
 */
@Document
public class CategoryProperty implements Serializable{

    @Id
    private String id;
    @Indexed
    private String categoryId;
    @Indexed
    private String name;
    @Indexed
    private int position;
    @Indexed
    private boolean filter;
    private PropertyType type;
    private PropertyOperator operator;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isFilter() {
        return filter;
    }

    public void setFilter(boolean filter) {
        this.filter = filter;
    }

    public PropertyType getType() {
        return type;
    }

    public void setType(PropertyType type) {
        this.type = type;
    }

    public PropertyOperator getOperator() {
        return operator;
    }

    public void setOperator(PropertyOperator operator) {
        this.operator = operator;
    }

}
