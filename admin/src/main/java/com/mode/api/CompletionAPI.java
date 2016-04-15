package com.mode.api;

import com.mode.config.Response;
import com.mode.service.CompletionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * Created by kang on 2016/3/11.
 */
@RestController
@RequestMapping("/system/completion")
public class CompletionAPI {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CompletionService completionService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Response createCompletion(@RequestHeader(value = "userId") Integer userId,
                                     MultipartHttpServletRequest mRequest) {
        Response res = completionService.createCompletion(userId, mRequest);
        logger.info("v2/create,{}",res.getMessage());
        return res;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public Response deleteCompletion(@PathVariable Integer id) {
        Response res = completionService.deleteCompletion(id);
        logger.info("v2/delete,{}",res.getMessage());
        return res;
    }

    @RequestMapping(value = "/fetch/{id}", method = RequestMethod.GET)
    public Response getCompletion(@PathVariable Integer id) {
        Response res = completionService.getCompletion(id);
        logger.info("v2/fetch,{}",res.getMessage());
        return res;
    }

    @RequestMapping(value = "/fetch/list", method = RequestMethod.GET)
    public Response getCompletionList(@RequestHeader(value = "userId") Integer userId,
                                  @RequestHeader(value = "limit", required = false) Integer limit,
                                  @RequestHeader(value = "offset", required = false) Integer offset) {
        Response res = completionService.getCompletionList(userId, limit, offset);
        logger.info("v2/fetch/lits,{}",res.getMessage());
        return res;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public Response updateCompletion(@PathVariable Integer id,
                                    @RequestHeader(value = "userId") Integer userId,
                                     MultipartHttpServletRequest mRequest) {
        Response res = completionService.updateCompletion(id, userId, mRequest);
        logger.info("v2/update,{}",res.getMessage());
        return res;
    }
}
