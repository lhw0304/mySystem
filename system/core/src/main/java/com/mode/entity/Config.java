package com.mode.entity;


import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by zhaoweiwei on 16/3/16.
 */
public class Config {

    @JsonView(View.Detail.class)
    private Integer id;

    @JsonView(View.Detail.class)
    private String type;

    @JsonView(View.Detail.class)
    private String attributeName;

    @JsonView(View.Detail.class)
    private String attributeValue;

    @JsonView(View.Detail.class)
    private String countryCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "Config{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", attributeName='" + attributeName + '\'' +
                ", attributeValue='" + attributeValue + '\'' +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }
}
