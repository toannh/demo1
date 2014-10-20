package vn.chodientu.entity.db;

import java.io.Serializable;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Email implements Serializable {

    @Id
    @NotBlank(message = "Địa chỉ email không được để trống!")
    @Pattern(message = "Địa chỉ email chưa đúng định dạng!", regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private String email;
    private long createTime;
    private long updateTime;
    private long lastSend;
    @Indexed
    private boolean pushC;

    public boolean isPushC() {
        return pushC;
    }

    public void setPushC(boolean pushC) {
        this.pushC = pushC;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public long getLastSend() {
        return lastSend;
    }

    public void setLastSend(long lastSend) {
        this.lastSend = lastSend;
    }

}
