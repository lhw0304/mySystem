package com.mode.entity;

/**
 * Created by Administrator on 2016/3/3.
 */
public class Check {
    private Integer id;

    private Integer userId;

    private String content;

    private Integer answer;

    private String knowledge;

    private long ctime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public String getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(String knowledge) {
        this.knowledge = knowledge;
    }

    @Override
    public String toString() {
        return "Check{" +
                "id=" + id +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", answer=" + answer +
                ", knowledge='" + knowledge + '\'' +
                ", ctime=" + ctime +
                '}';
    }
}
