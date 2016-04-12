package com.mode.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mode.config.AppConfig;
import com.mode.config.Message;
import com.mode.config.ObjectType;
import com.mode.config.Response;
import com.mode.dao.ProfileDAO;
import com.mode.dao.UserCommentDAO;
import com.mode.entity.Profile;
import com.mode.entity.UserComment;
import com.mode.service.SocialService;

/**
 * Created by zhaoweiwei on 15/11/30.
 */
@Service
public class SocialServiceImpl implements SocialService {

    @Autowired
    private UserCommentDAO userCommentDAO;

    @Autowired
    private ProfileDAO profileDAO;


    @Override
    public Response createStylistComment(Integer stylistId, Integer articleId, String content) {
        Response res = new Response();

        final Long now = System.currentTimeMillis();

        /* Decode UTF-8 encoding string. Sometimes the client side will send utf-8 encoding
        string. So it is necessary to decode it before store it into database. */
        try {
            content = URLDecoder.decode(content, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        UserComment userComment = new UserComment();
        userComment.setCtime(now);
        userComment.setContent(content);
        userComment.setObjectType(ObjectType.ARTICLE.getType());
        userComment.setObjectId(articleId);
        userComment.setUserId(stylistId);

        try {
            Integer succeedToCreate = userCommentDAO.createUserComment(userComment);
            if (succeedToCreate == 0) {
                res.setMessage(Message.FAILURE);
            } else {
                Map<String, Object> payload = new HashMap<>();
                payload.put("comment", userComment);
                res.setPayload(payload);
                res.setMessage(Message.SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.FAILURE);
        }

        return res;
    }

    @Override
    public Response listComments(Integer headlineId, Integer limit,
                                 Integer offset) {
        Response res = new Response();

        limit = (limit == null) ? AppConfig.DEFAULT_LIMIT : limit;
        offset = (offset == null) ? AppConfig.DEFAULT_OFFSET : offset;
        try {
            List<UserComment> userComments = userCommentDAO.listUserCommentAndProfile(headlineId,
                    limit, offset);

            if (userComments == null || userComments.size() == 0) {
                res.setMessage(Message.NO_MORE_RECORD);
                return res;
            }

            Map<String, Object> payload = new HashMap<>();
            payload.put("list", userComments);
            res.setPayload(payload);
            res.setMessage(Message.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.FAILURE);
        }

        return res;
    }
}
