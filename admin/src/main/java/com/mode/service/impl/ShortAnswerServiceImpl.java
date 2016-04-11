package com.mode.service.impl;

import com.mode.config.Message;
import com.mode.config.Response;
import com.mode.dao.ShortAnswerDAO;
import com.mode.entity.ShortAnswer;
import com.mode.service.ShortAnswerService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by �ľ� on 2016/3/11.
 */
@Service
public class ShortAnswerServiceImpl implements ShortAnswerService{

    @Autowired
    private ShortAnswerDAO shortAnswerDAO;

    @Override
    public Response createShortAnswer(Integer userId, String content, String answer) {
        Response res = new Response();
        try {
            ShortAnswer shortAnswer = new ShortAnswer();
            long now = System.currentTimeMillis();
            shortAnswer.setUserId(userId);
            shortAnswer.setContent(content);
            shortAnswer.setAnswer(answer);
            shortAnswer.setCtime(now);
            Integer success = shortAnswerDAO.createShortAnswer(shortAnswer);
            if (success == 0) {
                res.setMessage(Message.FAILURE);
                return res;
            }
            res.setMessage(Message.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.CATCH);
        }
        return res;
    }

    @Override
    public Response deleteShortAnswer(Integer id) {
        Response res = new Response();
        try {
            Integer success = shortAnswerDAO.deleteShortAnswer(id);
            if(success == 0) {
                res.setMessage(Message.DATABASE);
                return res;
            }
            res.setMessage(Message.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.CATCH);
        }
        return res;
    }

    @Override
    public Response getShortAnswer(Integer id) {
        Response res = new Response();
        try {
            ShortAnswer shortAnswer = shortAnswerDAO.getShortAnswer(id);
            if (shortAnswer == null) {
                res.setMessage(Message.NO_MORE_SINGLE_SELECT);
                return res;
            }
            Map<String, Object> payload = new HashMap<>();
            payload.put("shortAnswer",shortAnswer);
            res.setPayload(payload);
            res.setMessage(Message.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.CATCH);
        }
        return res;
    }

    @Override
    public Response getShortAnswerList(Integer userId, Integer limit, Integer offset) {
        Response res = new Response();
        try {
            List<ShortAnswer> list = shortAnswerDAO.getShortAnswerListByUserId(userId, limit,
                    offset);
            if (list.isEmpty()) {
                res.setMessage(Message.NO_MORE_SINGLE_SELECT);
                return res;
            }
            Integer total = shortAnswerDAO.countShortAnswer(userId);
            Map<String, Object> payload = new HashMap<>();
            payload.put("list", list);
            payload.put("total",total);
            res.setMessage(Message.SUCCESS);
            res.setPayload(payload);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.CATCH);
        }
        return res;
    }

    @Override
    public Response updateShortAnswer(Integer id, Integer userId, String content, String answer) {
        Response res = new Response();
        try {
            ShortAnswer shortAnswer = shortAnswerDAO.getShortAnswer(id);
            Integer user = shortAnswer.getUserId();
            if(user != userId) {
                res.setMessage(Message.ACCOUNT_NOT_ACTIVATED);
                return res;
            }
            shortAnswer.setContent(content);
            shortAnswer.setAnswer(answer);
            Integer success = shortAnswerDAO.updateShortAnswer(shortAnswer);
            if(success == 0) {
                res.setMessage(Message.DATABASE);
                return res;
            }
            res.setMessage(Message.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.CATCH);
        }
        return res;
    }
}
