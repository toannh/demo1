package vn.chodientu.entity.db;

import java.io.Serializable;
import java.util.List;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import vn.chodientu.entity.enu.EmailTemplate;

/**
 * @author thanhvv
 */
@Document
public class SellerEmailMarketing implements Serializable {

    @Id
    private String id;
    @Indexed
    @NotBlank(message = "Tên chương trình email marketing không được để trống")
    private String name;
    @Indexed
    private long createTime;
    private long updateTime;
    private long balance;
    private long senddone;
    @Indexed
    private long sendTime;
    private EmailTemplate template;
    @NotBlank(message = "Mã người bán không được để trống")
    @Indexed
    private String sellerId;
    @NotBlank(message = "Nội dung email không được để trống")
    private String content;
    private String fromEmail;
    private String fromName;
    private List<String> email;
    @Indexed
    private boolean done;
    @Indexed
    private boolean run;
    private String adminActive;
    @Indexed
    private boolean active;

    public String getAdminActive() {
        return adminActive;
    }

    public void setAdminActive(String adminActive) {
        this.adminActive = adminActive;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
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

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public long getSenddone() {
        return senddone;
    }

    public void setSenddone(long senddone) {
        this.senddone = senddone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
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

    public EmailTemplate getTemplate() {
        return template;
    }

    public void setTemplate(EmailTemplate template) {
        this.template = template;
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
