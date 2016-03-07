package com.mode.entity;

/**
 * Created by Administrator on 2016/3/3.
 */
public class Completion {
    private Integer id;

    private Integer userId;

    private String content;

    private String answer;

    private long ctime;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        return "Completion{" +
                "id=" + id +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", answer='" + answer + '\'' +
                ", ctime=" + ctime +
                '}';
    }
}
