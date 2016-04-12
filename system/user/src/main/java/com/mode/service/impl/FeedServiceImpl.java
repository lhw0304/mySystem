package com.mode.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mode.base.BaseConfig;
import com.mode.base.Message;
import com.mode.base.Response;
import com.mode.dao.FeedDAO;
import com.mode.dao.ProfileDAO;
import com.mode.dao.UserCommentDAO;
import com.mode.dto.UserFeed;
import com.mode.entity.Feed;
import com.mode.entity.Profile;
import com.mode.entity.UserComment;
import com.mode.service.FeedService;

/**
 * Created by Lei on 3/23/16.
 */
@Service
public class FeedServiceImpl implements FeedService {

    @Autowired
    private FeedDAO feedDAO;

    @Autowired
    private ProfileDAO profileDAO;

    @Autowired
    private UserCommentDAO userCommentDAO;

    private static ObjectMapper om = new ObjectMapper();

    /**
     * Get one feed via feed id
     *
     * @param feedId
     * @return
     * @throws Exception
     */
    @Override
    public Response getFeed(Integer feedId) throws Exception {
        Feed feed = new Feed();
        feed.setId(feedId);
        feed = feedDAO.getFeed(feed);

        UserFeed userFeed = new UserFeed(feed);

        // Add author information to the feed response
        if (feed.getAuthorId() != null) {
            Profile profile = new Profile();
            profile.setUserId(feed.getAuthorId());
            profile = profileDAO.getProfile(profile);
            userFeed.setAuthor(profile);
        }

        // Parse the content to JsonNode Object from String
        userFeed.setContent(om.readTree(feed.getContent()));

        Response res = new Response(Message.SUCCESS);
        res.setPayload(userFeed);

        return res;
    }

    /**
     * Get list comments of some feed with specific limit and offset
     *
     * @param feedId
     * @param limit
     * @param offset
     * @return
     */
    @Override
    public Response listFeedComments(Integer feedId, Integer limit, Integer offset) {
        List<UserComment> userComments = new ArrayList<>();

        Response res = new Response(Message.SUCCESS);
        res.setPayload(userComments);

        return res;
    }

    /**
     * Some user make a comment to some feed
     *
     * @param feedId
     * @param userId
     * @param userComment
     * @return
     */
    @Override
    public Response createAFeedComment(Integer feedId, Integer userId, UserComment userComment) {

        // Construct the user comment object and insert it into the database
        if (userComment.getCtime() == null) {
            final long now = System.currentTimeMillis();
            userComment.setCtime(now);
        }

        userComment.setUserId(userId);
        userComment.setLikes(0);
        userComment.setObjectId(feedId);

        userCommentDAO.createUserComment(userComment);

        Response res = new Response(Message.SUCCESS);
        res.setPayload(userComment);

        return res;
    }
}
