package vn.chodientu.entity.data;

import java.io.Serializable;
import org.springframework.data.annotation.Transient;

/**
 * @since Jun 10, 2014
 * @author Phuongdt
 */
public class CategorySubHome implements Serializable{

    @Transient
    private String id;
    @Transient
    private String name;
    @Transient
    private boolean primary;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

}
