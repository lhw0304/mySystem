package com.mode.entity;


import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by zhaoweiwei on 16/3/16.
 */
public class UserFeedback {

    private Integer id;

    @JsonView(View.Detail.class)
    private String type;

    @JsonView(View.Detail.class)
    private Integer sender;

    @JsonView(View.Detail.class)
    private Integer receiver;

    @JsonView(View.Detail.class)
    private String content;

    @JsonView(View.Detail.class)
    private Integer status;

    @JsonView(View.Detail.class)
    private Long ctime;

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

    public Integer getSender() {
        return sender;
    }

    public void setSender(Integer sender) {
        this.sender = sender;
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    @Override
    public String toString() {
        return "UserFeedback{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", ctime=" + ctime +
                '}';
    }
}
