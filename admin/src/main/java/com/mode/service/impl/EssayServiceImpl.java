package com.mode.service.impl;

import com.mode.config.Message;
import com.mode.config.Response;
import com.mode.dao.EssayDAO;
import com.mode.entity.Essay;
import com.mode.service.EssayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/17.
 */
@Service
public class EssayServiceImpl implements EssayService{

    @Autowired
    private EssayDAO essayDAO;

    @Override
    public Response createEssay(Integer userId, String content, String answer) {
        Response res = new Response();
        try {
            Essay essay = new Essay();
            essay.setContent(content);
            essay.setAnswer(answer);
            essay.setUserId(userId);
            final long now = System.currentTimeMillis();
            essay.setCtime(now);
            Integer success = essayDAO.createEssay(essay);
            if (success == 0) {
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
    public Response deleteEssay(Integer id) {
        Response res = new Response();
        try {
            Integer success = essayDAO.deleteEssay(id);
            if (success == 0) {
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
    public Response getEssay(Integer id) {
        Response res = new Response();
        try {
            Essay essay = essayDAO.getEssay(id);
            if (essay == null) {
                res.setMessage(Message.NO_MORE_SINGLE_SELECT);
                return res;
            }
            res.setMessage(Message.SUCCESS);
            Map<String, Object> payload = new HashMap<>();
            payload.put("essay",essay);
            res.setPayload(payload);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.FAILURE);
        }
        return res;
    }

    @Override
    public Response getEssayList(Integer userId, Integer limit, Integer offset) {
        Response res = new Response();
        try {
            List<Essay> list = essayDAO.getEssayListByUserId(userId, limit, offset);
            if (list.isEmpty() || list.size() == 0) {
                res.setMessage(Message.NO_MORE_SINGLE_SELECT);
                return res;
            }
            Integer total = essayDAO.countEssay(userId);
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
    public Response updateEssay(Integer id, Integer userId, String content, String answer) {
        Response res = new Response();
        try {
            Essay essay = essayDAO.getEssay(id);
            Integer user = essay.getUserId();
            if (user != userId) {
                res.setMessage(Message.FAILURE);
                return res;
            }
            essay.setContent(content);
            essay.setAnswer(answer);
            Integer success = essayDAO.updateEssay(essay);
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
}
