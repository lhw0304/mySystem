package com.mode.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.mode.entity.Feed;

/**
 * Created by Lei on 3/17/16.
 */
public class UserFeed {

    private Integer id;
    private String type;
    private String title;
    private String description;
    private String section;
    private Integer status;
    private Integer likes;
    private Integer saves;
    private Integer comments;
    private Integer shares;
    private Long ctime;
    private Long utime;
    private Object content;
    private Object author;

    @JsonCreator
    public UserFeed() {
    }

    public UserFeed(Feed feed) {
        this.id = feed.getId();
        this.type = feed.getType();
        this.title = feed.getTitle();
        this.description = feed.getDescription();
        this.section = feed.getSection();
        this.status = feed.getStatus();
        this.likes = feed.getLikes();
        this.saves = feed.getSaves();
        this.comments = feed.getComments();
        this.shares = feed.getShares();
        this.ctime = feed.getCtime();
        this.utime = feed.getUtime();
        this.author = null;
        this.content = null;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public Object getAuthor() {
        return author;
    }

    public void setAuthor(Object author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "{"
                + "                        \"id\":\"" + id + "\""
                + ",                         \"type\":\"" + type + "\""
                + ",                         \"title\":\"" + title + "\""
                + ",                         \"description\":\"" + description + "\""
                + ",                         \"section\":\"" + section + "\""
                + ",                         \"status\":\"" + status + "\""
                + ",                         \"likes\":\"" + likes + "\""
                + ",                         \"saves\":\"" + saves + "\""
                + ",                         \"comments\":\"" + comments + "\""
                + ",                         \"shares\":\"" + shares + "\""
                + ",                         \"ctime\":\"" + ctime + "\""
                + ",                         \"utime\":\"" + utime + "\""
                + ",                         \"content\":" + content
                + ",                         \"author\":" + author
                + "}";
    }
}
