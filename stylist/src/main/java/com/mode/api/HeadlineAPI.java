package com.mode.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mode.config.Response;
import com.mode.service.HeadlineService;

/**
 * Created by zhaoweiwei on 15/11/19.
 */
@RestController
@RequestMapping("/v2/headlines")
public class HeadlineAPI {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HeadlineService headlineService;

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public Response updateHeadlineStatus(@PathVariable Integer id,
                                         @RequestParam(value = "status", required = false) Integer
                                                 status,
                                         @RequestParam(value = "userId",required = false) Integer
                                         userId) {
        Response res = headlineService.updateHeadlineStatus(id, status, userId);
        logger.info("/v2/headlines/{}?status={}, {}", id, status, res.getMessage());
        return res;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Response listHeadlines(@RequestParam(value = "stylistId") Integer stylistId,
                                  @RequestParam(value = "limit", required = false)
                                  Integer limit,
                                  @RequestParam(value = "offset", required = false)
                                      Integer offset) {
        Response res = headlineService.listHeadlines(stylistId, limit, offset);
        logger.info("/v2/headlines?stylistId={}&limit={}&offset={}, {}", stylistId, limit,
                offset, res.getMessage());
        return res;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Response getHeadline(@PathVariable Integer id) {
        Response res = headlineService.getHeadline(id);
        logger.info("/v2/headlines/{}, {}", id, res.getMessage());
        return res;
    }
}
