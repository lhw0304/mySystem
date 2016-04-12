package com.mode.entity;


/**
 * Created by zhaoweiwei on 16/3/16.
 */
public class ItemCountry {

    private Integer id;
    private Integer itemId;
    private String countryCode;
    private Long ctime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
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
        return "ItemCountry{" +
                "id=" + id +
                ", itemId=" + itemId +
                ", countryCode='" + countryCode + '\'' +
                ", ctime=" + ctime +
                '}';
    }
}
