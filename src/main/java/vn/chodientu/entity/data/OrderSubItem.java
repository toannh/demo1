package vn.chodientu.entity.data;

import java.io.Serializable;
import java.util.List;

public class OrderSubItem implements Serializable {

    private String alias;
    private int quantity;
    private String colorId;
    private String colorName;
    private String colorValueId;
    private String colorValueName;
    private String sizeId;
    private String sizeName;
    private String sizeValueId;
    private String sizeValueName;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getColorId() {
        return colorId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getColorValueId() {
        return colorValueId;
    }

    public void setColorValueId(String colorValueId) {
        this.colorValueId = colorValueId;
    }

    public String getColorValueName() {
        return colorValueName;
    }

    public void setColorValueName(String colorValueName) {
        this.colorValueName = colorValueName;
    }

    public String getSizeId() {
        return sizeId;
    }

    public void setSizeId(String sizeId) {
        this.sizeId = sizeId;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public String getSizeValueId() {
        return sizeValueId;
    }

    public void setSizeValueId(String sizeValueId) {
        this.sizeValueId = sizeValueId;
    }

    public String getSizeValueName() {
        return sizeValueName;
    }

    public void setSizeValueName(String sizeValueName) {
        this.sizeValueName = sizeValueName;
    }

}
