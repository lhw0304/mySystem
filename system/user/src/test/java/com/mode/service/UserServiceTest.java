package com.mode.service;

import org.hamcrest.CoreMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mode.base.Response;
import com.mode.dao.AccountDAO;
import com.mode.dao.ProfileDAO;
import com.mode.dto.AccessToken;
import com.mode.dto.Registry;
import com.mode.entity.Account;
import com.mode.entity.HttpResponseEntity;
import com.mode.entity.Profile;
import com.mode.exception.ModeTestException;
import com.mode.security.TokenClaims;
import com.mode.util.TokenHandler;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * The test service contains the main test logic code.
 * <p>
 * Created by Lei on 3/21/16.
 */
@Service
public class UserServiceTest extends BaseService {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private ProfileDAO profileDAO;


    /**
     * Sign up api response result check
     *
     * @param entity
     */
    public void signupService(HttpResponseEntity entity, Registry registry) {

        /* Check if the account and profile are successfully created in the database. */
        Account account = new Account();
        account.setUsername(registry.getUsername());
        account.setPassword(registry.getPassword());
        account = accountDAO.getAccount(account);
        if (account == null) {
            throw new ModeTestException("User Account is not created when signing up!");
        }

        Profile profile = new Profile();
        profile.setUserId(account.getId());
        profile = profileDAO.getProfile(profile);
        if (profile == null) {
            throw new ModeTestException("User Profile is not created when signing up!");
        }

        /* Check if the request/response is successful with 200 status code.
           And check if the result is what we expected. */
        Response res = parseResponseBody(entity.getBody());

        // Check if the status code and message are correct.
        successResponseStatusCodeCheck(entity.getStatus());
        successResponseMessageCheck(res.getCode(), res.getMessage());

        // Check if the accessToken is correct
        AccessToken accessToken = om.convertValue(res.getPayload(), AccessToken.class);
        String token = accessToken.getAccessToken();
        TokenClaims claims = TokenHandler.parseUserFromToken(token);
        assertThat(claims.getUsername(), CoreMatchers.equalTo(registry.getUsername()));
        assertThat(claims.getSource(), CoreMatchers.equalTo(registry.getSource()));
    }

    /**
     * Login api response result check
     *
     * @param entity
     */
    public void loginService(HttpResponseEntity entity, Registry registry) {

        /* Check if the request/response is successful with 200 status code.
           And check if the result is what we expected. */
        Response res = parseResponseBody(entity.getBody());

        // Check if the status code and message are correct.
        successResponseStatusCodeCheck(entity.getStatus());
        successResponseMessageCheck(res.getCode(), res.getMessage());

        // Check if the accessToken is correct
        AccessToken accessToken = om.convertValue(res.getPayload(), AccessToken.class);
        String token = accessToken.getAccessToken();
        TokenClaims claims = TokenHandler.parseUserFromToken(token);
        assertThat(claims.getUsername(), CoreMatchers.equalTo(registry.getUsername()));
        assertThat(claims.getSource(), CoreMatchers.equalTo(registry.getSource()));
    }

    /**
     * Get User Self Info response result check
     *
     * @param entity
     */
    public void userSelfService(HttpResponseEntity entity, String token) {
        /* Check if the request/response is successful with 200 status code.
           And check if the result is what we expected. */
        Response res = parseResponseBody(entity.getBody());

        // Check if the status code and message are correct.
        successResponseStatusCodeCheck(entity.getStatus());
        successResponseMessageCheck(res.getCode(), res.getMessage());

        // Check if the result profile is correct
        Profile profile = om.convertValue(res.getPayload(), Profile.class);

        String username = TokenHandler.parseUserFromToken(token).getUsername();
        Account account = new Account();
        account.setUsername(username);
        account = accountDAO.getAccount(account);

        assertThat(profile.getUserId(), CoreMatchers.equalTo(account.getId()));
        assertThat(profile.getUsd(), CoreMatchers.is(0f));
    }

    /**
     * Get feeds for the user API response result check service
     *
     * @param entity
     */
    public static void userSelfFeedsService(HttpResponseEntity entity) {
        successResponseStatusCodeCheck(entity.getStatus());
    }

    /**
     * Get User saved feeds info api result check service
     *
     * @param entity
     */
    public static void userSelfSavesService(HttpResponseEntity entity) {
        successResponseStatusCodeCheck(entity.getStatus());
    }

    /**
     * Get User liked,saved,commented feeds info api result check service
     *
     * @param entity
     */
    public static void userSelfEngagementsService(HttpResponseEntity entity) {
        successResponseStatusCodeCheck(entity.getStatus());
    }

    /**
     * Get User task info api result check service
     *
     * @param entity
     */
    public static void userSelfTasksService(HttpResponseEntity entity) {
        successResponseStatusCodeCheck(entity.getStatus());
    }

    /**
     * Get User feedback info api result check service
     *
     * @param entity
     */
    public static void userSelfFeedbacksService(HttpResponseEntity entity) {
        successResponseStatusCodeCheck(entity.getStatus());
    }

    /**
     * Get User Notifications info api result check service
     *
     * @param entity
     */
    public static void userSelfNotificationsService(HttpResponseEntity entity) {
        successResponseStatusCodeCheck(entity.getStatus());
    }

    /**
     * Get User Info info api result check service
     *
     * @param entity
     */
    public void getUserInfoService(HttpResponseEntity entity, int userId) {
        /* Check if the request/response is successful with 200 status code.
           And check if the result is what we expected. */
        Response res = parseResponseBody(entity.getBody());

        // Check if the status code and message are correct.
        successResponseStatusCodeCheck(entity.getStatus());
        successResponseMessageCheck(res.getCode(), res.getMessage());

        // Check if the result profile is correct
        Profile profile = om.convertValue(res.getPayload(), Profile.class);

        Profile profileCheck = new Profile();
        profileCheck.setUserId(userId);
        profileCheck = profileDAO.getProfile(profileCheck);

        assertThat(profile.toString(), CoreMatchers.equalTo(profileCheck.toString()));
    }

    /**
     * List User Info info api result check service
     *
     * @param entity
     */
    public static void listUserInfoService(HttpResponseEntity entity) {
        successResponseStatusCodeCheck(entity.getStatus());
    }
}
