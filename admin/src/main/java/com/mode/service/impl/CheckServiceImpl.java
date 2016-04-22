package com.mode.service.impl;

import com.mode.config.Message;
import com.mode.config.Response;
import com.mode.dao.CheckDAO;
import com.mode.entity.Check;
import com.mode.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/10.
 */
@Service
public class CheckServiceImpl implements CheckService{

    @Autowired
    private CheckDAO checkDAO;

    @Override
    public Response createCheck(Integer userId, MultipartHttpServletRequest mRequest, Integer answer) {
        Response res = new Response();
        try{
            Check check = new Check();
            check.setUserId(userId);
            String content = mRequest.getParameter("content");
            String knowledge = mRequest.getParameter("knowledge");
            check.setContent(content);
            check.setKnowledge(knowledge);
            check.setAnswer(answer);
            long now = System.currentTimeMillis();
            check.setCtime(now);
            Integer success = checkDAO.createCheck(check);
            if (success == 0 ) {
                res.setMessage(Message.DATABASE);
            }
            res.setMessage(Message.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.CATCH);
        }
        return res;
    }

    @Override
    public Response deleteCheck(Integer id) {
        Response res = new Response();
        try {
            Integer success = checkDAO.deleteCheck(id);
            if (success == 0 ) {
                res.setMessage(Message.DATABASE);
            }
            res.setMessage(Message.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.CATCH);
        }
        return res;
    }

    @Override
    public Response getCheck(Integer id) {
        Response res = new Response();
        try {
            Check check = checkDAO.getCheck(id);
            if (check == null) {
                res.setMessage(Message.NO_MORE_CHECK);
            }
            Map<String, Object> payload = new HashMap<>();
            payload.put("check",check);
            res.setMessage(Message.SUCCESS);
            res.setPayload(payload);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.CATCH);
        }
        return res;
    }

    @Override
    public Response getCheckList(Integer userId, Integer limit, Integer offset) {
        Response res = new Response();
        try {
            List<Check> list = checkDAO.getCheckListByUserId(userId, limit, offset);
            if (list == null || list.isEmpty()) {
                res.setMessage(Message.NO_MORE_CHECK);
                return res;
            }
            Integer total = checkDAO.countCheck(userId);
            Map<String, Object> payload = new HashMap<>();
            payload.put("list",list);
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
    public Response updateCheck(Integer id, Integer userId, MultipartHttpServletRequest mRequest, Integer answer) {
        Response res = new Response();
        try {
            Check check = new Check();
            Integer user = checkDAO.getCheck(id).getUserId();
            if(user != userId ) {
                res.setMessage(Message.NOT_MATCH);
            }
            String content = mRequest.getParameter("content");
            check.setContent(content);
            check.setAnswer(answer);
            check.setId(id);
            Integer success = checkDAO.updateCheck(check);
            if (success == 0) {
                res.setMessage(Message.DATABASE);
            }
            res.setMessage(Message.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.CATCH);
        }
        return res;
    }
}
