package com.mode.entity;


/**
 * Created by zhaoweiwei on 16/3/16.
 */
public class UserShoppingLog {

    private Integer id;
    private Integer userId;
    private Integer itemId;
    private Integer feedId;
    private String url;
    private Float price;
    private Integer navigationType;
    private String source;
    private Long browseTime;

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

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getFeedId() {
        return feedId;
    }

    public void setFeedId(Integer feedId) {
        this.feedId = feedId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getNavigationType() {
        return navigationType;
    }

    public void setNavigationType(Integer navigationType) {
        this.navigationType = navigationType;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Long getBrowseTime() {
        return browseTime;
    }

    public void setBrowseTime(Long browseTime) {
        this.browseTime = browseTime;
    }
}
