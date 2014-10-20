package vn.chodientu.entity.db;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import vn.chodientu.entity.enu.ModelLogStatus;

/**
 * Trạng thái Model sản phẩm
 *
 * @author thanhvv
 * @since 07 - 05 , 2013
 */
@Document
public class ModelLog implements Serializable {

    @Id
    private String id;
    @Indexed
    private String modelId;
    @Indexed
    private ModelLogStatus status;
    @Indexed
    private String updaterId;
    @Indexed
    private String lastUpdaterId;
    @Indexed
    private String nextUpdaterId;
    @Indexed
    private long time;
    private String message;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public ModelLogStatus getStatus() {
        return status;
    }

    public void setStatus(ModelLogStatus status) {
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

    public String getNextUpdaterId() {
        return nextUpdaterId;
    }

    public void setNextUpdaterId(String nextUpdaterId) {
        this.nextUpdaterId = nextUpdaterId;
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
