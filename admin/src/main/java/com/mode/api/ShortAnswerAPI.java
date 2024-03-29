package com.mode.api;

import com.mode.config.Response;
import com.mode.service.ShortAnswerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by �ľ� on 2016/3/11.
 */
@RestController
@RequestMapping("/short")
public class ShortAnswerAPI {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ShortAnswerService shortAnswerService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Response createShortAnswer(@RequestHeader(value = "user_id") Integer userId,
                                @RequestHeader(value = "content") String content,
                                @RequestHeader(value = "answer") String answer) {
        Response res = shortAnswerService.createShortAnswer(userId, content, answer);
        logger.info("v2/create,{}",res.getMessage());
        return res;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public Response deleteShortAnswer(@PathVariable Integer id) {
        Response res = shortAnswerService.deleteShortAnswer(id);
        logger.info("v2/singleselect/create,{}",res.getMessage());
        return res;
    }

    @RequestMapping(value = "/fetch/{id}", method = RequestMethod.GET)
    public Response getShortAnswer(@PathVariable Integer id) {
        Response res = shortAnswerService.getShortAnswer(id);
        logger.info("v2/fetch/{id},{}",res.getMessage());
        return res;
    }

    @RequestMapping(value = "/fetch/list", method = RequestMethod.GET)
    public Response getShortAnswerList(@RequestHeader(value = "userId") Integer userId,
                                 @RequestHeader(value = "limit", required = false) Integer limit,
                                 @RequestHeader(value = "offset", required = false) Integer offset) {
        Response res = shortAnswerService.getShortAnswerList(userId, limit, offset);
        logger.info("v2/fetch/{id},{}",res.getMessage());
        return res;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public Response updateShortAnswer(@PathVariable Integer id,
                                @RequestHeader(value = "userId") Integer userId,
                                @RequestHeader(value = "content", required = false) String content,
                                @RequestHeader(value = "answer", required = false) String answer) {
        Response res = shortAnswerService.updateShortAnswer(id, userId, content, answer);
        logger.info("v2/check/update/{id},{}",res.getMessage());
        return res;
    }
}
