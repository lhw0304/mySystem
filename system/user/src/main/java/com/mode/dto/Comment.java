package com.mode.dto;

import com.mode.entity.UserComment;

/**
 * Created by Lei on 3/17/16.
 */
public class Comment extends UserComment{

    private String avatar;
    private String name;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
