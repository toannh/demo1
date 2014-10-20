package vn.chodientu.entity.search;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

/**
 * @since May 30, 2014
 * @author Phu
 */
@Document(indexName = "manufacturer")
public class ManufacturerSearch implements Serializable {

    @Id
    private String id;
    @Field(type = FieldType.String)
    private String keyword;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String name;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private List<String> categoryIds;
    @Field(type = FieldType.Boolean, index = FieldIndex.not_analyzed)
    private boolean active;
    @Field(type = FieldType.Boolean, index = FieldIndex.not_analyzed)
    private boolean global;

    public ManufacturerSearch() {
    }

    public ManufacturerSearch(String id, String keyword, String name, List<String> categoryIds, boolean active, boolean global) {
        this.id = id;
        this.keyword = keyword;
        this.name = name;
        this.categoryIds = categoryIds;
        this.active = active;
        this.global = global;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<String> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isGlobal() {
        return global;
    }

    public void setGlobal(boolean global) {
        this.global = global;
    }

}
