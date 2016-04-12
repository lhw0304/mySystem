package com.mode.service.impl;

import com.mode.dao.ProfileDAO;
import com.mode.entity.Profile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mode.config.AppConfig;
import com.mode.config.Message;
import com.mode.config.Response;
import com.mode.config.Status;
import com.mode.dao.HeadlineDAO;
import com.mode.entity.Headline;
import com.mode.service.HeadlineService;

/**
 * Created by zhaoweiwei on 15/11/19.
 */
@Service
public class HeadlineServiceImpl implements HeadlineService {

    @Autowired
    private HeadlineDAO headlineDAO;

    @Autowired
    private ProfileDAO profileDAO;

    @Override
    public Response updateHeadlineStatus(Integer id, Integer status, Integer userId) {
        Response res = new Response();
        try {
            Headline headline = new Headline();
            headline.setId(id);
            headline.setStatus(status);
            Integer success = headlineDAO.updateHeadline(headline);
            if (userId != null) {
                Profile profile = profileDAO.getProfileByUserId(userId);
                if (status == 1) {
                    profile.setArticleNumber(profile.getArticleNumber() + 1);
                } else {
                    profile.setArticleNumber(profile.getArticleNumber() - 1);
                }
                profileDAO.updateProfile(profile);
            }
            if (success == null || success == 0) {
                res.setMessage(Message.FAILURE);
                return res;
            }
            res.setMessage(Message.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.FAILURE);
        }
        return res;
    }

    @Override
    public Response listHeadlines(Integer stylistId, Integer limit, Integer offset) {

        Response res = new Response();

        limit = (limit == null) ? AppConfig.DEFAULT_LIMIT : limit;
        offset = (offset == null) ? AppConfig.DEFAULT_OFFSET : offset;
        try {
            // Get headlines from database
            List<Headline> headlines = headlineDAO.listHeadlinesByAuthorId(stylistId,
                    limit, offset);
            if (headlines == null || headlines.isEmpty()) {
                res.setMessage(Message.NO_MORE_RECORD);
                return res;
            }
            Integer total = headlineDAO.getHeadlineCountByAuthorId(stylistId);

            Map<String, Object> payload = new HashMap<>();
            payload.put("list", headlines);
            payload.put("total", total);
            res.setMessage(Message.SUCCESS);
            res.setPayload(payload);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.FAILURE);
        }

        return res;
    }

    @Override
    public Response getHeadline(Integer id) {
        Response res = new Response();
        try {
            Headline headline = headlineDAO.getHeadline(id);
            if (headline == null) {
                res.setMessage(Message.NO_MORE_HEADLINE);
            } else {
                res.setMessage(Message.SUCCESS);
                Map<String, Object> payload = new HashMap<String, Object>();
                payload.put("headline", headline);
                res.setPayload(payload);
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.FAILURE);
        }
        return res;
    }
}
