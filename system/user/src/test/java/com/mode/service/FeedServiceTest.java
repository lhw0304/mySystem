package com.mode.service;

import org.hamcrest.CoreMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mode.base.Response;
import com.mode.dao.ProfileDAO;
import com.mode.dao.UserCommentDAO;
import com.mode.dto.UserFeed;
import com.mode.entity.Feed;
import com.mode.entity.HttpResponseEntity;
import com.mode.entity.Profile;
import com.mode.entity.UserComment;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Lei on 3/21/16.
 */
@Service
public class FeedServiceTest extends BaseService {

    @Autowired
    private ProfileDAO profileDAO;

    @Autowired
    private UserCommentDAO userCommentDAO;

    public void getFeedService(HttpResponseEntity entity, Feed feedCheck) {
        /* Check if the request/response is successful with 200 status code.
           And check if the result is what we expected. */
        Response res = parseResponseBody(entity.getBody());

        // Check if the status code and message are correct.
        successResponseStatusCodeCheck(entity.getStatus());
        successResponseMessageCheck(res.getCode(), res.getMessage());

        // Check if the result Feed is correct
        UserFeed userFeed = om.convertValue(res.getPayload(), UserFeed.class);

        UserFeed userFeedCheck = new UserFeed(feedCheck);
        if (feedCheck.getAuthorId() != null) {
            Profile profile = new Profile();
            profile.setUserId(feedCheck.getAuthorId());
            profile = profileDAO.getProfile(profile);
            userFeedCheck.setAuthor(profile);
        }
        try {
            userFeedCheck.setContent(om.readTree(feedCheck.getContent()));
        }catch (Exception e){
        }

        assertThat(userFeed.toString(), CoreMatchers.equalTo(userFeedCheck.toString()));
    }

    public static void listFeedCommentsService(HttpResponseEntity entity) {
        successResponseStatusCodeCheck(entity.getStatus());
    }

    public void createAFeedCommentService(HttpResponseEntity entity, UserComment userCommentCheck) {
        /* Check if the request/response is successful with 200 status code.
           And check if the result is what we expected. */
        Response res = parseResponseBody(entity.getBody());

        // Check if the status code and message are correct.
        successResponseStatusCodeCheck(entity.getStatus());
        successResponseMessageCheck(res.getCode(), res.getMessage());

        UserComment userComment = om.convertValue(res.getPayload(), UserComment.class);

        assertThat(userComment.getUserId(), CoreMatchers.equalTo(userCommentCheck.getUserId()));
        assertThat(userComment.getObjectId(), CoreMatchers.equalTo(userCommentCheck.getObjectId()));

        userCommentDAO.deleteUserComment(userComment.getId());
    }
}
