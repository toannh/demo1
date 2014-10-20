package vn.chodientu.entity.output;

import java.io.Serializable;
import vn.chodientu.entity.enu.PropertyOperator;

/**
 *
 * @author ThienPhu
 * @since Jun 25, 2013
 */
public class PropertyValueHistogram implements Serializable{

    private String name;
    private int value;
    private int count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
