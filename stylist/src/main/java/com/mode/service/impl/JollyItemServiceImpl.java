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
import com.mode.dao.StylistJollyItemDAO;
import com.mode.entity.JollyItem;
import com.mode.entity.StylistItem;
import com.mode.entity.StylistJollyItem;
import com.mode.service.JollyItemService;

/**
 * Created by Lei on 3/25/16.
 */
@Service
public class JollyItemServiceImpl implements JollyItemService {

    @Autowired
    private JollyItemDAO cooperationItemDAO;

    @Autowired
    private StylistJollyItemDAO stylistItemDAO;

    /**
     * Get a list of cooperation items
     *
     * @param source
     * @param limit
     * @param offset
     * @return
     */
    @Override
    public Response listCooperationItems(String source, String itemNumber, Integer limit, Integer
            offset) {
        Response res = new Response();

        List<JollyItem> items = new ArrayList<>();
        try {
            items = cooperationItemDAO.listCooperationItem(source, limit, offset, null, itemNumber);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.FAILURE);
            return res;
        }

        Map<String, Object> payload = new HashMap<>();
        payload.put("items", items);

        res.setMessage(Message.SUCCESS);
        res.setPayload(payload);

        return res;
    }

    /**
     * Stylists select items
     *
     * @param stylistId
     * @param itemId
     * @return
     */
    public Response selectItems(Integer stylistId, Integer itemId) {
        Response res = new Response();

        final long now = System.currentTimeMillis();

        // Check if the stylist has selected the item
        StylistJollyItem stylistItem = stylistItemDAO.getStylistItem(stylistId, itemId, Status
                .NEW_RELEASE.getCode(), null);
        if (stylistItem == null) {

            stylistItem = new StylistJollyItem();
            stylistItem.setItemId(itemId);
            stylistItem.setStylistId(stylistId);
            stylistItem.setStatus(Status.NEW_RELEASE.getCode());
            stylistItem.setCtime(now);
            stylistItem.setUtime(now);

            try {
                stylistItemDAO.createStylistItems(stylistItem);
            } catch (Exception e) {
                e.printStackTrace();
                res.setMessage(Message.FAILURE);
                return res;
            }
        } else {
            res.setMessage(Message.HAS_BEEN_SAVED);
            return res;
        }

        res.setMessage(Message.SUCCESS);

        return res;
    }

    /**
     * Get a list items the stylist selected
     *
     * @param stylistId
     * @param limit
     * @param offset
     * @param status
     * @return
     */
    @Override
    public Response listStylistItems(Integer stylistId, Integer limit, Integer offset, Integer
            status) {
        Response res = new Response();

        List<StylistItem> items = new ArrayList<>();

        try {
            items = stylistItemDAO.listStylistItems(stylistId, limit, offset, status);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.FAILURE);
            return res;
        }

        Map<String, Object> payload = new HashMap<>();
        payload.put("items", items);
        payload.put("total", stylistItemDAO.getTotalStylistItems(stylistId, status));

        res.setMessage(Message.SUCCESS);
        res.setPayload(payload);

        return res;
    }

    /**
     * Delete an item the stylist selected
     *
     * @param id
     * @return
     */
    @Override
    public Response deleteStylistItem(Integer id) {
        Response res = new Response();

        Integer itemId = stylistItemDAO.getStylistItem(null, null, null, id).getItemId();

        try {
            stylistItemDAO.deleteStylistItem(id);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.FAILURE);
            return res;
        }

        // If the stylist remove the item, increase the quantity for that item.
        JollyItem item = cooperationItemDAO.getCooperationItem(itemId);
        item.setQuantity(item.getQuantity() + 1);
        cooperationItemDAO.updateCooperationItem(item);

        res.setMessage(Message.SUCCESS);

        return res;
    }

    /**
     * Get a list of cooperation items summary
     *
     * @param limit
     * @param offset
     * @return
     */
    @Override
    public Response listCooperationItemSummary(Integer limit, Integer offset) {
        Response res = new Response();

        limit = (limit == null) ? AppConfig.DEFAULT_LIMIT : limit;
        offset = (offset == null) ? AppConfig.DEFAULT_OFFSET : offset;

        List<JollyItem> items = new ArrayList<>();
        try {
            items = cooperationItemDAO.getCooperationItemStyles(limit, offset);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.FAILURE);
            return res;
        }

        Map<String, Object> payload = new HashMap<>();
        payload.put("items", items);
        payload.put("total", cooperationItemDAO.getTotalJollyItemNumbers());
        res.setPayload(payload);
        res.setMessage(Message.SUCCESS);

        return res;
    }
}
