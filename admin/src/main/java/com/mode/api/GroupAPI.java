package com.mode.api;

import com.mode.config.Response;
import com.mode.service.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kang on 2016/3/25.
 */
@RestController
@RequestMapping("/group")
public class GroupAPI {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Response createMultiSelect(@RequestHeader(value = "user_id") Integer userId,
                                      @RequestHeader(value = "checkCount") Integer checkCount,
                                      @RequestHeader(value = "completionCount") Integer
                                                  completionCount,
                                      @RequestHeader(value = "singleSelectCount") Integer
                                                  singleSelectCount,
                                      @RequestHeader(value = "multiSelectCount") Integer
                                                  multiSelectCount,
                                      @RequestHeader(value = "shortAnswerCount") Integer
                                                  shortAnswerCount,
                                      @RequestHeader(value = "essayCount") Integer essayCount) {
        Response res = groupService.createGroup(userId, checkCount, completionCount,
                singleSelectCount, multiSelectCount, shortAnswerCount,essayCount);
        logger.info("/v2/group/create,{}",res.getMessage());
        return res;
    }
}
