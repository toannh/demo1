package vn.chodientu.entity.db;

import java.io.Serializable;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Xu hướng tìm kiếm
 *
 * @since Aug 25, 2014
 * @author Phuongdt
 */
@Document
public class Footerkeyword implements Serializable {

    @Id
    private String id;
    @Indexed
    @NotBlank(message = "Bạn chưa nhập từ khóa")
    private String keyword;
    @Indexed
    @NotBlank(message = "Bạn chưa nhập từ đường link")
    private String url;
    private int position;
    @Indexed
    private boolean active;
    @Indexed
    private boolean common;

    public boolean isCommon() {
        return common;
    }

    public void setCommon(boolean common) {
        this.common = common;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
