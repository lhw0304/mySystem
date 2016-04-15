package com.mode.api;

import com.alibaba.druid.filter.AutoLoad;
import com.mode.config.Response;
import com.mode.service.MultiSelectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * Created by Administrator on 2016/3/17.
 */
@RestController
@RequestMapping("/system/multi")
public class MultiSelectAPI {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MultiSelectService multiSelectService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Response createMultiSelect(@RequestHeader(value = "userId") Integer userId,
                                      MultipartHttpServletRequest mRequest) {
        Response res = multiSelectService.createMultiSelect(userId, mRequest);
        logger.info("v2/singleselect/create,{}",res.getMessage());
        return res;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public Response deleteMultiSelect(@PathVariable Integer id) {
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
                                      MultipartHttpServletRequest mRequest) {
        Response res = multiSelectService.updateMultiSelect(id, userId, mRequest);
        logger.info("v2/singleselect/update/{id},{}",res.getMessage());
        return res;
    }
}
