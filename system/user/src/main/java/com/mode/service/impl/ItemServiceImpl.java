package com.mode.service.impl;

import com.mode.base.BaseConfig;
import com.mode.base.Message;
import com.mode.base.Response;
import com.mode.dao.ItemDAO;
import com.mode.entity.Item;
import com.mode.exception.ModeException;
import com.mode.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kang on 2016/3/23.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDAO itemDAO;

    @Override
    public Response getItem(Integer itemId) {
        Item item = new Item();
        item.setId(itemId);
        try {
            item = itemDAO.getItem(item);
        } catch (Exception e) {
            throw new ModeException(Message.NO_MORE_ITEMS);
        }

        Response res = new Response(Message.SUCCESS);
        res.setPayload(item);

        return res;
    }

    @Override
    public Response listItems(Integer limit, Integer offset, String country, Integer brandId) {
        try {
            // Uncomment these code after item dao has list item method
//            List<Item> list = itemDAO.listItems(brandId, null, limit, offset, country);
//            if (list.isEmpty() || list.size() == 0) {
//                throw new ModeException(Message.NO_MORE_ITEMS);
//            }
//            res.setPayload(list);
        } catch (Exception e) {
            throw new ModeException(Message.NO_MORE_ITEMS);
        }

        Response res = new Response(Message.SUCCESS);

        return res;
    }
}
