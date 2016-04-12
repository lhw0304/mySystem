package com.mode.entity;


import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by zhaoweiwei on 16/3/16.
 */
public class UserNotification {

    private Integer id;

    @JsonView(View.Detail.class)
    private String type;

    private String target;

    private Integer userId;

    @JsonView(View.Detail.class)
    private String title;

    @JsonView(View.Detail.class)
    private String content;

    @JsonView(View.Detail.class)
    private String description;

    @JsonView(View.Detail.class)
    private String defaultImage;

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

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(String defaultImage) {
        this.defaultImage = defaultImage;
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
        return "UserNotification{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", target='" + target + '\'' +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", description='" + description + '\'' +
                ", defaultImage='" + defaultImage + '\'' +
                ", status=" + status +
                ", ctime=" + ctime +
                ", utime=" + utime +
                '}';
    }
}
