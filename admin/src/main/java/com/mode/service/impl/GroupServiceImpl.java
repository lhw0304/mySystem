package com.mode.service.impl;

import com.mode.config.Message;
import com.mode.config.Response;
import com.mode.dao.CheckDAO;
import com.mode.dao.CompletionDAO;
import com.mode.dao.MultiSelectDAO;
import com.mode.dao.ShortAnswerDAO;
import com.mode.dao.SingleSelectDAO;
import com.mode.entity.Check;
import com.mode.entity.Completion;
import com.mode.entity.MultiSelect;
import com.mode.entity.ShortAnswer;
import com.mode.entity.SingleSelect;
import com.mode.service.GroupService;

import java.util.ArrayList;
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


    @Override
    public Response createGroup(Integer userId, Integer checkCount,Integer completionCount,
                                Integer singleSelectCount, Integer multiSelectCount, Integer
                                        shortAnswerCount) {
        Response res = new Response();
        // check list
        List<Check> checkList = new ArrayList<>();
        List<Integer> checkIds = new ArrayList<>();
        Integer checkKnowledgeCount = checkDAO.countCheckKnowledge(userId);
        Integer completionKnowledgeCount = completionDAO.countCompletionKnowledge(userId);
        Integer singleKnowledgeCount = singleSelectDAO.countSingleKnowledge(userId);
        Integer multiKnowledgeCount = multiSelectDAO.countMultiKnowledge(userId);
        if (checkKnowledgeCount >= checkCount) {
            checkIds = checkDAO.getCheckIdList(userId, checkCount);
            String stringCheckIds = checkIds.toString();
            stringCheckIds = stringCheckIds.substring(1, stringCheckIds.length()-1);
            checkList = checkDAO.getGroupList(stringCheckIds);
        } else {
            checkIds = checkDAO.getCheckIdList2(userId, checkCount*2, checkCount);
            String stringCheckIds = checkIds.toString();
            stringCheckIds = stringCheckIds.substring(1, stringCheckIds.length()-1);
            checkList = checkDAO.getGroupList(stringCheckIds);
        }
        //completion list
        List<Completion> completionList = new ArrayList<>();
        List<Integer> completionIds = new ArrayList<>();
        if (completionKnowledgeCount >= completionCount) {
            completionIds = completionDAO.getCompletionIdList(userId, completionCount);
            String stringCompletionIds = completionIds.toString();
            stringCompletionIds = stringCompletionIds.substring(1, stringCompletionIds.length()-1);
            completionList = completionDAO.getGroupList(stringCompletionIds);
        } else {
            completionIds = completionDAO.getCompletionIdList2(userId, completionCount*2, completionCount);
            String stringCompletionIds = completionIds.toString();
            stringCompletionIds = stringCompletionIds.substring(1, stringCompletionIds.length()-1);
            completionList = completionDAO.getGroupList(stringCompletionIds);
        }
        // single select
        List<SingleSelect> singleSelectList = new ArrayList<>();
        List<Integer> singleSelectIds = new ArrayList<>();
        if (singleKnowledgeCount >= singleSelectCount) {
            singleSelectIds = singleSelectDAO.getSingleSelectIdList(userId, singleSelectCount);
            String stringSingleIds = singleSelectIds.toString();
            stringSingleIds = stringSingleIds.substring(1, stringSingleIds.length()-1);
            singleSelectList = singleSelectDAO.getGroupList(stringSingleIds);
        } else {
            singleSelectIds = singleSelectDAO.getSingleSelectIdList2(userId, singleSelectCount*2, singleSelectCount);
            String stringSingleIds = singleSelectIds.toString();
            stringSingleIds = stringSingleIds.substring(1, stringSingleIds.length()-1);
            singleSelectList = singleSelectDAO.getGroupList(stringSingleIds);
        }
        // multi select
        List<MultiSelect> multiSelectList = new ArrayList<>();
        List<Integer> multiSelectIds = new ArrayList<>();
        if (multiKnowledgeCount >= multiSelectCount) {
            multiSelectIds = multiSelectDAO.getMultiSelectIdList(userId, multiSelectCount);
            String stringMultiIds = multiSelectIds.toString();
            stringMultiIds = stringMultiIds.substring(1, stringMultiIds.length()-1);
            multiSelectList = multiSelectDAO.getGroupList(stringMultiIds);
        } else {
            multiSelectIds = multiSelectDAO.getMultiSelectIdList2(userId, multiSelectCount*2, multiSelectCount);
            String stringMultiIds = multiSelectIds.toString();
            stringMultiIds = stringMultiIds.substring(1, stringMultiIds.length()-1);
            multiSelectList = multiSelectDAO.getGroupList(stringMultiIds);
        }

        List<ShortAnswer> shortAnswerList = shortAnswerDAO.getGroupList(userId, shortAnswerCount);

        Map<String, Object> payload = new HashMap<>();
        payload.put("checkList",checkList);
        payload.put("completionList",completionList);
        payload.put("singleSelectList",singleSelectList);
        payload.put("multiSelectList",multiSelectList);
        payload.put("shortAnswerList",shortAnswerList);
        res.setPayload(payload);
        res.setMessage(Message.SUCCESS);

        return res;
    }
}
