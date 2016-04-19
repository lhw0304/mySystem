package com.mode.service.impl;

import com.mode.config.Message;
import com.mode.config.Response;
import com.mode.dao.CompletionDAO;
import com.mode.entity.Completion;
import com.mode.service.CompletionService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * Created by kang on 2016/3/11.
 */
@Service
public class CompletionServiceImpl implements CompletionService{

    @Autowired
    private CompletionDAO completionDAO;

    @Override
    public Response createCompletion(Integer userId, MultipartHttpServletRequest mRequest) {
        Response res = new Response();
//        try {
            Completion completion = new Completion();
            completion.setUserId(userId);
            String content = mRequest.getParameter("content");
            String answer = mRequest.getParameter("answer");
            completion.setContent(content);
            completion.setAnswer(answer);
            long now = System.currentTimeMillis();
            completion.setCtime(now);
            Integer success = completionDAO.createCompletion(completion);
            if(success == 0) {
                res.setMessage(Message.DATABASE);
            }
            res.setMessage(Message.SUCCESS);
//        } catch (Exception e) {
//            e.printStackTrace();
//            res.setMessage(Message.CATCH);
//        }
        return res;
    }

    @Override
    public Response deleteCompletion(Integer id) {
        Response res = new Response();
        try {
            Integer success = completionDAO.deleteCompletion(id);
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

    @Override
    public Response getCompletion(Integer id) {
        Response res = new Response();
        try {
            Completion completion = completionDAO.getCompletion(id);
            if (completion == null) {
                res.setMessage(Message.DATABASE);
            }
            Map<String, Object> payload = new HashMap<>();
            payload.put("completion",completion);
            res.setPayload(payload);
            res.setMessage(Message.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.CATCH);
        }
        return res;
    }

    @Override
    public Response getCompletionList(Integer userId, Integer limit, Integer offset) {
        Response res = new Response();
//        try {
            List<Completion> list = completionDAO.getCompletionListByUserId(userId, limit, offset);
            if(list.isEmpty()) {
                res.setMessage(Message.NO_MORE_COMPLETION);
            }
            Integer total = completionDAO.countCompletion(userId);
            Map<String, Object> payload = new HashMap<>();
            payload.put("list",list);
            payload.put("total",total);
            res.setPayload(payload);
            res.setMessage(Message.SUCCESS);
//        } catch (Exception e) {
//            e.printStackTrace();
//            res.setMessage(Message.CATCH);
//        }
        return res;
    }

    @Override
    public Response updateCompletion(Integer id, Integer userId, MultipartHttpServletRequest mRequest) {
        Response res = new Response();
        try {
            Completion completion = completionDAO.getCompletion(id);
            Integer user = completion.getUserId();
            if (user != userId) {
                res.setMessage(Message.ACCOUNT_NOT_ACTIVATED);
                return res;
            }
            String content = mRequest.getParameter("content");
            String answer = mRequest.getParameter("answer");
            completion.setContent(content);
            completion.setAnswer(answer);
            Integer success = completionDAO.updateCompletion(completion);
            if(success == 0) {
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
