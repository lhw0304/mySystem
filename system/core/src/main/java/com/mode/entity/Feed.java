package com.mode.entity;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by zhaoweiwei on 16/3/16.
 */
public class Feed {

    private Integer id;

    @JsonView(View.Detail.class)
    private String type;

    @JsonView(View.Detail.class)
    private Integer authorId;

    @JsonView(View.Detail.class)
    private String title;

    @JsonView(View.Detail.class)
    private String defaultImage;

    @JsonView(View.Detail.class)
    private String content;

    @JsonView(View.Detail.class)
    private String description;

    @JsonView(View.Detail.class)
    private String section;

    private Integer status;

    @JsonView(View.Detail.class)
    private Integer likes;

    @JsonView(View.Detail.class)
    private Integer saves;

    @JsonView(View.Detail.class)
    private Integer comments;

    @JsonView(View.Detail.class)
    private Integer shares;

    @JsonView(View.Detail.class)
    private Long ctime;

    @JsonView(View.Detail.class)
    private Long utime;

    @JsonCreator
    public Feed() {
    }

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

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(String defaultImage) {
        this.defaultImage = defaultImage;
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

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getSaves() {
        return saves;
    }

    public void setSaves(Integer saves) {
        this.saves = saves;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public Integer getShares() {
        return shares;
    }

    public void setShares(Integer shares) {
        this.shares = shares;
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
        return "Feed{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", authorId=" + authorId +
                ", title='" + title + '\'' +
                ", defaultImage='" + defaultImage + '\'' +
                ", content='" + content + '\'' +
                ", description='" + description + '\'' +
                ", section='" + section + '\'' +
                ", status=" + status +
                ", likes=" + likes +
                ", saves=" + saves +
                ", comments=" + comments +
                ", shares=" + shares +
                ", ctime=" + ctime +
                ", utime=" + utime +
                '}';
    }
}
