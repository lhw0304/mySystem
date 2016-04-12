package com.mode.entity;


/**
 * Created by zhaoweiwei on 16/3/16.
 */
public class UserLoginLog {

    private Integer id;
    private Integer userId;
    private String action;
    private String deviceType;
    private String phoneNumber;
    private String phoneName;
    private String deviceToken;
    private String systemVersion;
    private String appVersion;
    private String uuid;
    private String imsi;
    private String model;
    private String simserialnum;
    private String pixel;
    private String ipAddress;
    private String macAddress;
    private Double latitude;
    private Double longitude;
    private String language;
    private String country;
    private String timeZone;
    private Long ctime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSimserialnum() {
        return simserialnum;
    }

    public void setSimserialnum(String simserialnum) {
        this.simserialnum = simserialnum;
    }

    public String getPixel() {
        return pixel;
    }

    public void setPixel(String pixel) {
        this.pixel = pixel;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        return "{"
                + "                        \"id\":\"" + id + "\""
                + ",                         \"userId\":\"" + userId + "\""
                + ",                         \"action\":\"" + action + "\""
                + ",                         \"deviceType\":\"" + deviceType + "\""
                + ",                         \"phoneNumber\":\"" + phoneNumber + "\""
                + ",                         \"phoneName\":\"" + phoneName + "\""
                + ",                         \"deviceToken\":\"" + deviceToken + "\""
                + ",                         \"systemVersion\":\"" + systemVersion + "\""
                + ",                         \"appVersion\":\"" + appVersion + "\""
                + ",                         \"uuid\":\"" + uuid + "\""
                + ",                         \"imsi\":\"" + imsi + "\""
                + ",                         \"model\":\"" + model + "\""
                + ",                         \"simserialnum\":\"" + simserialnum + "\""
                + ",                         \"pixel\":\"" + pixel + "\""
                + ",                         \"ipAddress\":\"" + ipAddress + "\""
                + ",                         \"macAddress\":\"" + macAddress + "\""
                + ",                         \"latitude\":\"" + latitude + "\""
                + ",                         \"longitude\":\"" + longitude + "\""
                + ",                         \"language\":\"" + language + "\""
                + ",                         \"country\":\"" + country + "\""
                + ",                         \"timeZone\":\"" + timeZone + "\""
                + ",                         \"ctime\":\"" + ctime + "\""
                + "}";
    }
}
