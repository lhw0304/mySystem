package com.mode.entity;


/**
 * Created by zhaoweiwei on 16/3/16.
 */
public class UserNotificationCountry {

    private Integer id;
    private Integer notificationId;
    private String countryCode;
    private Long ctime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
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
        return "UserNotificationCountry{" +
                "id=" + id +
                ", notificationId=" + notificationId +
                ", countryCode='" + countryCode + '\'' +
                ", ctime=" + ctime +
                '}';
    }
}
