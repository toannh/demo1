package vn.chodientu.entity.input;

import java.io.Serializable;
import vn.chodientu.entity.enu.ItemWarningType;

public class ItemWarningSearch implements Serializable {

    private ItemWarningType type;
    private int pageIndex;
    private int pageSize;

    public ItemWarningType getType() {
        return type;
    }

    public void setType(ItemWarningType type) {
        this.type = type;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
