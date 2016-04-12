package com.mode.entity;


import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by zhaoweiwei on 16/3/16.
 */
public class Stylist {

    @JsonView(View.Summary.class)
    private Integer userId;

    @JsonView(View.Summary.class)
    private String name;

    @JsonView(View.Summary.class)
    private String logo;

    @JsonView(View.Detail.class)
    private String description;

    private Integer status;

    private String countryCode;

    @JsonView(View.Detail.class)
    private Integer followers;

    @JsonView(View.Detail.class)
    private Integer articles;

    private Long ctime;

    private Long utime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public Integer getArticles() {
        return articles;
    }

    public void setArticles(Integer articles) {
        this.articles = articles;
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
        return "Stylist{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", countryCode='" + countryCode + '\'' +
                ", followers=" + followers +
                ", articles=" + articles +
                ", ctime=" + ctime +
                ", utime=" + utime +
                '}';
    }
}
