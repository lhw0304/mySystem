package com.mode.api;

import com.mode.config.Response;
import com.mode.service.KnowledgeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * Created by Administrator on 2016/4/21.
 */
@RestController
@RequestMapping("/system/knowledge")
public class KnowledgeAPI {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private KnowledgeService knowledgeService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Response createKnowledge(@RequestHeader(value = "userId") Integer userId,
                                    @RequestBody String knowledge) {
        Response res = knowledgeService.createKnowledge(userId, knowledge);
        logger.info("v2/create,{}",res.getMessage());
        return res;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public Response deleteKnowledge(@PathVariable Integer id) {
        Response res = knowledgeService.deleteKnowledge(id);
        logger.info("v2/create,{}",res.getMessage());
        return res;
    }

    @RequestMapping(value = "/fetch/{id}", method = RequestMethod.GET)
    public Response getKnowledge(@PathVariable Integer id) {
        Response res = knowledgeService.getKnowledge(id);
        logger.info("v2/create,{}",res.getMessage());
        return res;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Response getKnowledgeList(@RequestHeader(value = "userId", required = false) Integer userId,
                                 @RequestHeader(value = "limit", required = false) Integer limit,
                                 @RequestHeader(value = "offset", required = false) Integer offset) {
        Response res = knowledgeService.getKnowledgeList(userId, limit, offset);
        logger.info("v2/fetch/{id},{}",res.getMessage());
        return res;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public Response updateKnowledge(@PathVariable Integer id,
                                     @RequestBody String knowledge) {
        Response res = knowledgeService.updateKnowledge(id, knowledge);
        logger.info("v2/fetch/{id},{}",res.getMessage());
        return res;
    }
}
