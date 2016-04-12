package com.mode.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mode.base.BaseConfig;
import com.mode.base.Message;
import com.mode.base.Response;
import com.mode.base.Status;
import com.mode.dao.ConfigDAO;
import com.mode.dao.CreditZoneDAO;
import com.mode.dao.ProfileDAO;
import com.mode.dao.UserCreditLogDAO;
import com.mode.dao.UserNotificationCountryDAO;
import com.mode.dao.UserNotificationDAO;
import com.mode.dao.UserPrizeDAO;
import com.mode.entity.Config;
import com.mode.entity.CreditZone;
import com.mode.entity.Prize;
import com.mode.entity.Profile;
import com.mode.entity.UserCreditLog;
import com.mode.entity.UserNotification;
import com.mode.entity.UserNotificationCountry;
import com.mode.entity.UserPrize;
import com.mode.exception.ModeException;
import com.mode.service.CreditZoneService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by Lei on 3/23/16.
 */
@Service
public class CreditZoneServiceImpl implements CreditZoneService {

    @Autowired
    private CreditZoneDAO creditZoneDAO;

    @Autowired
    private ProfileDAO profileDAO;

    @Autowired
    private UserCreditLogDAO userCreditLogDAO;

    @Autowired
    private UserPrizeDAO userPrizeDAO;

    @Autowired
    private ConfigDAO configDAO;

    @Autowired
    private UserNotificationDAO userNotificationDAO;

    @Autowired
    private UserNotificationCountryDAO userNotificationCountryDAO;

    private static ObjectMapper om = new ObjectMapper();

    @Override
    public Response listCreditZoneItems(Integer limit, Integer offset, String country) {
        Response res = new Response(BaseConfig.OPERATION_SUCCEEDED, BaseConfig.SUCCESSFUL_MESSAGE);
        // Uncomment these code after creditZone dao has list item method
//        try {
//            List<CreditZone> list = creditZoneDAO.listCreditZone(limit, offset, country);
//            if (list.isEmpty() || list.size() == 0) {
//                throw new ModeException(Message.NO_MORE_CREDITZONES);
//            }
//            res.setPayload(list);
//        } catch (Exception e) {
//            throw new ModeException(Message.NO_MORE_CREDITZONES);
//        }
        return res;
    }

