package vn.chodientu.entity.db;

import java.io.Serializable;
import java.util.List;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @since Jun 4, 2014
 * @author Phu
 */
@Document
public class ShopHomeItem implements Serializable {

    @Id
    private String id;
    @Indexed
    private String sellerId;
    @NotBlank(message = "Icon không được để trống")
    private String icon;
    @NotBlank(message = "Tiêu đề box không được để trống")
    private String name;
    private List<String> itemIds;
    @Indexed
    private int position;
    @Indexed
    private boolean active;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<String> getItemIds() {
        return itemIds;
    }

    public void setItemIds(List<String> itemIds) {
        this.itemIds = itemIds;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
