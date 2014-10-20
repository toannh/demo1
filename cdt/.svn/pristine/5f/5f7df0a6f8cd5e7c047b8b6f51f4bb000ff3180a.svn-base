package vn.chodientu.entity.db;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import vn.chodientu.entity.enu.CashTransactionType;

@Document
public class CashHistory implements Serializable {

    @Id
    private String id;
    @Indexed
    private String userId;
    @Indexed
    private CashTransactionType type;
    private long balance;
    private long fineBalance;
    @Indexed
    private String objectId;
    private String url;
    @Indexed
    private long createTime;
    @Indexed
    private long updateTime;
    @Indexed
    private String cashTransactionId;
    private long turn;
    @Indexed
    private String admin;
    @Indexed
    private boolean fine;
    @Indexed
    private boolean unAppro;
    private String note;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isUnAppro() {
        return unAppro;
    }

    public void setUnAppro(boolean unAppro) {
        this.unAppro = unAppro;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public boolean isFine() {
        return fine;
    }

    public void setFine(boolean fine) {
        this.fine = fine;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getFineBalance() {
        return fineBalance;
    }

    public void setFineBalance(long fineBalance) {
        this.fineBalance = fineBalance;
    }

    public long getTurn() {
        return turn;
    }

    public void setTurn(long turn) {
        this.turn = turn;
    }

    public String getCashTransactionId() {
        return cashTransactionId;
    }

    public void setCashTransactionId(String cashTransactionId) {
        this.cashTransactionId = cashTransactionId;
    }

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

    public CashTransactionType getType() {
        return type;
    }

    public void setType(CashTransactionType type) {
        this.type = type;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

}
