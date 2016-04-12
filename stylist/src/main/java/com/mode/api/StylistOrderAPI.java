package com.mode.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mode.config.Response;
import com.mode.entity.StylistJollyOrder;
import com.mode.service.StylistOrderService;

/**
 * Created by Lei on 3/30/16.
 */
@RestController
@RequestMapping("/v2/stylistOrders")
public class StylistOrderAPI {

    @Autowired
    private StylistOrderService stylistOrderService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Response listStylistOrders(@RequestParam("stylistId") Integer stylistId,
                                      @RequestParam(value = "limit", required = false) Integer
                                              limit,
                                      @RequestParam(value = "offset", required = false) Integer
                                              offset) {
        Response res = stylistOrderService.listStylistOrders(stylistId, limit, offset);
        return res;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Response createStylistOrder(@RequestBody StylistJollyOrder stylistOrder) {
        Response res = stylistOrderService.createStylistOrder(stylistOrder);
        return res;
    }
}
