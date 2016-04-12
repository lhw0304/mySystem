package com.mode.service;

import com.mode.base.Response;
import com.mode.dto.Registry;

/**
 * Created by Lei on 3/22/16.
 */
public interface UserService {

    /**
     * Log in
     *
     * @param registry
     * @return
     */
    public Response login(Registry registry);

    /**
     * Sign up
     *
     * @param registry
     * @return
     */
    public Response signup(Registry registry);

    /**
     * Get the user self info
     *
     * @param userId
     * @return
     */
    public Response userSelf(int userId);

    /**
     * Get a list of feeds for the user
     *
     * @param userId
     * @param limit
     * @param offset
     * @param country
     * @return
     */
    public Response userSelfFeeds(int userId, Integer limit, Integer offset, String country);

    /**
     * Get a list of saved things the user have
     *
     * @param userId
     * @param type
     * @param country
     * @param limit
     * @param offset
     * @return
     */
    public Response userSelfSaves(int userId, String type, String country, Integer limit, Integer
            offset);

    /**
     * Get the data about feeds/items the user liked, saved, commented, followed.
     *
     * @param userId
     * @return
     */
    public Response userSelfEngagements(int userId);

    /**
     * Get a list of tasks for the user
     *
     * @param userId
     * @return
     */
    public Response userSelfTasks(int userId);

    /**
     * Get a list of feedback of the user
     *
     * @param userId
     * @param limit
     * @param offset
     * @param country
     * @return
     */
    public Response userSelfFeedbacks(int userId, Integer limit, Integer offset, String country);

    /**
     * Get a list of notification of the user
     *
     * @param userId
     * @param limit
     * @param offset
     * @param country
     * @return
     */
    public Response userSelfNotifications(int userId, Integer limit, Integer offset, String
            country);

    /**
     * Get the info of some user
     *
     * @param userId
     * @return
     */
    public Response getUserInfo(int userId);

    /**
     * Get a list user info
     *
     * @param limit
     * @param offset
     * @param country
     * @return
     */
    public Response listUserInfo(Integer limit, Integer offset, String country);
}
