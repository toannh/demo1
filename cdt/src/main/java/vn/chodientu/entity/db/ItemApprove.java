package vn.chodientu.entity.db;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import vn.chodientu.entity.enu.ItemApproveStatus;
import vn.chodientu.entity.enu.ItemUpdaterType;

/**
 * @since May 9, 2014
 * @author Phu
 */
@Document
public class ItemApprove implements Serializable {

    @Id
    private String id;
    @Indexed
    private String itemId;
    @Indexed
    private ItemApproveStatus status;
    @Indexed
    private String updaterId;
    @Indexed
    private String lastUpdaterId;
    @Indexed
    private ItemUpdaterType updaterType;
    @Indexed
    private long time;
    private String message;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public ItemApproveStatus getStatus() {
        return status;
    }

    public void setStatus(ItemApproveStatus status) {
        this.status = status;
    }

    public String getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(String updaterId) {
        this.updaterId = updaterId;
    }

    public String getLastUpdaterId() {
        return lastUpdaterId;
    }

    public void setLastUpdaterId(String lastUpdaterId) {
        this.lastUpdaterId = lastUpdaterId;
    }

    public ItemUpdaterType getUpdaterType() {
        return updaterType;
    }

    public void setUpdaterType(ItemUpdaterType updaterType) {
        this.updaterType = updaterType;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
