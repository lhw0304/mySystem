package com.mode.service.data;

import com.mode.base.Status;
import com.mode.dto.Registry;
import com.mode.entity.Profile;
import com.mode.entity.UserComment;
import com.mode.entity.UserFeedback;
import com.mode.entity.UserLoginLog;
import com.mode.entity.UserNotification;

/**
 * Created by Lei on 3/21/16.
 */
public class UserData {

    public static Registry getRegistry(String source){
        Registry registry = new Registry();
        registry.setUsername("123456789@qq.com");
        registry.setPassword("e10adc3949ba59abbe56e057f20f883e");
        registry.setSource(source);

        UserLoginLog userLoginLog = new UserLoginLog();

        registry.setUserLoginLog(userLoginLog);

        return registry;
    }

    public static Profile getProfile(Integer userId){

        final long now = System.currentTimeMillis();

        Profile profile = new Profile();
        profile.setCtime(now);
        profile.setUtime(now);
        profile.setUsd(0f);
        profile.setUserId(userId);
        profile.setCredits(100000);
        return profile;
    }

    public static UserComment getUserComment(int objectId, int userId){
        final long now = System.currentTimeMillis();

        UserComment userComment = new UserComment();
        userComment.setContent("Test Comment!");
        userComment.setCtime(now);
        userComment.setUserId(userId);
        userComment.setObjectType("article");
        userComment.setObjectId(objectId);

        return userComment;
    }

    public static UserFeedback getUserFeedback(Integer userId){

        final long now = System.currentTimeMillis();

        UserFeedback userFeedback = new UserFeedback();
        userFeedback.setCtime(now);
        userFeedback.setType("Bugs");
        userFeedback.setStatus(Status.NORMAL.getCode());
        userFeedback.setContent("There's a bug in the app.");
        userFeedback.setSender(userId);

        return userFeedback;
    }

    public static UserNotification getUserNotification(Integer userId) {

        final long now = System.currentTimeMillis();

        UserNotification userNotification = new UserNotification();

        userNotification.setStatus(Status.NORMAL.getCode());
        userNotification.setUserId(userId);
        userNotification.setContent("{\"title\":\"Redeem Successfully !!\"," +
                "\"type\":\"Notification\",\"defaultImage\":\"http://img.cdn.whatsmode" +
                ".com/images/redeems/560b94f6aa274f35f5b623c8c990ab81.png\",\"description\":null," +
                "\"content\":[{\"type\":\"text\",\"content\":\"Hey! Here's the reward for " +
                "you!\\nAll you need to do is providing your personal information (Full name, " +
                "address, city, state, zip code and Tel No.) in feedback->gift within 3 days. " +
                "\\nIf not, all expired rewards (except credits) in the account will be " +
                "forfeited, deleted and without further notice. (Credits will automatic recharge " +
                "your account).\\n\\nXOXO\\nThe MODE team\"}]}");
        userNotification.setCtime(now);
        userNotification.setUtime(now);
        userNotification.setDefaultImage("http://img.cdn.whatsmode.com/images/1/jpg");
        userNotification.setDescription("Wish you be happy!");
        userNotification.setTarget("all");
        userNotification.setTitle("Wish you be happy!");
        userNotification.setType("notice");

        return userNotification;
    }
}
