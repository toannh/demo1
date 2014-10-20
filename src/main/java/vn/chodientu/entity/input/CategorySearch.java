package vn.chodientu.entity.input;

import java.io.Serializable;

/**
 *
 * @author thanhvv
 */
public class CategorySearch implements Serializable {

    private String keyword;
    private int leaf;
    private int active;

    public int getLeaf() {
        return leaf;
    }

    public void setLeaf(int leaf) {
        this.leaf = leaf;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}
