package com.mode.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mode.config.Response;
import com.mode.service.ItemService;

/**
 * Created by Lei on 12/16/15.
 */
@RestController
@RequestMapping("/v2/items")
public class ItemAPI {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ItemService itemService;

    @CrossOrigin(methods = RequestMethod.GET)
    @RequestMapping(value="/{itemId}", method = RequestMethod.GET)
    public Response getItem(@PathVariable("itemId") Integer itemId){
        Response res = itemService.getItem(itemId);
        logger.info("/v2/items/{}, {}", itemId, res.getMessage());
        return res;
    }
}
