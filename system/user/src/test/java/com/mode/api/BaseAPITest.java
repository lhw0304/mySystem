package com.mode.api;

import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mode.base.BaseConfig;
import com.mode.base.Status;
import com.mode.dao.AccountDAO;
import com.mode.dao.AdDAO;
import com.mode.dao.ConfigDAO;
import com.mode.dao.CreditZoneDAO;
import com.mode.dao.FeedDAO;
import com.mode.dao.ItemDAO;
import com.mode.dao.LuckyWheelDAO;
import com.mode.dao.ProfileDAO;
import com.mode.dao.TaskDAO;
import com.mode.dao.UserCommentDAO;
import com.mode.dao.UserFeedbackDAO;
import com.mode.dao.UserNotificationDAO;
import com.mode.dto.Registry;
import com.mode.entity.Account;
import com.mode.entity.HttpResponseEntity;
import com.mode.entity.Profile;
import com.mode.service.CreditZoneServiceTest;
import com.mode.service.FeedServiceTest;
import com.mode.service.ItemServiceTest;
import com.mode.service.UserServiceTest;
import com.mode.service.data.AdData;
import com.mode.service.data.UserData;
import com.mode.util.TokenHandler;

/**
 * This the base class for all the tests.
 * In here, common methods and variables are defined in this base class.
 *
 * Created by Lei on 3/21/16.
 */
public class BaseAPITest {

    @Autowired
    protected UserServiceTest userServiceTest;

    @Autowired
    protected FeedServiceTest feedServiceTest;

    @Autowired
    protected ItemServiceTest itemServiceTest;

    @Autowired
    protected CreditZoneServiceTest creditZoneServiceTest;

    @Autowired
    protected FeedDAO feedDAO;

    @Autowired
    protected ConfigDAO configDAO;

    @Autowired
    protected ItemDAO itemDAO;

    @Autowired
    protected CreditZoneDAO creditZoneDAO;

    @Autowired
    protected TaskDAO taskDAO;

    @Autowired
    protected UserFeedbackDAO userFeedbackDAO;

    @Autowired
    protected UserNotificationDAO userNotificationDAO;

    @Autowired
    protected UserCommentDAO userCommentDAO;

    @Autowired
    protected LuckyWheelDAO luckyWheelDAO;

    @Autowired
    protected AccountDAO accountDAO;

    @Autowired
    protected ProfileDAO profileDAO;

    @Autowired
    protected AdDAO adDAO;

    @Autowired
    protected ObjectMapper om;

    // Test template to do http request
    protected static RestTemplate template = new RestTemplate();

    protected static String accessToken =
            "accessToken=eyJhbGciOiJIUzUxMiJ9" +
                    ".eyJzdWIiOiJkYW5pZWxtYUBraXRldGVhLmNvbSIsImlhdCI6MTQ1ODUyOTE1MiwiZXhwIjoxNDY3MTY5MTUyfQ.O1XYXZ0vRI0H7WPzUiRnidxkBL0n4MSoEJ-LimR1xYAKBDxeCXpm_oD9USw9x8R7ShllV4lcDcPB2QMYlmb6GQ";

    protected static String token = "GV";

    // The test server domain
    protected static final String DOMAIN = "http://localhost:8081";

    protected static int userId = 0;

    protected static int costCredit = 20;


    // Setup test. Call it before every test
    @Before
    public void setup() {

        final long now = System.currentTimeMillis();

        Registry registry = UserData.getRegistry("mode");

        Account account = new Account();
        account.setUsername(registry.getUsername());
        account.setPassword(registry.getPassword());
        account.setCtime(now);
        account.setUtime(now);
        account.setSource(registry.getSource());
        account.setRole(BaseConfig.ROLE_USER);
        account.setStatus(Status.NORMAL.getCode());
        accountDAO.createAccount(account);

        Profile profile = UserData.getProfile(account.getId());
        profileDAO.createProfile(profile);

        token = TokenHandler.createTokenForUser(registry.getUsername(), registry.getSource());
        accessToken = "accessToken=" + token;
        userId = account.getId();

        System.out.println("Test is starting.");
    }

    // Tear down. Call it after every test finished
    @After
    public void teardown() {
        deleteAccount();
        System.out.println("Test is ended.");
    }

    protected void deleteAccount() {
        Registry registry = UserData.getRegistry("mode");
        Account account = new Account();
        account.setUsername(registry.getUsername());
        account.setPassword(registry.getPassword());

        account = accountDAO.getAccount(account);
        if (account != null && account.getId() != null) {
            profileDAO.deleteProfile(account.getId());
            accountDAO.deleteAccount(account.getId());
        }
    }

    /**
     * Do http post
     *
     * @param url
     * @return
     */
    protected HttpResponseEntity httpPost(String url, HttpHeaders headers, String body) {
        HttpEntity<?> httpEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> entity = template.postForEntity(url, httpEntity, String.class);

        HttpResponseEntity responseEntity = new HttpResponseEntity();
        responseEntity.setBody(entity.getBody());
        responseEntity.setHeaders(entity.getHeaders());
        responseEntity.setStatus(entity.getStatusCode());

        return responseEntity;
    }

    /**
     * Do http get
     *
     * @param url
     * @return
     */
    protected HttpResponseEntity httpGet(String url) {
        ResponseEntity<String> entity = template.getForEntity(url, String.class);

        HttpResponseEntity responseEntity = new HttpResponseEntity();
        responseEntity.setBody(entity.getBody());
        responseEntity.setHeaders(entity.getHeaders());
        responseEntity.setStatus(entity.getStatusCode());

        return responseEntity;
    }
}
