package com.mode.entity;


import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by zhaoweiwei on 16/3/16.
 */
public class UserComment {

    private Integer id;

    @JsonView(View.Detail.class)
    private Integer userId;

    @JsonView(View.Detail.class)
    private String objectType;

    @JsonView(View.Detail.class)
    private Integer objectId;

    @JsonView(View.Detail.class)
    private String content;

    @JsonView(View.Detail.class)
    private Integer likes;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        return "UserComment{" +
                "id=" + id +
                ", userId=" + userId +
                ", objectType='" + objectType + '\'' +
                ", objectId=" + objectId +
                ", content='" + content + '\'' +
                ", likes=" + likes +
                ", ctime=" + ctime +
                '}';
    }
}
