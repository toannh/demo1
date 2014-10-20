package vn.chodientu.entity.db;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author PhuGT
 * @since Jun 18, 2013
 */
@Document
public class CategoryPropertyValue implements Serializable{

    @Id
    private String id;
    @Indexed
    private String categoryId;
    @Indexed
    private String categoryPropertyId;
    @Indexed
    private String name;
    private int value;
    @Indexed
    private int position;
    @Indexed
    private boolean filter;

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

    public String getCategoryPropertyId() {
        return categoryPropertyId;
    }

    public void setCategoryPropertyId(String categoryPropertyId) {
        this.categoryPropertyId = categoryPropertyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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

}
