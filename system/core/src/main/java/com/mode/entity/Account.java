package com.mode.entity;


import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by zhaoweiwei on 16/3/16.
 */
public class Account {

    private Integer id;

    @JsonView(View.Summary.class)
    private String username;

    private String password;

    @JsonView(View.Summary.class)
    private String email;

    @JsonView(View.Detail.class)
    private String mobile;

    @JsonView(View.Detail.class)
    private String accessToken;

    @JsonView(View.Detail.class)
    private Long expireTime;

    private String role;

    private Integer status;

    private Integer activateKey;

    private Integer resetPasswordKey;

    private String source;

    private Long ctime;

    private Long utime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getActivateKey() {
        return activateKey;
    }

    public void setActivateKey(Integer activateKey) {
        this.activateKey = activateKey;
    }

    public Integer getResetPasswordKey() {
        return resetPasswordKey;
    }

    public void setResetPasswordKey(Integer resetPasswordKey) {
        this.resetPasswordKey = resetPasswordKey;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", expireTime=" + expireTime +
                ", role='" + role + '\'' +
                ", status=" + status +
                ", activateKey=" + activateKey +
                ", resetPasswordKey=" + resetPasswordKey +
                ", source='" + source + '\'' +
                ", ctime=" + ctime +
                ", utime=" + utime +
                '}';
    }
}
