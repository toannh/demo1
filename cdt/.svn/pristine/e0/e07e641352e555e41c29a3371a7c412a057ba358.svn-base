package vn.chodientu.entity.db;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import vn.chodientu.entity.enu.ModelApproveStatus;

/**
 * @since May 7, 2014
 * @author Phu
 */
@Document
public class ModelApprove implements Serializable {

    @Id
    private String modelId;
    @Indexed
    private String administratorId;
    @Indexed
    private ModelApproveStatus status;
    @Indexed
    private long updateTime;

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(String administratorId) {
        this.administratorId = administratorId;
    }

    public ModelApproveStatus getStatus() {
        return status;
    }

    public void setStatus(ModelApproveStatus status) {
        this.status = status;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

}
