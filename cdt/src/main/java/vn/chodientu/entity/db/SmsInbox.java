package vn.chodientu.entity.db;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import vn.chodientu.entity.enu.SmsInboxType;

@Document
public class SmsInbox implements Serializable {

    @Id
    private String id;
    @Indexed
    private String userId;
    @Indexed
    private String itemId;
    @Indexed
    private String username;
    private String message;
    @Indexed
    private String messageId;
    @Indexed
    private String phone;
    @Indexed
    private String receiver; //Đầu số
    @Indexed
    private long createTime;
    private String responMessage;
    private long responTime;
    @Indexed
    private SmsInboxType type;
    private boolean success;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getResponMessage() {
        return responMessage;
    }

    public void setResponMessage(String responMessage) {
        this.responMessage = responMessage;
    }

    public long getResponTime() {
        return responTime;
    }

    public void setResponTime(long responTime) {
        this.responTime = responTime;
    }

    public SmsInboxType getType() {
        return type;
    }

    public void setType(SmsInboxType type) {
        this.type = type;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
