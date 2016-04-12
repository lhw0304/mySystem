package com.mode.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mode.config.Message;
import com.mode.config.Response;
import com.mode.dao.ItemDAO;
import com.mode.entity.Item;
import com.mode.service.ItemService;

/**
 * Created by Lei on 12/16/15.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDAO itemDAO;

    public Response getItem(Integer itemId){
        Response res = new Response();

        try {
            Item item = itemDAO.getItemByItemId(itemId);

            Map<String, Object> payload = new HashMap<>();
            payload.put("item", item);
            res.setPayload(payload);
            res.setMessage(Message.SUCCESS);
        } catch (Exception e){
            e.printStackTrace();
            res.setMessage(Message.FAILURE);
        }

        return res;
    }
}
