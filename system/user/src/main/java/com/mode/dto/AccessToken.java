package com.mode.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The JWT access token for authentication purposes.
 *
 * Created by Lei on 3/11/16.
 */
public class AccessToken {

    private String accessToken;

    public AccessToken(){
    }

    @JsonCreator
    public AccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "{\"AccessToken\":\"" + accessToken + "\"}";
    }
}