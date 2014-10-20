package vn.chodientu.entity.form;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @since Jul/05/2014
 * @author PhucTd
 */
public class ShopNewsCategoryForm {

    private String id;
    @NotBlank(message = "Tên danh mục không được bỏ trống.")
    private String name;
    private String parentId;

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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

   
}
