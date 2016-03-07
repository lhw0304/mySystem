package com.mode.entity;

/**
 * Created by Administrator on 2016/3/3.
 */
public class Profile {
    private Integer profileId;

    private Integer userId;

    private String username;

    private String description;

    private String gender;

    private long ctime;

    private long utime;

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public long getUtime() {
        return utime;
    }

    public void setUtime(long utime) {
        this.utime = utime;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "profileId=" + profileId +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", description='" + description + '\'' +
                ", gender='" + gender + '\'' +
                ", ctime=" + ctime +
                ", utime=" + utime +
                '}';
    }
}
