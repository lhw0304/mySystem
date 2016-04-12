package com.mode.service;

import com.mode.config.Response;
import com.mode.entity.StylistJollyOrder;

/**
 * Created by Lei on 3/30/16.
 */
public interface StylistOrderService {

    /**
     * List the stylist's orders
     *
     * @param stylistId
     * @param limit
     * @param offset
     * @return
     */
    public Response listStylistOrders(Integer stylistId, Integer limit, Integer offset);

    /**
     * Create a new order of the stylist
     *
     * @param stylistOrder
     * @return
     */
    public Response createStylistOrder(StylistJollyOrder stylistOrder);
}
