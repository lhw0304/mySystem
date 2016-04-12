package com.mode.security;

/**
 * Created by Lei on 3/22/16.
 */
public class TokenClaims {

    private String username;
    private String source;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "{"
                + "\"username\":\"" + username + "\""
                + ",\"source\":\"" + source + "\""
                + "}";
    }
}
