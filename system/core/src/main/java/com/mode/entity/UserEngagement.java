package com.mode.entity;


import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by zhaoweiwei on 16/3/16.
 */
public class UserEngagement {

    private Long id;

    private Integer userId;

    @JsonView(View.Detail.class)
    private String action;

    @JsonView(View.Detail.class)
    private String objectType;

    @JsonView(View.Detail.class)
    private Integer objectId;

    private Long ctime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        return "UserEngagement{" +
                "id=" + id +
                ", userId=" + userId +
                ", action='" + action + '\'' +
                ", objectType='" + objectType + '\'' +
                ", objectId=" + objectId +
                ", ctime=" + ctime +
                '}';
    }
}
