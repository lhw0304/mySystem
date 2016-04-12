package com.mode.service;

import com.mode.base.Response;
import com.mode.entity.UserComment;

/**
 * Created by Lei on 3/23/16.
 */
public interface FeedService {

    /**
     * Get one feed via feed id
     *
     * @param feedId
     * @return
     * @throws Exception
     */
    public Response getFeed(Integer feedId) throws Exception;

    /**
     * Get list comments of some feed with specific limit and offset
     *
     * @param feedId
     * @param limit
     * @param offset
     * @return
     */
    public Response listFeedComments(Integer feedId, Integer limit, Integer offset);

    /**
     * Some user make a comment to some feed
     *
     * @param feedId
     * @param userId
     * @param userComment
     * @return
     */
    public Response createAFeedComment(Integer feedId, Integer userId, UserComment userComment);
}
