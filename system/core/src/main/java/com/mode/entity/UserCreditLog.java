package com.mode.entity;


import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by zhaoweiwei on 16/3/16.
 */
public class UserCreditLog {

    @JsonView(View.Detail.class)
    private Integer id;

    private Integer userId;

    @JsonView(View.Detail.class)
    private Integer taskId;

    @JsonView(View.Detail.class)
    private String type;

    @JsonView(View.Detail.class)
    private Integer credit;

    @JsonView(View.Detail.class)
    private Integer balance;

    @JsonView(View.Detail.class)
    private String remarks;

    @JsonView(View.Detail.class)
    private Long ctime;

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

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        return "UserCreditLog{" +
                "id=" + id +
                ", userId=" + userId +
                ", taskId=" + taskId +
                ", type='" + type + '\'' +
                ", credit=" + credit +
                ", balance=" + balance +
                ", remarks='" + remarks + '\'' +
                ", ctime=" + ctime +
                '}';
    }
}
