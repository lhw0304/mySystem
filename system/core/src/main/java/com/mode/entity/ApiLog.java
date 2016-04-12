package com.mode.entity;

/**
 * Created by zhaoweiwei on 16/3/16.
 */
public class ApiLog {

    private Long id;
    private String api;
    private String httpMethod;
    private String httpStatus;
    private String ip;
    private Integer userId;
    private String source;
    private Long ctime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(String httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "ApiLog{" +
                "id=" + id +
                ", api='" + api + '\'' +
                ", httpMethod='" + httpMethod + '\'' +
                ", httpStatus='" + httpStatus + '\'' +
                ", ip='" + ip + '\'' +
                ", userId=" + userId +
                ", source='" + source + '\'' +
                ", ctime=" + ctime +
                '}';
    }
}
