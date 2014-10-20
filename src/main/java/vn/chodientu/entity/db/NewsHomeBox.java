package vn.chodientu.entity.db;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @since Jun 16, 2014
 * @author Phuongdt
 */
@Document
public class NewsHomeBox implements Serializable {

    @Id
    private String id;
    private List<String> itemIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getItemIds() {
        return itemIds;
    }

    public void setItemIds(List<String> itemIds) {
        this.itemIds = itemIds;
    }

}
