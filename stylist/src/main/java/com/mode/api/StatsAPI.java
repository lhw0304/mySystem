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
import com.mode.service.StatsService;

/**
 * Created by zhaoweiwei on 15/11/21.
 */
@RestController
@RequestMapping("/v2/stats")
public class StatsAPI {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StatsService statsService;

    @RequestMapping(value = "/listStylistArticleStats", method = RequestMethod.GET)
    public Response listStylistArticleStats(@RequestParam(value = "stylistId") Integer stylistId,
                                            @RequestParam(value = "limit", required = false)
                                            Integer limit,
                                            @RequestParam(value = "offset", required = false)
                                            Integer offset) {
        Response res = statsService.listStylistArticleStats(stylistId, limit, offset);
        logger.info("/v2/listStylistArticleStats?stylistId={}&limit={}&offset={}, {}", stylistId,
                limit, offset, res.getMessage());
        return res;
    }

    @RequestMapping(value = "/listStylistCommissionStats", method = RequestMethod.GET)
    public Response listStylistCommissionStats(@RequestParam(value = "stylistId")
                                               Integer stylistId,
                                               @RequestParam(value = "limit", required = false)
                                               Integer limit,
                                               @RequestParam(value = "offset", required = false)
                                               Integer offset) {
        Response res = statsService.listStylistCommissionStats(stylistId, limit, offset);
        logger.info("/v2/listStylistCommissionStats?stylistId={}&limit={}&offset={}, {}", stylistId,
                limit, offset, res.getMessage());
        return res;
    }

    @RequestMapping(value = "/listStylistArticleStatsByDate", method = RequestMethod.GET)
    public Response listStylistArticleStatsByDate(@RequestParam(value = "articleId")
                                                  Integer articleId,
                                                  @RequestParam(value = "limit", required = false)
                                                  Integer limit) {
        Response res = statsService.listStylistArticleStatsByDate(articleId, limit);
        logger.info("/v2/listStylistArticleStatsByDate?articleId={}&limit={}, {}", articleId,
                limit, res.getMessage());
        return res;
    }

    @RequestMapping(value = "/listConfigs/{userId}", method = RequestMethod.GET)
    public Response listConfig(@PathVariable("userId") Integer userId) {
        Response res = statsService.listConfig(userId);
        logger.info("/v2/stats/listConfigs/{}, {}", userId, res.getMessage());
        return res;
    }

//    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    public void test() {
//        statsService.stylistCommissionStats();
//        logger.info("stats ends");
//    }
}
