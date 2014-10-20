package vn.chodientu.entity.data;

import java.io.Serializable;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @since Jun 16, 2014
 * @author Phuongdt
 */
@Document
public class NewsHomeBoxId implements Serializable {

    private String itemId;
    private String position;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}
