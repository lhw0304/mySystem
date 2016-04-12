package com.mode.service;

import com.mode.base.Response;

/**
 * Created by kang on 2016/3/23.
 */
public interface ItemService {

    /**
     * Get item by item id
     *
     * @param itemId
     * @return
     */
    public Response getItem(Integer itemId);

    /**
     * Get item list
     *
     * @param limit
     * @param offset
     * @param country
     * @param brandId
     * @return
     */
    public Response listItems(Integer limit, Integer offset, String country, Integer brandId);
}
