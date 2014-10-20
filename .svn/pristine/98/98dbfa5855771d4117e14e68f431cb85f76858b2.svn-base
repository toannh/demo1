package vn.chodientu.entity.db;

import java.io.Serializable;
import java.util.List;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @since Jul 14, 2014
 * @author Account
 */
@Document
public class HotdealItem implements Serializable {
    @Id
    private String id;
    @Indexed
    @NotEmpty(message = "Mã sản phẩm không được để trống")
    private String itemId;
    @NotEmpty(message = "Danh mục không được để trống")
    private String hotdealCategoryId;
    @Indexed
    private List<String> hotdealCategoryPath;
    @Indexed
    private boolean home;
    @Indexed
    private boolean special;
    @Transient
    private Item item;
    @Transient
    private String image;
    
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

    public String getHotdealCategoryId() {
        return hotdealCategoryId;
    }

    public void setHotdealCategoryId(String hotdealCategoryId) {
        this.hotdealCategoryId = hotdealCategoryId;
    }

    public List<String> getHotdealCategoryPath() {
        return hotdealCategoryPath;
    }

    public void setHotdealCategoryPath(List<String> hotdealCategoryPath) {
        this.hotdealCategoryPath = hotdealCategoryPath;
    }

    public boolean isHome() {
        return home;
    }

    public void setHome(boolean home) {
        this.home = home;
    }

    public boolean isSpecial() {
        return special;
    }

    public void setSpecial(boolean special) {
        this.special = special;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    
}
