package vn.chodientu.entity.form;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @since Jun 16, 2014
 * @author Phuongdt
 */
public class NewsHomeBoxForm {

    @NotBlank(message = "Mời bạn nhập ID sản phẩm")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
