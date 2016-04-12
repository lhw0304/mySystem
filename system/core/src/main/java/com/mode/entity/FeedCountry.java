package com.mode.entity;

/**
 * Created by zhaoweiwei on 16/3/16.
 */
public class FeedCountry {

    private Integer id;
    private Integer feedId;
    private String countryCode;
    private Long ctime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFeedId() {
        return feedId;
    }

    public void setFeedId(Integer feedId) {
        this.feedId = feedId;
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

    @Override
    public String toString() {
        return "FeedCountry{" +
                "id=" + id +
                ", feedId=" + feedId +
                ", countryCode='" + countryCode + '\'' +
                ", ctime=" + ctime +
                '}';
    }
}
