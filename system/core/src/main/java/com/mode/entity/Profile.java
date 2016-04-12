package com.mode.entity;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by zhaoweiwei on 16/3/16.
 */
public class Profile {

    private Integer userId;

    @JsonView(View.Summary.class)
    private String gender;

    @JsonView(View.Summary.class)
    private String nickname;

    @JsonView(View.Detail.class)
    private Integer level;

    @JsonView(View.Detail.class)
    private String avatar;

    @JsonView(View.Detail.class)
    private String birthday;

    @JsonView(View.Detail.class)
    private String description;

    @JsonView(View.Detail.class)
    private String payment;

    @JsonView(View.Detail.class)
    private Integer credits;

    @JsonView(View.Detail.class)
    private String address;

    @JsonView(View.Detail.class)
    private Float usd;

    private Integer inviteBy;

    @JsonView(View.Detail.class)
    private String defaultImage;

    @JsonView(View.Detail.class)
    private Integer saves;

    private Long ctime;

    private Long utime;

    @JsonCreator
    public Profile(){

    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getUsd() {
        return usd;
    }

    public void setUsd(Float usd) {
        this.usd = usd;
    }

    public Integer getInviteBy() {
        return inviteBy;
    }

    public void setInviteBy(Integer inviteBy) {
        this.inviteBy = inviteBy;
    }

    public String getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(String defaultImage) {
        this.defaultImage = defaultImage;
    }

    public Integer getSaves() {
        return saves;
    }

    public void setSaves(Integer saves) {
        this.saves = saves;
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
        return "Profile{" +
                "userId=" + userId +
                ", gender='" + gender + '\'' +
                ", nickname='" + nickname + '\'' +
                ", level=" + level +
                ", avatar='" + avatar + '\'' +
                ", birthday='" + birthday + '\'' +
                ", description='" + description + '\'' +
                ", payment='" + payment + '\'' +
                ", credits=" + credits +
                ", address='" + address + '\'' +
                ", usd=" + usd +
                ", inviteBy=" + inviteBy +
                ", defaultImage='" + defaultImage + '\'' +
                ", saves=" + saves +
                ", ctime=" + ctime +
                ", utime=" + utime +
                '}';
    }
}
