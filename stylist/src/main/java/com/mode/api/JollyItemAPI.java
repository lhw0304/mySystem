package com.mode.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mode.config.Response;
import com.mode.service.JollyItemService;

/**
 * Created by Lei on 3/25/16.
 */
@RestController
@RequestMapping("/v2")
public class JollyItemAPI {

    @Autowired
    private JollyItemService cooperationItemService;

    /**
     * @api {GET} /v2/cooperationitems Get a list of cooperation items for the stylist
     * @apiName listCooperationItems
     * @apiGroup JollyItem
     * @apiParam {String} [source]  The source of the cooperation items
     * @apiParam {Number} [limit]   The limit number of result to retrieve
     * @apiParam {Number} [offset]  The offset number of result to retrieve
     * @apiHeader {String} X-Auth-Token Access token for authentication
     * @apiVersion 2.0.0
     */
    @RequestMapping(value = "/cooperationitems", method = RequestMethod.GET)
    public Response listCooperationItems(@RequestParam(value = "source", required = false) String
                                                 source,
                                         @RequestParam(value = "itemNumber", required = false)
                                         String itemNumber,
                                         @RequestParam(value = "limit", required = false)
                                             Integer limit,
                                         @RequestParam(value = "offset", required = false) Integer offset) {
        Response res = cooperationItemService.listCooperationItems(source, itemNumber, limit,
                offset);
        return res;
    }

    /**
     * @api {GET} /v2/stylists/{stylistId}/cooperationitems/{itemId} Select an item
     * @apiName selectItems
     * @apiGroup JollyItem
     * @apiParam {Number} stylistId   The unique id of the stylist
     * @apiParam {Number} itemId      The unique id of the cooperation item
     * @apiHeader {String} X-Auth-Token Access token for authentication
     * @apiVersion 2.0.0
     */
    @RequestMapping(value = "/stylists/{stylistId}/cooperationitems/{itemId}", method =
            RequestMethod.POST)
    public Response selectItems(@PathVariable("stylistId") Integer stylistId,
                                @PathVariable("itemId") Integer itemId) {
        Response res = cooperationItemService.selectItems(stylistId, itemId);
        return res;
    }

    /**
     * @api {GET} /v2/stylists/{stylistId}/cooperationitems Get a list of items the stylist selected
     * @apiName listStylistSelectedItems
     * @apiGroup JollyItem
     * @apiParam {Number} stylistId     The unique id of the stylist
     * @apiParam {Number} [limit]       The limit number of result to retrieve
     * @apiParam {Number} [offset]      The offset number of result to retrieve
     * @apiHeader {String} X-Auth-Token Access token for authentication
     * @apiVersion 2.0.0
     */
    @RequestMapping(value = "/stylists/{stylistId}/cooperationitems", method = RequestMethod.GET)
    public Response listStylistSelectedItems(@PathVariable("stylistId") Integer stylistId,
                                             @RequestParam(value = "limit", required = false)
                                             Integer limit,
                                             @RequestParam(value = "offset", required = false)
                                             Integer offset,
                                             @RequestParam(value = "status", required = false)
                                             Integer status) {
        Response res = cooperationItemService.listStylistItems(stylistId, limit, offset, status);
        return res;
    }

    /**
     * @api {GET} /v2/stylists/cooperationitems/{id} Delete a stylist item
     * @apiName deleteStylistSelectedItem
     * @apiGroup JollyItem
     * @apiParam {Number} id   The unique id of the stylist item
     * @apiHeader {String} X-Auth-Token Access token for authentication
     * @apiVersion 2.0.0
     */
    @RequestMapping(value = "/stylists/cooperationitems/{id}", method =
            RequestMethod.POST)
    public Response deleteStylistSelectedItem(@PathVariable("id") Integer id) {
        Response res = cooperationItemService.deleteStylistItem(id);
        return res;
    }

    /**
     * @api {GET} /v2/cooperationitems/summary Get a list of cooperation items summary
     * @apiName listCooperationItemSummary
     * @apiGroup JollyItem
     * @apiParam {Number} [limit]   The limit number of result to retrieve
     * @apiParam {Number} [offset]  The offset number of result to retrieve
     * @apiHeader {String} X-Auth-Token Access token for authentication
     * @apiVersion 2.0.0
     */
    @RequestMapping(value = "/cooperationitems/summary", method = RequestMethod.GET)
    public Response listCooperationItemSummary(@RequestParam("limit") Integer limit,
                                               @RequestParam("offset") Integer offset) {
        Response res = cooperationItemService.listCooperationItemSummary(limit, offset);
        return res;
    }
}
