package vn.chodientu.entity.db;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import vn.chodientu.entity.enu.ImageType;

/**
 * @since May 28, 2014
 * @author Phu
 */
@Document
@CompoundIndex(name = "ImageTarget", def = "{'targetId':1,'type':1,'position':1}")
public class Image  implements Serializable {

    @Id
    private String id;
    @Indexed
    private String imageId;
    @Indexed
    private String targetId;
    @Indexed
    private int position;
    @Indexed
    private ImageType type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ImageType getType() {
        return type;
    }

    public void setType(ImageType type) {
        this.type = type;
    }

}
