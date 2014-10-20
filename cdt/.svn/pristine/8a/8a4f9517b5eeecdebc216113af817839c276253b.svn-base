package vn.chodientu.entity.search;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @since May 29, 2014
 * @author Phu
 */
@Document(indexName = "category")
public class CategorySearch {

    @Id
    private String id;
    @Field(type = FieldType.String)
    private String keyword;
    @Field(type = FieldType.Boolean, index = FieldIndex.not_analyzed)
    private boolean active;
    @Field(type = FieldType.Boolean, index = FieldIndex.not_analyzed)
    private boolean leaf;

    public CategorySearch(String id, String name, boolean active, boolean leaf) {
        this.id = id;
        this.keyword = name;
        this.active = active;
        this.leaf = leaf;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

}
