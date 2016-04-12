package com.mode.entity;


import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by zhaoweiwei on 16/3/16.
 */
public class Task {

    @JsonView(View.Detail.class)
    private Integer id;

    @JsonView(View.Detail.class)
    private String group;

    @JsonView(View.Detail.class)
    private String type;

    @JsonView(View.Detail.class)
    private String name;

    @JsonView(View.Detail.class)
    private String description;

    @JsonView(View.Detail.class)
    private String tips;

    @JsonView(View.Detail.class)
    private String objectType;

    @JsonView(View.Detail.class)
    private String action;


    private String rule;

    @JsonView(View.Detail.class)
    private Long startTime;

    @JsonView(View.Detail.class)
    private Long endTime;

    private Integer priority;

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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
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
        return "Task{" +
                "id=" + id +
                ", group='" + group + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", tips='" + tips + '\'' +
                ", objectType='" + objectType + '\'' +
                ", action='" + action + '\'' +
                ", rule='" + rule + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", priority=" + priority +
                ", status=" + status +
                ", countryCode='" + countryCode + '\'' +
                ", ctime=" + ctime +
                ", utime=" + utime +
                '}';
    }
}
