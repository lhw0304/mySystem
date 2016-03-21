package com.mode.api;

import com.mode.config.Response;
import com.mode.service.EssayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2016/3/17.
 */
@RestController
@RequestMapping("/essay")
public class EssayAPI {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EssayService essayService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Response createEssay(@RequestHeader(value = "user_id") Integer userId,
                                @RequestHeader(value = "content") String content,
                                @RequestHeader(value = "answer") String answer) {
        Response res = essayService.createEssay(userId, content, answer);
        logger.info("/v2/essay/create,{}",res.getMessage());
        return res;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public Response deleteEssay(@PathVariable Integer id) {
        Response res = essayService.deleteEssay(id);
        logger.info("v2/essay/delete,{}",res.getMessage());
        return res;
    }

    @RequestMapping(value = "/fetch/{id}", method = RequestMethod.GET)
    public Response getEssay(@PathVariable Integer id) {
        Response res = essayService.getEssay(id);
        logger.info("/v2/essay/fetch/{id},{}",res.getMessage());
        return res;
    }

    @RequestMapping(value = "/fetch/list", method = RequestMethod.GET)
    public Response getEssayList(@RequestHeader(value = "userId") Integer userId,
                                 @RequestHeader(value = "limit", required = false) Integer limit,
                                 @RequestHeader(value = "offset", required = false) Integer offset) {
        Response res = essayService.getEssayList(userId, limit, offset);
        logger.info("v2/fetch/list,{}",res.getMessage());
        return res;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public Response updateEssay(@PathVariable Integer id,
                                @RequestHeader(value = "userId") Integer userId,
                                @RequestHeader(value = "content", required = false) String content,
                                @RequestHeader(value = "answer", required = false) String answer) {
        Response res = essayService.updateEssay(id, userId, content, answer);
        logger.info("v2/check/update/{id},{}",res.getMessage());
        return res;
    }
}
