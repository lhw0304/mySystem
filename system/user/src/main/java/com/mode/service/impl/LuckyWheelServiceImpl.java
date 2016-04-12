package com.mode.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mode.base.BaseConfig;
import com.mode.base.Message;
import com.mode.base.Response;
import com.mode.base.Status;
import com.mode.dao.ConfigDAO;
import com.mode.dao.LuckyWheelDAO;
import com.mode.dao.ProfileDAO;
import com.mode.dao.UserCreditLogDAO;
import com.mode.dao.UserNotificationCountryDAO;
import com.mode.dao.UserNotificationDAO;
import com.mode.dao.UserPrizeDAO;
import com.mode.entity.Config;
import com.mode.entity.LuckyWheel;
import com.mode.entity.Prize;
import com.mode.entity.Profile;
import com.mode.entity.UserCreditLog;
import com.mode.entity.UserNotification;
import com.mode.entity.UserNotificationCountry;
import com.mode.entity.UserPrize;
import com.mode.exception.ModeException;
import com.mode.service.LuckyWheelService;

/**
 * Created by Lei on 3/23/16.
 */
@Service
public class LuckyWheelServiceImpl implements LuckyWheelService {

    @Autowired
    private ProfileDAO profileDAO;

    @Autowired
    private UserPrizeDAO userPrizeDAO;

    @Autowired
    private UserCreditLogDAO userCreditLogDAO;

    @Autowired
    private LuckyWheelDAO luckyWheelDAO;

    @Autowired
    private ConfigDAO configDAO;

    @Autowired
    private UserNotificationDAO userNotificationDAO;

    @Autowired
    private UserNotificationCountryDAO userNotificationCountryDAO;

    @Autowired
    private ObjectMapper om;
    /**
     * Get a list of lucky wheel items
     *
     * @param country
     * @return
     */
    @Override
    public Response listLuckyWheelItems(String country) {
        // Uncomment these code after luckyWheelDAO dao  list luckyWheel method
        List<LuckyWheel> luckyWheels = new ArrayList<>();
//        try {
//            luckyWheels = luckyWheelDAO.listLuckyWheels(country);
//            if (luckyWheels.isEmpty() || luckyWheels.size() == 0) {
//                throw new ModeException(Message.NO_MORE_LUCKYWHEELS);
//            }
//        } catch (Exception e) {
//            throw new ModeException(Message.NO_MORE_LUCKYWHEELS);
//        }
        Response res = new Response(Message.SUCCESS);
        res.setPayload(luckyWheels);

        return res;
    }

    /**
     * User spin the lucky wheel and get the reward.
     *
     * @param userId
     * @return
     */
    @Override
    public Response spinLuckyWheel(Integer userId, String country) {
        // Get a list of lucky wheel items from database
        // Because of no dao for now, set it to null. Complete it later
        List<LuckyWheel> list = null;
        Random random = new Random();
        Integer prizeLevel = null;
        Prize prize = null;
        if (list != null && list.size() > 0) {
            Integer base = list.get(0).getBase();
            if (base != null) {
                int num = random.nextInt(base) + 1;
                Map<Integer, List<Prize>> prizeMap = new HashMap<Integer, List<Prize>>();
                for (LuckyWheel luckyWheel : list) {
                    Integer startRange = luckyWheel.getStartRange();
                    Integer endRange = luckyWheel.getEndRange();
                    Integer level = luckyWheel.getLevel();
                    if (level != null) {
                        if (prizeLevel == null && startRange != null
                                && endRange != null) {
                            if (num >= startRange && num <= endRange)
                                prizeLevel = level;
                        }
                        String prizeDetail = luckyWheel.getPrizeDetail();
                        try {
                            Prize prizeFromJson = om.readValue(prizeDetail, Prize.class);

                            prizeFromJson.setId(luckyWheel.getId());
                            List<Prize> prizeList = null;
                            if (prizeMap.get(level) == null) {
                                prizeList = new ArrayList<Prize>();
                                prizeList.add(prizeFromJson);
                                prizeMap.put(level, prizeList);
                            } else {
                                prizeList = prizeMap.get(level);
                                prizeList.add(prizeFromJson);
                            }
                        } catch (Exception e) {
                        }
                    }
                }
                // Default prize level is 9
                if (prizeLevel == null && prizeMap.get(9) != null) {
                    prizeLevel = 9;
                }
                List<Prize> prizeList = prizeMap.get(prizeLevel);
                if (prizeList != null && prizeList.size() > 0) {
                    num = random.nextInt(prizeList.size());
                    prize = prizeList.get(num);
                    long now = System.currentTimeMillis();
                    if (BaseConfig.LUCK_DRAW_TYPE_ITEM.equalsIgnoreCase(prize.getType())) {
                        // Add prize into md_user_prize table
                        UserPrize userPrize = new UserPrize();
                        userPrize.setType(BaseConfig.USER_PRICE_TYPE_LUCKY_DRAW);
                        userPrize.setUserId(userId);
                        userPrize.setId(prize.getId());
                        userPrize.setStatus(Status.USER_PRICE_STATUS_NEW.getCode());
                        userPrize.setCtime(now);
                        userPrize.setUtime(now);
                        userPrizeDAO.createUserPrize(userPrize);
                    } else if (BaseConfig.LUCK_DRAW_TYPE_CREDIT.equalsIgnoreCase(prize.getType())) {
                        Integer startValue = prize.getStartValue();
                        Integer endValue = prize.getEndValue();
                        Integer credit = null;
                        if (startValue != null && endValue != null) {
                            if (startValue.equals(endValue)) {
                                credit = startValue;
                            }
                            if (endValue > startValue) {
                                credit = random.nextInt(endValue - startValue + 1) + startValue;
                            }
                        }
                        if (credit != null && credit > 0) {
                            // Update user credit log in the md_user_credit_log table
                            UserCreditLog userCreditLog = new UserCreditLog();
                            userCreditLog.setUserId(userId);
                            userCreditLog.setTaskId(prize.getId());
                            userCreditLog.setType(BaseConfig.USER_PRICE_TYPE_LUCKY_DRAW);
                            userCreditLog.setCredit(credit);
                            userCreditLog.setCtime(now);
                            try {
                                Profile profile = new Profile();
                                profile.setUserId(userId);
                                userCreditLog.setBalance(profileDAO.getProfile(profile)
                                        .getCredits() + credit);
                            } catch (Exception e) {
                            }
                            userCreditLogDAO.createUserCreditLog(userCreditLog);
                            // Update user credit in the md_profile table
                            prize.setCredit(credit);
                            Profile profile = new Profile();
                            profile.setUserId(userId);
                            profile.setCredits(credit);
                            profile.setUtime(now);
                            profileDAO.updateProfile(profile);
                        }
                    }
                }

            }
        }

        // Send a notification to the user if the user get a prize
        // otherwise, throw no prize Exception
        if (prize == null) {
            throw new ModeException(Message.NO_PRIZE);
        }

        if (BaseConfig.LUCK_DRAW_TYPE_ITEM.equalsIgnoreCase(prize.getType())) {
            try {
                // Send a notification to the user
                sendNotification(userId, prize);
            } catch (Exception e) {
            }
        }
        Response res = new Response(BaseConfig.OPERATION_SUCCEEDED, BaseConfig.SUCCESSFUL_MESSAGE);
        res.setPayload(prize);
        return res;
    }

    private void sendNotification(Integer userId, Prize prize) {
        final Long now = System.currentTimeMillis();
        try {
            /* Send a notification to the user */

            // Get notification template defined
            Config config = new Config();
            config.setAttributeName("luckyDrawNotification");
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

            // Set notification properties
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
