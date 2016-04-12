package com.mode.dto;

import com.mode.entity.UserLoginLog;

/**
 * Created by Lei on 3/15/16.
 */
public class Registry {

    private String username;
    private String password;
    private String source;
    private UserLoginLog userLoginLog;

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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public UserLoginLog getUserLoginLog() {
        return userLoginLog;
    }

    public void setUserLoginLog(UserLoginLog userLoginLog) {
        this.userLoginLog = userLoginLog;
    }

    @Override
    public String toString() {
        return "{"
                + "                        \"username\":\"" + username + "\""
                + ",                         \"password\":\"" + password + "\""
                + ",                         \"source\":\"" + source + "\""
                + ",                         \"userLoginLog\":" + userLoginLog
                + "}";
    }
}
