package com.mode.entity;

/**
 * Created by Administrator on 2016/4/21.
 */
public class Knowledge {
    private Integer id;
    private Integer userId;
    private String knowledge;
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

    public String getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(String knowledge) {
        this.knowledge = knowledge;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        return "Knowledge{" +
                "id=" + id +
                ", userId=" + userId +
                ", knowledge='" + knowledge + '\'' +
                ", ctime=" + ctime +
                '}';
    }
}
