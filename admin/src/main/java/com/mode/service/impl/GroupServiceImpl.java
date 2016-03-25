package com.mode.service.impl;

import com.mode.config.Message;
import com.mode.config.Response;
import com.mode.dao.CheckDAO;
import com.mode.dao.CompletionDAO;
import com.mode.dao.EssayDAO;
import com.mode.dao.MultiSelectDAO;
import com.mode.dao.ShortAnswerDAO;
import com.mode.dao.SingleSelectDAO;
import com.mode.entity.Check;
import com.mode.entity.Completion;
import com.mode.entity.Essay;
import com.mode.entity.MultiSelect;
import com.mode.entity.ShortAnswer;
import com.mode.entity.SingleSelect;
import com.mode.service.GroupService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kang on 2016/3/25.
 */
@Service
public class GroupServiceImpl implements GroupService{

    @Autowired
    private CheckDAO checkDAO;

    @Autowired
    private CompletionDAO completionDAO;

    @Autowired
    private SingleSelectDAO singleSelectDAO;

    @Autowired
    private MultiSelectDAO multiSelectDAO;

    @Autowired
    private ShortAnswerDAO shortAnswerDAO;

    @Autowired
    private EssayDAO essayDAO;

    @Override
    public Response createGroup(Integer userId, Integer checkCount,Integer completionCount,
                                Integer singleSelectCount, Integer multiSelectCount, Integer
                                        shortAnswerCount, Integer essayCount) {
        Response res = new Response();
        try {
            List<Completion> completionList = completionDAO.getCompletionListByUserId(userId,
                    null, null);

            List<Check> checkList = checkDAO.getCheckListByUserId(userId, null, null);

            List<SingleSelect> singleSelectList = singleSelectDAO.getSingleSelectListByUserId
                    (userId, null, null);

            List<MultiSelect> multiSelectList = multiSelectDAO.getMultiSelectListByUserId(userId,
                    null, null);

            List<ShortAnswer> shortAnswerList = shortAnswerDAO.getShortAnswerListByUserId(userId,
                    null, null);

            List<Essay> essayList = essayDAO.getEssayListByUserId(userId, null, null);

            Map<String, Object> payload = new HashMap<>();
            payload.put("completionList",completionList);
            payload.put("checkList",checkList);
            payload.put("singleSelectList",singleSelectList);
            payload.put("multiSelectList",multiSelectList);
            payload.put("shortAnswerList",shortAnswerList);
            payload.put("essayList",essayList);
            res.setPayload(payload);
            res.setMessage(Message.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.CATCH);
        }
        return res;
    }
}
