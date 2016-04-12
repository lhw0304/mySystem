package com.mode.security;

/**
 * This is for generating access tokens and to verify tokens.
 *
 * @author chao
 *
 */
public class LoginUser {

    private int userId;
    private String username;
    private String token;
    private long expires;
    private String role;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpires() {
        return expires;
    }

    public void setExpires(long expires) {
        this.expires = expires;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "LoginUser [userId=" + userId + ", username=" + username + ", token=" + token
                + ", expires=" + expires + ", role=" + role + "]";
    }
}