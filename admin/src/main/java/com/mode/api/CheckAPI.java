package com.mode.api;

import com.mode.config.Response;
import com.mode.service.CheckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2016/3/10.
 */
@RestController
@RequestMapping("/check")
public class CheckAPI {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CheckService checkService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Response createCheck(@RequestHeader(value = "user_id") Integer userId,
                                       @RequestHeader(value = "content") String content,
                                       @RequestHeader(value = "answer") Integer answer) {
        Response res = checkService.createCheck(userId, content, answer);
        logger.info("v2/create,{}",res.getMessage());
        return res;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public Response deleteCheck(@PathVariable Integer id) {
        Response res = checkService.deleteCheck(id);
        logger.info("v2/singleselect/create,{}",res.getMessage());
        return res;
    }

    @RequestMapping(value = "/fetch/{id}", method = RequestMethod.GET)
    public Response getCheck(@PathVariable Integer id) {
        Response res = checkService.getCheck(id);
        logger.info("v2/fetch/{id},{}",res.getMessage());
        return res;
    }

    @RequestMapping(value = "/fetch/list", method = RequestMethod.GET)
    public Response getCheckList(@RequestHeader(value = "userId") Integer userId,
                                 @RequestHeader(value = "limit", required = false) Integer limit,
                                 @RequestHeader(value = "offset", required = false) Integer offset) {
        Response res = checkService.getCheckList(userId, limit, offset);
        logger.info("v2/fetch/{id},{}",res.getMessage());
        return res;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public Response updateCheck(@PathVariable Integer id,
                                       @RequestHeader(value = "userId") Integer userId,
                                       @RequestHeader(value = "content", required = false) String content,
                                       @RequestHeader(value = "answer", required = false) Integer answer) {
        Response res = checkService.updateCheck(id, userId, content, answer);
        logger.info("v2/check/update/{id},{}",res.getMessage());
        return res;
    }
}
