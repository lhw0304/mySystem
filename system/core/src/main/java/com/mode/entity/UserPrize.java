package com.mode.entity;


import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by zhaoweiwei on 16/3/16.
 */
public class UserPrize {

    private Integer id;

    @JsonView(View.Detail.class)
    private String type;

    private Integer userId;

    @JsonView(View.Detail.class)
    private Integer sourceId;

    @JsonView(View.Detail.class)
    private Integer usd;

    @JsonView(View.Detail.class)
    private String address;

    @JsonView(View.Detail.class)
    private String comment;

    @JsonView(View.Detail.class)
    private Integer status;

    private Long ctime;

    private Long utime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public Integer getUsd() {
        return usd;
    }

    public void setUsd(Integer usd) {
        this.usd = usd;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        return "UserPrize{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", userId=" + userId +
                ", sourceId=" + sourceId +
                ", usd=" + usd +
                ", address='" + address + '\'' +
                ", comment='" + comment + '\'' +
                ", status=" + status +
                ", ctime=" + ctime +
                ", utime=" + utime +
                '}';
    }
}
