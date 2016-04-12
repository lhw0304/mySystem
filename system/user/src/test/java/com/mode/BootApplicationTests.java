package com.mode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.mode.api.BaseAPITest;
import com.mode.dao.ConfigDAO;
import com.mode.dao.CreditZoneDAO;
import com.mode.dao.FeedDAO;
import com.mode.dao.ItemDAO;
import com.mode.dao.LuckyWheelDAO;
import com.mode.dao.TaskDAO;
import com.mode.dao.UserCommentDAO;
import com.mode.dao.UserFeedbackDAO;
import com.mode.dao.UserNotificationDAO;
import com.mode.dto.Registry;
import com.mode.entity.Ad;
import com.mode.entity.Config;
import com.mode.entity.CreditZone;
import com.mode.entity.Feed;
import com.mode.entity.HttpResponseEntity;
import com.mode.entity.Item;
import com.mode.entity.LuckyWheel;
import com.mode.entity.Task;
import com.mode.entity.UserActionLog;
import com.mode.entity.UserComment;
import com.mode.entity.UserFeedback;
import com.mode.entity.UserNotification;
import com.mode.service.AdServiceTest;
import com.mode.service.ConfigurationServiceTest;
import com.mode.service.CreditZoneServiceTest;
import com.mode.service.FeedServiceTest;
import com.mode.service.ItemServiceTest;
import com.mode.service.LoggingServiceTest;
import com.mode.service.LuckyWheelServiceTest;
import com.mode.service.UserServiceTest;
import com.mode.service.data.AdData;
import com.mode.service.data.ConfigData;
import com.mode.service.data.CreditZoneData;
import com.mode.service.data.FeedData;
import com.mode.service.data.ItemData;
import com.mode.service.data.LuckyWheelData;
import com.mode.service.data.TaskData;
import com.mode.service.data.UserData;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class BootApplicationTests extends BaseAPITest {


    /* Positive Unit Tests for User APIs */
    @Test
    public void signupApiTest() throws Exception {

        // Construct the request with test data
        // And delete existed data.
        Registry registry = UserData.getRegistry("mode");
        deleteAccount();

        // Api URL
        String url = DOMAIN + "/v3/signup";

        // Headers
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, "application/json");

        // Do test request
        HttpResponseEntity entity = httpPost(url, httpHeaders, om.writeValueAsString(registry));

        System.out.println(entity.getBody());
        // Check the result.
        userServiceTest.signupService(entity, registry);
    }

    @Test
    public void loginApiTest() throws Exception {

        // Construct the request with test data
        Registry registry = UserData.getRegistry("mode");
        String url = DOMAIN + "/v3/login";

        // Headers
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, "application/json");

        // Do test request
        HttpResponseEntity entity = httpPost(url, httpHeaders, om.writeValueAsString(registry));

        System.out.println(entity.getBody());

        // Check the result.
        userServiceTest.loginService(entity, registry);
    }

    @Test
    public void userSelfApiTest() throws Exception {

        String url = DOMAIN + "/v3/users/self?" + accessToken;

        HttpResponseEntity entity = httpGet(url);

        userServiceTest.userSelfService(entity, token);
    }

    @Test
    public void userSelfFeedsApiTest() {

        String url = DOMAIN + "/v3/users/self/feeds?" + accessToken;

        HttpResponseEntity entity = httpGet(url);

        UserServiceTest.userSelfFeedsService(entity);
    }

    @Test
    public void userSelfSavesApiTest() {
        String url = DOMAIN + "/v3/users/self/saves?" + accessToken;

        HttpResponseEntity entity = httpGet(url);

        UserServiceTest.userSelfSavesService(entity);
    }

    @Test
    public void userSelfEngagementsApiTest() {
        String url = DOMAIN + "/v3/users/self/engagements?" + accessToken;

        HttpResponseEntity entity = httpGet(url);

        UserServiceTest.userSelfEngagementsService(entity);
    }

    @Test
    public void userSelfTasksApiTest() {
        // Create test data of task
        Task task = TaskData.getTask();
        taskDAO.createTask(task);

        String url = DOMAIN + "/v3/users/self/tasks?" + accessToken;

        HttpResponseEntity entity = httpGet(url);

        UserServiceTest.userSelfTasksService(entity);

        // Delete test data
        taskDAO.deleteTask(task.getId());
    }

    @Test
    public void userSelfFeedbacksApiTest() {
        // Create test data of user feedbacks
        UserFeedback userFeedback = UserData.getUserFeedback(userId);
        userFeedbackDAO.createUserFeedback(userFeedback);

        String url = DOMAIN + "/v3/users/self/feedbacks?" + accessToken;

        HttpResponseEntity entity = httpGet(url);

        UserServiceTest.userSelfFeedbacksService(entity);

        // Delete test data
        userFeedbackDAO.deleteUserFeedback(userFeedback.getId());
    }

    @Test
    public void userSelfNotificationsApiTest() {
        // Create test data of user notifications
        UserNotification userNotification = UserData.getUserNotification(userId);
        userNotificationDAO.createUserNotification(userNotification);

        String url = DOMAIN + "/v3/users/self/notifications?" + accessToken;

        HttpResponseEntity entity = httpGet(url);

        UserServiceTest.userSelfNotificationsService(entity);

        // Delete test data
        userNotificationDAO.deleteUserNotification(userNotification.getId());
    }

    @Test
    public void getUserInfoApiTest() {
        String url = DOMAIN + "/v3/users/" + userId + "?" + accessToken;

        HttpResponseEntity entity = httpGet(url);

        userServiceTest.getUserInfoService(entity, userId);
    }

    @Test
    public void listUserInfoApiTest() {
        String url = DOMAIN + "/v3/users?" + accessToken;

        HttpResponseEntity entity = httpGet(url);

        UserServiceTest.listUserInfoService(entity);
    }


    /* Positive Tests for Item APIs */
    @Test
    public void getItemApiTest() throws Exception {
        // Construct the test data of item
        Item item = ItemData.getItem();
        itemDAO.createItem(item);

        // Construct the test request url and do request.
        String url = DOMAIN + "/v3/items/" + item.getId() + "?" + accessToken;
        HttpResponseEntity entity = httpGet(url);

        // Test service for doing detail testing logic
        itemServiceTest.getItemService(entity, item);

        // Delete test item data
        itemDAO.deleteItem(item.getId());
    }

    @Test
    public void listItemsApiTest() throws Exception {
        // Create the test data of item
        Item item = ItemData.getItem();
        itemDAO.createItem(item);

        String url = DOMAIN + "/v3/items?" + accessToken;

        HttpResponseEntity entity = httpGet(url);

        ItemServiceTest.listItemsService(entity);

        // Delete test item data
        itemDAO.deleteItem(item.getId());
    }

    /* Positive Tests for Configuration APIs*/
    @Test
    public void configApiTest() throws Exception {
        // Construct the test data of config
        Config config = ConfigData.getConfig();
        configDAO.createConfig(config);

        String url = DOMAIN + "/v3/configs?" + accessToken;

        HttpResponseEntity entity = httpGet(url);

        ConfigurationServiceTest.configService(entity);

        // Delete test data of config
        configDAO.deleteConfig(config.getId());
    }

    /* Positive Tests for CreditZone APIS */
    @Test
    public void listCreditZoneItemsApiTest() {
        // Construct the test data of Credit Zone
        CreditZone creditZone = CreditZoneData.getCreditZone();
        creditZoneDAO.createCreditZone(creditZone);

        String url = DOMAIN + "/v3/creditzones?" + accessToken;

        HttpResponseEntity entity = httpGet(url);

        CreditZoneServiceTest.listCreditZoneItemsService(entity);

        // Delete the test data
        creditZoneDAO.deleteCreditZone(creditZone.getId());
    }

    @Test
    public void redeemItemInCreditZoneApiTest() {
        // Construct the test data of Credit Zone
        CreditZone creditZone = CreditZoneData.getCreditZone();
        creditZoneDAO.createCreditZone(creditZone);

        // Construct the request url and do test request
        String url = DOMAIN + "/v3/creditzones/" + creditZone.getId() + "?" + accessToken +
                "&costCredit=" + costCredit;

        HttpResponseEntity entity = httpPost(url, null, null);

        creditZoneServiceTest.redeemItemInCreditZoneService(entity, creditZone, userId);

        // Delete the test data
        creditZoneDAO.deleteCreditZone(creditZone.getId());
    }

    /* Positive Tests for Feed APIs*/
    @Test
    public void getFeedApiTest() {

        // Create a feed for test
        Feed feed = FeedData.getFeed();
        feedDAO.createFeed(feed);

        String url = DOMAIN + "/v3/feeds/" + feed.getId() + "?" + accessToken;

        HttpResponseEntity entity = httpGet(url);

        feedServiceTest.getFeedService(entity, feed);

        // Delete that feed we created for test
        feedDAO.deleteFeed(feed.getId());
    }

    @Test
    public void listFeedCommentsApiTest() {
        // Create test data of feeds and comments
        Feed feed = FeedData.getFeed();
        feedDAO.createFeed(feed);
        UserComment userComment = UserData.getUserComment(feed.getId(), userId);
        userCommentDAO.createUserComment(userComment);

        String url = DOMAIN + "/v3/feeds/1/comments?" + accessToken;

        HttpResponseEntity entity = httpGet(url);

        FeedServiceTest.listFeedCommentsService(entity);

        // Delete test data
        feedDAO.deleteFeed(feed.getId());
        userCommentDAO.deleteUserComment(userComment.getId());
    }

    @Test
    public void createAFeedCommentApiTest() throws Exception {
        // Create a feed for test
        Feed feed = FeedData.getFeed();
        feedDAO.createFeed(feed);

        String userComment = om.writeValueAsString(UserData.getUserComment(feed.getId(), userId));
        String url = DOMAIN + "/v3/feeds/" + feed.getId() + "/comments?" + accessToken;

        // Headers
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, "application/json");

        HttpResponseEntity entity = httpPost(url, httpHeaders, userComment);

        feedServiceTest.createAFeedCommentService(entity, UserData.getUserComment(feed.getId(),
                userId));

        // Delete that feed we created for test
        feedDAO.deleteFeed(feed.getId());
    }

    /* Positive Lucky Wheel API Tests*/
    @Test
    public void listLuckyWheelItemsApiTest() {
        // Create test data of lucky wheel
        LuckyWheel luckyWheel = LuckyWheelData.getLuckyWheel();
        luckyWheelDAO.createLuckyWheel(luckyWheel);

        String url = DOMAIN + "/v3/luckywheels?" + accessToken;

        HttpResponseEntity entity = httpGet(url);

        LuckyWheelServiceTest.listLuckyWheelItemsService(entity);

        // Delete test data
        luckyWheelDAO.deleteLuckyWheel(luckyWheel.getId());
    }

    @Test
    public void spinLuckyWheelApiTest() {
        // Create test data of lucky wheel
        LuckyWheel luckyWheel = LuckyWheelData.getLuckyWheel();
        luckyWheelDAO.createLuckyWheel(luckyWheel);

        String url = DOMAIN + "/v3/luckywheels?" + accessToken;

        HttpResponseEntity entity = httpPost(url, null, null);

        LuckyWheelServiceTest.spinLuckyWheelService(entity);

        // Delete test data
        luckyWheelDAO.deleteLuckyWheel(luckyWheel.getId());
    }

    /* Positive Logging API tests*/
    @Test
    public void loggingApiTest() throws Exception {
        UserActionLog[] userActionLogs = new UserActionLog[10];

        String url = DOMAIN + "/v3/logs?" + accessToken;

        // Headers
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, "application/json");

        HttpResponseEntity entity = httpPost(url, httpHeaders, om.writeValueAsString
                (userActionLogs));

        LoggingServiceTest.loggingService(entity);
    }

    /* Positive Ad API Tests*/
    @Test
    public void listAdsApiTest() {
        // Create test data of ads
        Ad ad = AdData.getAd();
        adDAO.createAd(ad);

        String url = DOMAIN + "/v3/ads?" + accessToken;

        HttpResponseEntity entity = httpGet(url);

        AdServiceTest.listAdsService(entity);

        // Delete test data
        adDAO.deleteAd(ad.getId());
    }

}