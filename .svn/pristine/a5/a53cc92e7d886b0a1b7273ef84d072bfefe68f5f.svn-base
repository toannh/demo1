package vn.chodientu.entity.db;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import vn.chodientu.entity.enu.CashTransactionType;
import vn.chodientu.entity.enu.PaymentMethod;

@Document
public class CashTransaction implements Serializable {

    @Id
    private String id;
    private String userId;
    private CashTransactionType type;
    private String spentId;
    private int spentQuantity;
    private long amount;
    private double discount;
    private long newBalance;
    private String nlToken;
    private String nlCheckoutUrl;
    private String nlTransactionId;
    private int nlStatus;
    private long time;
    @Indexed
    private long nlPayTime;
    @Indexed
    private long updateTime;
    private PaymentMethod paymentMethod;
    @Indexed
    private String support;
    private String note;
    @Transient
    private long totalXeng;
    @Transient
    private String userName;

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getId() {
        return id;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
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

    public String getSpentId() {
        return spentId;
    }

    public void setSpentId(String spentId) {
        this.spentId = spentId;
    }

    public int getSpentQuantity() {
        return spentQuantity;
    }

    public void setSpentQuantity(int spentQuantity) {
        this.spentQuantity = spentQuantity;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(long newBalance) {
        this.newBalance = newBalance;
    }

    public String getNlToken() {
        return nlToken;
    }

    public void setNlToken(String nlToken) {
        this.nlToken = nlToken;
    }

    public String getNlCheckoutUrl() {
        return nlCheckoutUrl;
    }

    public void setNlCheckoutUrl(String nlCheckoutUrl) {
        this.nlCheckoutUrl = nlCheckoutUrl;
    }

    public String getNlTransactionId() {
        return nlTransactionId;
    }

    public void setNlTransactionId(String nlTransactionId) {
        this.nlTransactionId = nlTransactionId;
    }

    public int getNlStatus() {
        return nlStatus;
    }

    public void setNlStatus(int nlStatus) {
        this.nlStatus = nlStatus;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getNlPayTime() {
        return nlPayTime;
    }

    public void setNlPayTime(long nlPayTime) {
        this.nlPayTime = nlPayTime;
    }

    public long getTotalXeng() {
        return totalXeng;
    }

    public void setTotalXeng(long totalXeng) {
        this.totalXeng = totalXeng;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

}
