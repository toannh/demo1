package vn.chodientu.entity.search;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @since May 30, 2014
 * @author Phu
 */
public class PropertySearch {

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String name;
    @Field(type = FieldType.Integer, index = FieldIndex.not_analyzed)
    private int value;

    public PropertySearch() {
    }

    public PropertySearch(String name, int value) {
        this.name = name;
        this.value = value;
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

}
