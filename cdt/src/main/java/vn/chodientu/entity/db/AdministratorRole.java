package vn.chodientu.entity.db;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class AdministratorRole implements Serializable {

    @Id
    private String id;
    @Indexed
    private String administratorId;
    @Indexed
    private String functionUri;
    @Indexed
    private String refUri;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(String administratorId) {
        this.administratorId = administratorId;
    }

    public String getFunctionUri() {
        return functionUri;
    }

    public void setFunctionUri(String functionUri) {
        this.functionUri = functionUri;
    }

    public String getRefUri() {
        return refUri;
    }

    public void setRefUri(String refUri) {
        this.refUri = refUri;
    }

}
