package com.mode.entity;


import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by zhaoweiwei on 16/3/16.
 */
public class LuckyWheel {

    private Integer id;

    @JsonView(View.Detail.class)
    private String prizeDetail;

    @JsonView(View.Detail.class)
    private Integer level;

    private Integer base;

    private Integer probability;

    private Integer startRange;

    private Integer endRange;

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

    public String getPrizeDetail() {
        return prizeDetail;
    }

    public void setPrizeDetail(String prizeDetail) {
        this.prizeDetail = prizeDetail;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getBase() {
        return base;
    }

    public void setBase(Integer base) {
        this.base = base;
    }

    public Integer getProbability() {
        return probability;
    }

    public void setProbability(Integer probability) {
        this.probability = probability;
    }

    public Integer getStartRange() {
        return startRange;
    }

    public void setStartRange(Integer startRange) {
        this.startRange = startRange;
    }

    public Integer getEndRange() {
        return endRange;
    }

    public void setEndRange(Integer endRange) {
        this.endRange = endRange;
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
        return "LuckyWheel{" +
                "id=" + id +
                ", prizeDetail='" + prizeDetail + '\'' +
                ", level=" + level +
                ", base=" + base +
                ", probability=" + probability +
                ", startRange=" + startRange +
                ", endRange=" + endRange +
                ", status=" + status +
                ", countryCode='" + countryCode + '\'' +
                ", ctime=" + ctime +
                ", utime=" + utime +
                '}';
    }
}
