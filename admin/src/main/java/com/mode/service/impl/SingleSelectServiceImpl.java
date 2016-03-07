package com.mode.service.impl;

import com.mode.config.Message;
import com.mode.config.Response;
import com.mode.dao.SingleSelectDAO;
import com.mode.entity.SingleSelect;
import com.mode.service.SingleSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/7.
 */
@Service
public class SingleSelectServiceImpl implements SingleSelectService{

    @Autowired
    private SingleSelectDAO singleSelectDAO;

    @Override
    public Response createSingleSelect(Integer userId, String content, String a, String b, String c, String d, String answer) {
        Response res = new Response();
        try{
            SingleSelect singleSelect = new SingleSelect();
            singleSelect.setUserId(userId);
            singleSelect.setContent(content);
            singleSelect.setA(a);
            singleSelect.setB(b);
            singleSelect.setC(c);
            singleSelect.setD(d);
            singleSelect.setAnswer(answer);
            long now = System.currentTimeMillis();
            singleSelect.setCtime(now);
            Integer success = singleSelectDAO.createSingleSelect(singleSelect);
            if(success == 0 || success == null) {
                res.setMessage(Message.FAILURE);
                return res;
            }
            res.setMessage(Message.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.FAILURE);
            return res;
        }
        return res;
    }

    @Override
    public Response fetchSingleSelect(Integer id) {
        Response res = new Response();
        try {
            SingleSelect singleSelect = singleSelectDAO.getSingleSelect(id);
            if (singleSelect == null) {
                res.setMessage(Message.NO_MORE_SINGLE_SELECT);
                return res;
            }
            Map<String,Object> payload = new HashMap<>();
            payload.put("singleSelect",singleSelect);
            res.setMessage(Message.SUCCESS);
            res.setPayload(payload);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.FAILURE);
            return res;
        }
        return res;
    }

    @Override
    public Response deleteSingleSelect(Integer id) {
        Response res = new Response();
        try{
            Integer success = singleSelectDAO.deleteSingleSelect(id);
            if(success == null || success == 0) {
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
    public Response getSingleSelectList(Integer userId, Integer limit, Integer offset) {
        Response res = new Response();
        try{
            List<SingleSelect> list = singleSelectDAO.getSingleSelectListByUserId(userId, limit, offset);
            if (list.isEmpty()) {
                res.setMessage(Message.NO_MORE_SINGLE_SELECT);
                return res;
            }
            Integer total = singleSelectDAO.countSingleSelect(userId);
            Map<String, Object> payload = new HashMap<>();
            payload.put("list",list);
            payload.put("total",total);
            res.setMessage(Message.SUCCESS);
            res.setPayload(payload);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.FAILURE);
        }
        return res;
    }

    @Override
    public Response updateSingleSelect(Integer id, Integer userId, String content, String a, String b, String c, String d, String answer) {
        Response res = new Response();
        try{
            SingleSelect singleSelect = new SingleSelect();
            singleSelect.setId(id);
            singleSelect.setUserId(userId);
            singleSelect.setContent(content);
            singleSelect.setA(a);
            singleSelect.setB(b);
            singleSelect.setC(c);
            singleSelect.setD(d);
            singleSelect.setAnswer(answer);
            long now = System.currentTimeMillis();
            singleSelect.setCtime(now);
            Integer success = singleSelectDAO.updateSingleSelect(singleSelect);
            if (success == 0 || success == null) {
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
