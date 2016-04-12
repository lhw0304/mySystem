package com.mode.entity;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by zhaoweiwei on 16/3/16.
 */
public class Ad {

    private Integer id;

    private String type;

    @JsonView(View.Detail.class)
    private String title;

    @JsonView(View.Detail.class)
    private String displayPage;

    @JsonView(View.Detail.class)
    private String defaultImage;

    @JsonView(View.Detail.class)
    private String content;

    @JsonView(View.Detail.class)
    private String description;

    private Integer status;

    private String countryCode;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisplayPage() {
        return displayPage;
    }

    public void setDisplayPage(String displayPage) {
        this.displayPage = displayPage;
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
        return "Ad{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", displayPage='" + displayPage + '\'' +
                ", defaultImage='" + defaultImage + '\'' +
                ", content='" + content + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", countryCode='" + countryCode + '\'' +
                ", ctime=" + ctime +
                ", utime=" + utime +
                '}';
    }
}
