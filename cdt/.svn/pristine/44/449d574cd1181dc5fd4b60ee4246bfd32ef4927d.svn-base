package vn.chodientu.entity.db;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import vn.chodientu.entity.enu.SmsOutboxType;

/**
 * @since May 17, 2014
 * @author Phu
 */
@Document
public class SmsOutbox implements Serializable {

    @Id
    public String id;
    @Indexed
    public SmsOutboxType type;
    @Indexed
    public String receiver;
    public String content;
    public int smsType;
    public int smsTemp;
    @Indexed
    private boolean sent;
    @Indexed
    private boolean success;
    private String response;
    @Indexed
    private long time;
    @Indexed
    private long sentTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SmsOutboxType getType() {
        return type;
    }

    public void setType(SmsOutboxType type) {
        this.type = type;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSmsType() {
        return smsType;
    }

    public void setSmsType(int smsType) {
        this.smsType = smsType;
    }

    public int getSmsTemp() {
        return smsTemp;
    }

    public void setSmsTemp(int smsTemp) {
        this.smsTemp = smsTemp;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getSentTime() {
        return sentTime;
    }

    public void setSentTime(long sentTime) {
        this.sentTime = sentTime;
    }

}