    /**
     * Redeem item in credit zone
     *
     * @param userId
     * @param creditzonesId
     * @param costCredit
     * @param usd
     * @return
     */
    @Override
    public Response redeemItemInCreditZone(Integer userId, Integer creditzonesId, Integer
            costCredit, Integer usd) {
        CreditZone creditZone = new CreditZone();
        creditZone.setId(creditzonesId);
        creditZone = creditZoneDAO.getCreditZone(creditZone);
        if (creditZone != null) {
            String prizeDetail = creditZone.getPrizeDetail();
            if (!StringUtils.isEmpty(prizeDetail)) {
                try {
                    Prize prize = om.readValue(prizeDetail, Prize.class);
                    Integer credit = prize.getCredit();

                    Profile profile = new Profile();
                    profile.setUserId(userId);
                    Integer curCredit = profileDAO.getProfile(profile).getCredits();
                    long now = System.currentTimeMillis();

                    if (BaseConfig.REDEEM_TYPE_ITEM.equalsIgnoreCase(prize.getType())) {
                        if (credit > 0) {
                            if (curCredit < credit) {
                                throw new ModeException(Message.REDEEM_CREDIT_NOT_ENOUGH);
                            }
                            // Add prize into md_user_prize table
                            UserPrize userPrize = new UserPrize();
                            userPrize.setType(BaseConfig.USER_PRICE_TYPE_REDEEM);
                            userPrize.setUserId(userId);
                            userPrize.setSourceId(creditzonesId);
                            userPrize.setStatus(Status.USER_PRICE_STATUS_NEW.getCode());
                            userPrize.setCtime(now);
                            userPrize.setUtime(now);
                            userPrizeDAO.createUserPrize(userPrize);
                            // Update user credit log in the md_user_credit_log table
                            UserCreditLog userCreditLog = new UserCreditLog();
                            userCreditLog.setUserId(userId);
                            userCreditLog.setTaskId(creditzonesId);
                            userCreditLog.setType(BaseConfig.USER_PRICE_TYPE_REDEEM);
                            userCreditLog.setCredit(0 - credit);
                            userCreditLog.setCtime(now);
                            try {
                                userCreditLog.setBalance(curCredit - credit);
                            } catch (Exception e) {
                            }
                            userCreditLogDAO.createUserCreditLog(userCreditLog);
                            // Update user credit in the md_profile table
                            prize.setCredit(credit);
                            profile = new Profile();
                            profile.setUserId(userId);
                            profile.setCredits(0 - credit);
                            profile.setUtime(now);
                            profileDAO.updateProfile(profile);

                            // Send a notification to the user
                            sendNotification(userId, prize);
                        }
                    } else if (BaseConfig.REDEEM_TYPE_USD.equalsIgnoreCase(prize.getType())) {
                        if (costCredit != null && usd != null) {
                            if (curCredit < costCredit) {
                                throw new ModeException(Message.REDEEM_CREDIT_NOT_ENOUGH);
                            }
                            // Add prize into md_user_prize table
                            UserPrize userPrize = new UserPrize();
                            userPrize.setType(BaseConfig.USER_PRICE_TYPE_REDEEM);
                            userPrize.setUserId(userId);
                            userPrize.setId(creditzonesId);
                            userPrize.setStatus(Status.USER_PRICE_STATUS_NEW.getCode());
                            userPrize.setUsd(usd);
                            userPrize.setCtime(now);
                            userPrize.setUtime(now);
                            userPrizeDAO.createUserPrize(userPrize);
                            // Update user credit log in the md_user_credit_log table
                            UserCreditLog userCreditLog = new UserCreditLog();
                            userCreditLog.setUserId(userId);
                            userCreditLog.setTaskId(creditzonesId);
                            userCreditLog.setType(BaseConfig.USER_PRICE_TYPE_REDEEM);
                            userCreditLog.setCredit(0 - credit);
                            userCreditLog.setCtime(now);
                            try {
                                userCreditLog.setBalance(curCredit - credit);
                            } catch (Exception e) {
                            }
                            userCreditLogDAO.createUserCreditLog(userCreditLog);
                            // Update user credit in the md_profile table
                            profile = new Profile();
                            profile.setUserId(userId);
                            profile.setCredits(0 - costCredit);
                            profile.setUtime(now);
                            profileDAO.updateProfile(profile);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new ModeException(Message.FAILURE);
                }
            }
        } else {
            throw new ModeException(Message.REDEEM_OUT_OF_DATE);
        }

        Response res = new Response(Message.SUCCESS);
        return res;
    }

    private void sendNotification(Integer userId, Prize prize) {
        final Long now = System.currentTimeMillis();
        try {
            // Send a notification to the user

            Config config = new Config();
            config.setAttributeName("redeemNotification");
            config.setType("userConfig");
            config.setCountryCode("US");
            config = configDAO.getConfig(config);

            UserNotification notification = new UserNotification();

            JsonNode notifi = om.readTree(config.getAttributeValue());

            List<Object> objects = new ArrayList<>();

            Text text = new Text();
            text.setType(notifi.get("content").get(0).get("type").asText());
            text.setContent(notifi.get("content").get(0).get("content").asText());

            objects.add(text);

            Content content = new Content();
            content.setContent(objects);
            content.setType("Notification");
            content.setTitle(notifi.get("title").asText());
            if (prize.getSubImage().isEmpty()) {
                content.setDefaultImage(prize.getImage());
            } else {
                content.setDefaultImage(prize.getSubImage());
            }

            notification.setType("notice");
            notification.setCtime(now);
            notification.setStatus(0);
            notification.setTarget("partial");
            notification.setUtime(now);
            notification.setContent(om.writeValueAsString(content));
            notification.setUserId(userId);
            userNotificationDAO.createUserNotification(notification);

            UserNotificationCountry country = new UserNotificationCountry();
            country.setNotificationId(notification.getId());
            country.setCountryCode("US");
            country.setCtime(now);
            userNotificationCountryDAO.createUserNotificationCountry(country);

        } catch (Exception e) {
        }
    }

    private class Text {
        private String type;
        private String content;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    private class Image {
        private String type;
        private String defaultImage;
        private String url;
        private String items;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDefaultImage() {
            return defaultImage;
        }

        public void setDefaultImage(String defaultImage) {
            this.defaultImage = defaultImage;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getItems() {
            return items;
        }

        public void setItems(String items) {
            this.items = items;
        }
    }

    private class Content {
        private String title;
        private String type;
        private String defaultImage;
        private String description;
        private List<Object> content;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDefaultImage() {
            return defaultImage;
        }

        public void setDefaultImage(String defaultImage) {
            this.defaultImage = defaultImage;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public List<Object> getContent() {
            return content;
        }

        public void setContent(List<Object> content) {
            this.content = content;
        }
    }
}
