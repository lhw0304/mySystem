package com.mode.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.support.logging.Log;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mode.base.BaseConfig;
import com.mode.base.Message;
import com.mode.base.Response;
import com.mode.base.Status;
import com.mode.dao.AccountDAO;
import com.mode.dao.FeedDAO;
import com.mode.dao.ProfileDAO;
import com.mode.dao.UserLoginLogDAO;
import com.mode.dto.AccessToken;
import com.mode.dto.Registry;
import com.mode.dto.UserFeed;
import com.mode.dto.UserSaves;
import com.mode.entity.Account;
import com.mode.entity.Feed;
import com.mode.entity.Item;
import com.mode.entity.Profile;
import com.mode.entity.Task;
import com.mode.entity.UserEngagement;
import com.mode.entity.UserFeedback;
import com.mode.entity.UserLoginLog;
import com.mode.entity.UserNotification;
import com.mode.exception.ModeException;
import com.mode.service.LogService;
import com.mode.service.UserService;
import com.mode.util.LoginValidation;
import com.mode.util.TokenHandler;

/**
 * Created by Lei on 3/22/16.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private ProfileDAO profileDAO;

    @Autowired
    private UserLoginLogDAO userLoginLogDAO;

    @Autowired
    private FeedDAO feedDAO;

    @Autowired
    private LogService logService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Log in
     *
     * @param registry
     * @return
     */
    @Override
    public Response login(Registry registry) {

        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(registry.getUsername(), registry
                            .getPassword());
            // try to validate username and password
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            // Save the successfully authenticated object in security context.
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (AuthenticationException e) {
            throw new ModeException(Message.UNAUTHORIZED);
        }

        if (registry.getUserLoginLog() != null) {
            // Record the device info of the new sign up user
            // Go on the login process if some exception happened.
            try {
                logService.log(BaseConfig.LOG_TYPE_USER_LOGIN, registry.getUserLoginLog()
                        .toString());
            } catch (Exception e) {
            }

        }

        // Generate the Token
        String token = TokenHandler.createTokenForUser(registry.getUsername(), registry.getSource
                ());
        AccessToken accessToken = new AccessToken(token);

        // Set Response
        Response res = new Response(Message.SUCCESS);
        res.setPayload(accessToken);

        return res;
    }

    /**
     * Sign up
     *
     * @param registry
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response signup(Registry registry) {

        // Validate if the username is according to the rules.
        LoginValidation.validate(registry.getUsername());

        final long now = System.currentTimeMillis();

        // Record the device info of the new sign up user
        // Go on the sign up process if some exception happened.
        if (registry.getUserLoginLog() != null) {
            try {
                logService.log(BaseConfig.LOG_TYPE_USER_LOGIN, registry.getUserLoginLog()
                        .toString());
            } catch (Exception e) {
            }
        }

        // Generate the Access Token
        String token = TokenHandler.createTokenForUser(registry.getUsername(), registry.getSource
                ());
        AccessToken accessToken = new AccessToken(token);

        // Create account and profile for the user
        try {
            Account account = new Account();
            account.setUsername(registry.getUsername());
            account.setPassword(registry.getPassword());
            account.setSource(registry.getSource());
            account.setStatus(Status.NORMAL.getCode());
            account.setCtime(now);
            account.setUtime(now);
            account.setRole(BaseConfig.ROLE_USER);
            accountDAO.createAccount(account);

            Profile profile = new Profile();
            profile.setUtime(now);
            profile.setCtime(now);
            profile.setUserId(account.getId());
            profile.setUsd(0f);
            profileDAO.createProfile(profile);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ModeException(Message.ERROR_CREATE_ACCOUNT);
        }

        // Set Response
        Response res = new Response(Message.SUCCESS);
        res.setPayload(accessToken);

        return res;
    }

    /**
     * Get the user self info
     *
     * @param userId
     * @return
     */
    @Override
    public Response userSelf(int userId) {
        Profile profile = new Profile();
        profile.setUserId(userId);

        try {
            profile = profileDAO.getProfile(profile);
        } catch (Exception e) {
            throw new ModeException(Message.PROFILE_NOT_EXIST);
        }

        Response res = new Response(Message.SUCCESS);
        res.setPayload(profile);

        return res;
    }

    /**
     * Get a list of feeds for the user
     *
     * @param userId
     * @param limit
     * @param offset
     * @param country
     * @return
     */
    @Override
    public Response userSelfFeeds(int userId, Integer limit, Integer offset, String country) {

        List<UserFeed> feeds = new ArrayList<>();

        // Here leave the space for dao methods to get feeds from database

        Response res = new Response(Message.SUCCESS);
        res.setPayload(feeds);
        return res;
    }

    /**
     * Get a list of saved things the user have
     *
     * @param userId
     * @return
     */
    @Override
    public Response userSelfSaves(int userId, String type, String country, Integer limit, Integer
            offset) {
        UserSaves userSaves = new UserSaves();

        List<Feed> savedArticles = new ArrayList<>();
        List<Item> savedItems = new ArrayList<>();
        List<Feed> savedCollections = new ArrayList<>();
        List<Feed> savedVideos = new ArrayList<>();

        // Here leave the space for dao methods to get saved feeds and items from database

        userSaves.setSavedArticles(savedArticles);
        userSaves.setSavedCollections(savedCollections);
        userSaves.setSavedVideos(savedVideos);
        userSaves.setSavedItems(savedItems);

        Response res = new Response(Message.SUCCESS);
        res.setPayload(userSaves);

        return res;
    }

    /**
     * Get the data about feeds/items the user liked, saved, commented, followed.
     *
     * @param userId
     * @return
     */
    @Override
    public Response userSelfEngagements(int userId) {
        List<UserEngagement> userEngagements = new ArrayList<>();

        /* Leave space for getting engagement data from database lately. */

        Response res = new Response(Message.SUCCESS);
        res.setPayload(userEngagements);

        return null;
    }

    /**
     * Get a list of tasks for the user
     *
     * @param userId
     * @return
     */
    @Override
    public Response userSelfTasks(int userId) {

        List<Task> tasks = new ArrayList<>();

        Response res = new Response(Message.SUCCESS);
        res.setPayload(tasks);
        return res;
    }

    /**
     * Get a list of feedback of the user
     *
     * @param userId
     * @param limit
     * @param offset
     * @param country
     * @return
     */
    @Override
    public Response userSelfFeedbacks(int userId, Integer limit, Integer offset, String country) {

        List<UserFeedback> userFeedbacks = new ArrayList<>();

        Response res = new Response(Message.SUCCESS);
        res.setPayload(userFeedbacks);
        return res;
    }

    /**
     * Get a list of notification of the user
     *
     * @param userId
     * @param limit
     * @param offset
     * @param country
     * @return
     */
    @Override
    public Response userSelfNotifications(int userId, Integer limit, Integer offset, String
            country) {
        List<UserNotification> notifications = new ArrayList<>();

        Response res = new Response(Message.SUCCESS);
        res.setPayload(notifications);
        return res;
    }

    /**
     * Get the info of some user
     *
     * @param userId
     * @return
     */
    @Override
    public Response getUserInfo(int userId) {
        Profile profile = new Profile();
        profile.setUserId(userId);

        try {
            profile = profileDAO.getProfile(profile);
        } catch (Exception e) {
            throw new ModeException(Message.FAILURE);
        }

        Response res = new Response(Message.SUCCESS);
        res.setPayload(profile);

        return res;
    }

    /**
     * Get a list user info
     *
     * @param limit
     * @param offset
     * @param country
     * @return
     */
    @Override
    public Response listUserInfo(Integer limit, Integer offset, String country) {

        List<Profile> profiles = new ArrayList<>();

        Response res = new Response(Message.SUCCESS);
        res.setPayload(profiles);

        return res;
    }
}
