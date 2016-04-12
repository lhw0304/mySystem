package com.mode.entity;


/**
 * Created by zhaoweiwei on 16/3/16.
 */
public class UserTask {

    private Integer id;
    private Integer userId;
    private Integer taskId;
    private Integer status;
    private String inviteCode;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        return "UserTask{" +
                "id=" + id +
                ", userId=" + userId +
                ", taskId=" + taskId +
                ", status=" + status +
                ", inviteCode='" + inviteCode + '\'' +
                ", ctime=" + ctime +
                '}';
    }
}
