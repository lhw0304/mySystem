package com.mode.service.impl;

import com.mode.config.Message;
import com.mode.config.Response;
import com.mode.dao.MultiSelectDAO;
import com.mode.entity.MultiSelect;
import com.mode.service.MultiSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/17.
 */
@Service
public class MultiSelectServiceImpl implements MultiSelectService{

    @Autowired
    private MultiSelectDAO multiSelectDAO;

    @Override
    public Response createMultiSelect(Integer userId, String content, String a, String b, String c, String d, String answer) {
        Response res = new Response();
        try {
            MultiSelect multiSelect = new MultiSelect();
            multiSelect.setUserId(userId);
            multiSelect.setContent(content);
            multiSelect.setA(a);
            multiSelect.setA(b);
            multiSelect.setA(c);
            multiSelect.setA(d);
            multiSelect.setAnswer(answer);
            final long now = System.currentTimeMillis();
            multiSelect.setCtime(now);
            Integer success = multiSelectDAO.createMultiSelect(multiSelect);
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
    public Response deleteMultiSelect(Integer id) {
        Response res = new Response();
        try {
            Integer success = multiSelectDAO.deleteMultiSelect(id);
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
    public Response fetchMultiSelect(Integer id) {
        Response res = new Response();
        try {
            MultiSelect multiSelect = multiSelectDAO.getMultiSelect(id);
            if (multiSelect == null) {
                res.setMessage(Message.DATABASE);
                return res;
            }
            Map<String, Object> payload = new HashMap<>();
            payload.put("multiSelect",multiSelect);
            res.setPayload(payload);
            res.setMessage(Message.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.CATCH);
        }
        return res;
    }

    @Override
    public Response getMultiSelectList(Integer userId, Integer limit, Integer offset) {
        Response res = new Response();
        try {
            List<MultiSelect> list = multiSelectDAO.getMultiSelectListByUserId(userId, limit, offset);
            if (list.isEmpty() || list.size() == 0) {
                res.setMessage(Message.NO_MORE_SINGLE_SELECT);
                return res;
            }
            Integer total = multiSelectDAO.countMultiSelect(userId);
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

    public Response updateMultiSelect(Integer id, Integer userId, String content, String a, String b,
                                      String c, String d, String answer) {
        Response res = new Response();
        try {
            MultiSelect multiSelect = multiSelectDAO.getMultiSelect(id);
            Integer user = multiSelect.getUserId();
            if (user != userId) {
                res.setMessage(Message.SUCCESS);
                return res;
            }
            multiSelect.setContent(content);
            multiSelect.setA(a);
            multiSelect.setA(b);
            multiSelect.setA(c);
            multiSelect.setA(d);
            multiSelect.setAnswer(answer);
            Integer success = multiSelectDAO.updateMultiSelect(multiSelect);
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
}
