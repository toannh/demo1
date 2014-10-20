package vn.chodientu.entity.data;

import java.util.List;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.db.ItemProperty;

/**
 * @since May 8, 2014
 * @author Phu
 */
public class ItemMig {

    private Item item;
    private List<ItemProperty> properties;
    private String detail;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<ItemProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<ItemProperty> properties) {
        this.properties = properties;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

}
