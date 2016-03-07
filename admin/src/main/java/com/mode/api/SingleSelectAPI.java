package com.mode.api;

import com.mode.config.Response;
import com.mode.service.SingleSelectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2016/3/7.
 */
@RestController
@RequestMapping("/v2/singleselect/")
public class SingleSelectAPI {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SingleSelectService singleSelectService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Response createSingleSelect(@RequestHeader(value = "user_id") Integer userId,
                                       @RequestHeader(value = "content") String content,
                                       @RequestHeader(value = "a") String a,
                                       @RequestHeader(value = "b") String b,
                                       @RequestHeader(value = "c") String c,
                                       @RequestHeader(value = "d") String d,
                                       @RequestHeader(value = "answer") String answer) {
        Response res = singleSelectService.createSingleSelect(userId, content, a, b, c, d, answer);
        logger.info("v2/singleselect/create,{}",res.getMessage());
        return res;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Response deleteSingleSelect(@RequestHeader(value = "id") Integer id) {
        Response res = singleSelectService.deleteSingleSelect(id);
        logger.info("v2/singleselect/delete/{id},{}",res.getMessage());
        return res;
    }

    @RequestMapping(value = "/fetch/{id}", method = RequestMethod.GET)
    public Response fetchSingleSelect(@PathVariable Integer id) {
        Response res = singleSelectService.fetchSingleSelect(id);
        logger.info("v2/singleselect/fetch/{id},{}",res.getMessage());
        return res;
    }

    @RequestMapping(value = "/fetch/list", method = RequestMethod.GET)
    public Response getSingleSelectList(@RequestHeader(value = "userId") Integer userId,
                                        @RequestHeader(value = "limit", required = false) Integer limit,
                                        @RequestHeader(value = "offset", required = false) Integer offset) {
        Response res = singleSelectService.getSingleSelectList(userId, limit, offset);
        logger.info("v2/singleselect/fetch/{id},{}",res.getMessage());
        return res;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public Response updateSingleSelect(@PathVariable Integer id,
                                       @RequestHeader(value = "userId") Integer userId,
                                       @RequestHeader(value = "content", required = false) String content,
                                       @RequestHeader(value = "a", required = false) String a,
                                       @RequestHeader(value = "b", required = false) String b,
                                       @RequestHeader(value = "c", required = false) String c,
                                       @RequestHeader(value = "d", required = false) String d,
                                       @RequestHeader(value = "answer", required = false) String answer) {
        Response res = singleSelectService.updateSingleSelect(id, userId, content, a, b, c, d, answer);
        logger.info("v2/singleselect/update/{id},{}",res.getMessage());
        return res;
    }
}
