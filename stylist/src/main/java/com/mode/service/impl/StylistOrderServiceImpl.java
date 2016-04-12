package com.mode.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mode.config.AppConfig;
import com.mode.config.Message;
import com.mode.config.Response;
import com.mode.config.Status;
import com.mode.dao.JollyItemDAO;
import com.mode.dao.ProfileAddressDAO;
import com.mode.dao.StylistJollyItemDAO;
import com.mode.dao.StylistJollyOrderDAO;
import com.mode.entity.JollyItem;
import com.mode.entity.ProfileAddress;
import com.mode.entity.StylistJollyOrder;
import com.mode.service.StylistOrderService;

/**
 * Created by Lei on 3/30/16.
 */
@Service
public class StylistOrderServiceImpl implements StylistOrderService {

    @Autowired
    private StylistJollyOrderDAO stylistOrderDAO;

    @Autowired
    private ProfileAddressDAO profileAddressDAO;

    @Autowired
    private JollyItemDAO cooperationItemDAO;

    @Autowired
    private StylistJollyItemDAO stylistJollyItemDAO;

    /**
     * List the stylist's orders
     *
     * @param stylistId
     * @param limit
     * @param offset
     * @return
     */
    @Override
    public Response listStylistOrders(Integer stylistId, Integer limit, Integer offset) {
        Response res = new Response();

        limit = (limit == null) ? AppConfig.DEFAULT_LIMIT : limit;
        offset = (offset == null) ? AppConfig.DEFAULT_OFFSET : offset;

        List<StylistJollyOrder> stylistOrders = stylistOrderDAO.listStylistOrders(stylistId, limit,
                offset);
        Integer total = stylistOrderDAO.countStylistOrder(stylistId);
        List<Order> orders = new ArrayList<>();
        for (StylistJollyOrder stylistOrder : stylistOrders) {
            try {
                List<JollyItem> items = cooperationItemDAO.listCooperationItem(null, null, null,
                        stylistOrder.getDetail(), null);

                Order order = new Order();
                order.setItems(items);
                order.setStylistOrder(stylistOrder);

                orders.add(order);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Map<String, Object> payload = new HashMap<>();
        payload.put("orders", orders);
        payload.put("total", total);
        res.setPayload(payload);
        res.setMessage(Message.SUCCESS);

        return res;
    }

    /**
     * Create a new order of the stylist
     *
     * @param stylistOrder
     * @return
     */
    @Override
    public Response createStylistOrder(StylistJollyOrder stylistOrder) {
        Response res = new Response();

        final long now = System.currentTimeMillis();
        stylistOrder.setCtime(now);
        stylistOrder.setUtime(now);
        stylistOrder.setStatus(Status.ORDER_SUBMITTED.getCode());

        try {
            stylistOrderDAO.createStylistOrder(stylistOrder);

            stylistJollyItemDAO.updateBatchStylistJollyItem(stylistOrder.getDetail(), Status
                    .WAIT_FOR_CHECK.getCode(), now);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.FAILURE);
            return res;
        }

        res.setMessage(Message.SUCCESS);

        return res;
    }

    private class Order {
        private StylistJollyOrder stylistOrder;
        private List<JollyItem> items;

        public StylistJollyOrder getStylistOrder() {
            return stylistOrder;
        }

        public void setStylistOrder(StylistJollyOrder stylistOrder) {
            this.stylistOrder = stylistOrder;
        }

        public List<JollyItem> getItems() {
            return items;
        }

        public void setItems(List<JollyItem> items) {
            this.items = items;
        }

    }
}
