package vn.chodientu.entity.db;

import java.io.Serializable;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @since Jul 17, 2014
 * @author Account
 */
@Document
public class LandingCategory implements Serializable {
    @Id
    private String id;
    @Indexed
    @NotEmpty(message = "Mã landing không được để trống")
    private String landingId;
    @NotEmpty(message = "Tên danh mục không được để trống")
    private String name;
    @Indexed
    private int position;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLandingId() {
        return landingId;
    }

    public void setLandingId(String landingId) {
        this.landingId = landingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    
    
}
