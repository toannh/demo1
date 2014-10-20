package vn.chodientu.entity.db;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import vn.chodientu.entity.data.CrawlProperty;

@Document
public class ItemCrawl implements Serializable {

    @Id
    private String id;
    List<CrawlProperty> data;
    HashMap<String, String> error;
    @Indexed
    private long createTime;
    @Indexed
    private String itemId;
    @Indexed
    private String type;

    public HashMap<String, String> getError() {
        return error;
    }

    public void setError(HashMap<String, String> error) {
        this.error = error;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<CrawlProperty> getData() {
        return data;
    }

    public void setData(List<CrawlProperty> data) {
        this.data = data;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
