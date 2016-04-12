package com.mode.service;

import com.mode.config.Response;

/**
 * Created by Lei on 3/25/16.
 */
public interface JollyItemService {

    /**
     * Get a list of cooperation items
     *
     * @param source
     * @param limit
     * @param offset
     * @return
     */
    public Response listCooperationItems(String source, String itemNumber, Integer limit, Integer
            offset);

    /**
     * Stylists select items
     *
     * @param stylistId
     * @param itemId
     * @return
     */
    public Response selectItems(Integer stylistId, Integer itemId);

    /**
     * Get a list items the stylist selected
     *
     * @param stylistId
     * @param limit
     * @param offset
     * @param status
     * @return
     */
    public Response listStylistItems(Integer stylistId, Integer limit, Integer offset, Integer
            status);

    /**
     * Delete an item the stylist selected
     *
     * @param id
     * @return
     */
    public Response deleteStylistItem(Integer id);

    /**
     * Get a list of cooperation items summary
     *
     * @param limit
     * @param offset
     * @return
     */
    public Response listCooperationItemSummary(Integer limit, Integer offset);
}
