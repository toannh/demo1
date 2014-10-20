package vn.chodientu.entity.db;

import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.Max;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author thanhvv
 */
@Document
public class SellerSmsMarketing implements Serializable {

    @Id
    private String id;
    @NotBlank(message = "Tên chương trình sms marketing không được để trống")
    @Indexed
    private String name;
    @Indexed
    private long createTime;
    @Indexed
    private long updateTime;
    @Indexed
    private long sendTime;
    private long balance;
    private long senddone;
    @Indexed
    @NotBlank(message = "Mã người bán không được để trống")
    private String sellerId;
    @NotBlank(message = "Nội dung tin nhắn không được để trống")
    @Length(max = 130, message = "Nội dung tin nhắn tối đa là 130 ký tự")
    private String content;
    private List<String> phone;
    @Indexed
    private boolean done;
    @Indexed
    private boolean run;
    @Indexed
    private boolean active;
    private String adminActive;

    public String getAdminActive() {
        return adminActive;
    }

    public void setAdminActive(String adminActive) {
        this.adminActive = adminActive;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSenddone() {
        return senddone;
    }

    public void setSenddone(long senddone) {
        this.senddone = senddone;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getPhone() {
        return phone;
    }

    public void setPhone(List<String> phone) {
        this.phone = phone;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }

}
