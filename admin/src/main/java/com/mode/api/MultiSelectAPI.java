package com.mode.api;

import com.alibaba.druid.filter.AutoLoad;
import com.mode.config.Response;
import com.mode.service.MultiSelectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2016/3/17.
 */
@RestController
@RequestMapping("/multiselect")
public class MultiSelectAPI {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MultiSelectService multiSelectService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Response createMultiSelect(@RequestHeader(value = "user_id") Integer userId,
                                       @RequestHeader(value = "content") String content,
                                       @RequestHeader(value = "a") String a,
                                       @RequestHeader(value = "b") String b,
                                       @RequestHeader(value = "c") String c,
                                       @RequestHeader(value = "d") String d,
                                       @RequestHeader(value = "answer") String answer) {
        Response res = multiSelectService.createMultiSelect(userId, content, a, b, c, d, answer);
        logger.info("v2/singleselect/create,{}",res.getMessage());
        return res;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public Response deleteMultiSelect(@RequestHeader(value = "id") Integer id) {
        Response res = multiSelectService.deleteMultiSelect(id);
        logger.info("v2/multiselect/delete/{id},{}",res.getMessage());
        return res;
    }

    @RequestMapping(value = "/fetch/{id}", method = RequestMethod.GET)
    public Response fetchMultiSelect(@PathVariable Integer id) {
        Response res = multiSelectService.fetchMultiSelect(id);
        logger.info("v2/singleselect/fetch/{id},{}",res.getMessage());
        return res;
    }

    @RequestMapping(value = "/fetch/list", method = RequestMethod.GET)
    public Response getMultiSelectList(@RequestHeader(value = "userId") Integer userId,
                                        @RequestHeader(value = "limit", required = false) Integer limit,
                                        @RequestHeader(value = "offset", required = false) Integer offset) {
        Response res = multiSelectService.getMultiSelectList(userId, limit, offset);
        logger.info("v2/singleselect/fetch/{id},{}",res.getMessage());
        return res;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public Response updateMultiSelect(@PathVariable Integer id,
                                       @RequestHeader(value = "userId") Integer userId,
                                       @RequestHeader(value = "content", required = false) String content,
                                       @RequestHeader(value = "a", required = false) String a,
                                       @RequestHeader(value = "b", required = false) String b,
                                       @RequestHeader(value = "c", required = false) String c,
                                       @RequestHeader(value = "d", required = false) String d,
                                       @RequestHeader(value = "answer", required = false) String answer) {
        Response res = multiSelectService.updateMultiSelect(id, userId, content, a, b, c, d, answer);
        logger.info("v2/singleselect/update/{id},{}",res.getMessage());
        return res;
    }
}
