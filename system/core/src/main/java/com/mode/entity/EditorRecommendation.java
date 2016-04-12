package com.mode.entity;


import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by zhaoweiwei on 16/3/16.
 */
public class EditorRecommendation {

    @JsonView(View.Detail.class)
    private Integer id;

    @JsonView(View.Detail.class)
    private String title;

    @JsonView(View.Detail.class)
    private String itemId;

    @JsonView(View.Detail.class)
    private String description;

    @JsonView(View.Detail.class)
    private String defaultImage;

    private Integer status;

    @JsonView(View.Detail.class)
    private String author;

    private String countryCode;

    @JsonView(View.Detail.class)
    private Integer sortOrder;

    private Long ctime;

    private Long utime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
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
        return "EditorRecommendation{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", itemId='" + itemId + '\'' +
                ", description='" + description + '\'' +
                ", defaultImage='" + defaultImage + '\'' +
                ", status=" + status +
                ", author='" + author + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", sortOrder=" + sortOrder +
                ", ctime=" + ctime +
                ", utime=" + utime +
                '}';
    }
}
