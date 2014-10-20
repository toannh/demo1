package vn.chodientu.entity.db;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import vn.chodientu.entity.enu.CpFunctionType;

@Document
public class CpFunction implements java.io.Serializable {

    @Id
    private String uri;
    @Indexed
    private String groupName;
    @Indexed
    private CpFunctionType type;
    @Indexed
    private String refUri;
    private String name;
    @Indexed
    private int position;
    @Indexed
    private boolean skip;
    @Transient
    private int groupPosition;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public CpFunctionType getType() {
        return type;
    }

    public void setType(CpFunctionType type) {
        this.type = type;
    }

    public String getRefUri() {
        return refUri;
    }

    public void setRefUri(String refUri) {
        this.refUri = refUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getGroupPosition() {
        return groupPosition;
    }

    public void setGroupPosition(int groupPosition) {
        this.groupPosition = groupPosition;
    }

}
