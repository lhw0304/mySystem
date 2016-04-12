package com.mode.entity;


import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by zhaoweiwei on 16/3/16.
 */
public class Transaction {

    @JsonView(View.Detail.class)
    private Integer id;

    private Integer userId;

    @JsonView(View.Detail.class)
    private String type;

    @JsonView(View.Detail.class)
    private Float amount;

    @JsonView(View.Detail.class)
    private String unit;

    private Integer status;

    @JsonView(View.Detail.class)
    private Float formerBalance;

    @JsonView(View.Detail.class)
    private String comment;

    @JsonView(View.Detail.class)
    private String paymentChannel;

    @JsonView(View.Detail.class)
    private String paymentAccount;

    @JsonView(View.Detail.class)
    private String paymentTransaction;

    private Long ctime;

    private Long utime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Float getFormerBalance() {
        return formerBalance;
    }

    public void setFormerBalance(Float formerBalance) {
        this.formerBalance = formerBalance;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPaymentChannel() {
        return paymentChannel;
    }

    public void setPaymentChannel(String paymentChannel) {
        this.paymentChannel = paymentChannel;
    }

    public String getPaymentAccount() {
        return paymentAccount;
    }

    public void setPaymentAccount(String paymentAccount) {
        this.paymentAccount = paymentAccount;
    }

    public String getPaymentTransaction() {
        return paymentTransaction;
    }

    public void setPaymentTransaction(String paymentTransaction) {
        this.paymentTransaction = paymentTransaction;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    public Long getUtime() {
        return utime;
    }

    public void setUtime(Long utime) {
        this.utime = utime;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", userId=" + userId +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", unit='" + unit + '\'' +
                ", status=" + status +
                ", formerBalance=" + formerBalance +
                ", comment='" + comment + '\'' +
                ", paymentChannel='" + paymentChannel + '\'' +
                ", paymentAccount='" + paymentAccount + '\'' +
                ", paymentTransaction='" + paymentTransaction + '\'' +
                ", ctime=" + ctime +
                ", utime=" + utime +
                '}';
    }
}
