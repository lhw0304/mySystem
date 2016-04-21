package com.mode.service.impl;

import com.mode.config.Message;
import com.mode.config.Response;
import com.mode.dao.KnowledgeDAO;
import com.mode.entity.Knowledge;
import com.mode.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/21.
 */
@Service
public class KnowledgeServiceImpl implements KnowledgeService {

    @Autowired
    private KnowledgeDAO knowledgeDAO;

    @Override
    public Response createKnowledge(Integer userId, String knowledge) {
        Response res = new Response();
        try {
            Knowledge knowledge1 = new Knowledge();
            knowledge1.setUserId(userId);
            knowledge1.setKnowledge(knowledge);
            final Long now = System.currentTimeMillis();
            knowledge1.setCtime(now);
            Integer success = knowledgeDAO.createCheck(knowledge1);
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
    public Response deleteKnowledge(Integer id) {
        Response res = new Response();
        try {
            Integer success = knowledgeDAO.deleteKnowledge(id);
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
    public Response getKnowledge(Integer id) {
        Response res = new Response();
        try {
            Knowledge knowledge = knowledgeDAO.getKnowledge(id);
            if (knowledge == null) {
                res.setMessage(Message.NO_MORE_SINGLE_SELECT);
                return res;
            }
            Map<String, Object> payload = new HashMap<>();
            payload.put("knowledge", knowledge);
            res.setMessage(Message.SUCCESS);
            res.setPayload(payload);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.CATCH);
        }
        return res;
    }

    @Override
    public Response getKnowledgeList(Integer userId, Integer limit, Integer offset) {
        Response res = new Response();
        try {
            List<Knowledge> list = knowledgeDAO.getKnowledgeListByUserId(userId, limit, offset);
            if (list == null || list.size() == 0) {
                res.setMessage(Message.NO_MORE_SINGLE_SELECT);
                return  res;
            }
            Integer total = knowledgeDAO.countKnowledge(userId);
            Map<String, Object> payload = new HashMap<>();
            payload.put("list", list);
            payload.put("total", total);
            res.setMessage(Message.SUCCESS);
            res.setPayload(payload);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.CATCH);
        }
        return res;
    }

    @Override
    public Response updateKnowledge(Integer id, String knowledge) {
        Response res = new Response();
        try {
            Knowledge knowledge1 = knowledgeDAO.getKnowledge(id);
            knowledge1.setKnowledge(knowledge);
            final Long now = System.currentTimeMillis();
            knowledge1.setCtime(now);
            Integer success = knowledgeDAO.updateKnowledge(knowledge1);
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
