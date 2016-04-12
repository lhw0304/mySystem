package com.mode.entity;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by zhaoweiwei on 16/3/16.
 */
public class CreditZone {

    private Integer id;

    @JsonView(View.Detail.class)
    private String prizeDetail;

    private Integer status;

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

    public String getPrizeDetail() {
        return prizeDetail;
    }

    public void setPrizeDetail(String prizeDetail) {
        this.prizeDetail = prizeDetail;
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
        return "CreditZone{" +
                "id=" + id +
                ", prizeDetail='" + prizeDetail + '\'' +
                ", status=" + status +
                ", countryCode='" + countryCode + '\'' +
                ", sortOrder=" + sortOrder +
                ", ctime=" + ctime +
                ", utime=" + utime +
                '}';
    }
}
