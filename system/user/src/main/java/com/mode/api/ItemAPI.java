package com.mode.api;

import com.fasterxml.jackson.annotation.JsonView;
import com.mode.base.Response;
import com.mode.entity.View;
import com.mode.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Lei on 3/14/16.
 */
@RestController
@RequestMapping("/v3")
public class ItemAPI {

    @Autowired
    private ItemService itemService;

    /**
     * @api {GET} /v3/items/{itemId} Get an item
     * @apiName getItem
     * @apiGroup Item
     * @apiVersion 3.0.0
     * @apiDescription This api is used to get an item
     *
     * @apiPermission USER, ANONYMOUS
     *
     * @apiParam {Number} itemId   The unique item id
     *
     * @apiHeader {String} X-Access-Token    The unique auth token of the user
     *
     * @apiExample Example usage:
     * curl -i GET http://api.whatsmode.com/v3/items/1
     * -h "X-Access-Token:eyJ1c2VySWQiOj.AsInVzZXJuYW1lIj.oiNTMyNzQ5NTUzQH"
     *
     * @apiSuccess {Number} code                The code of the message
     * @apiSuccess {String} message             The message content of the response
     * @apiSuccess {Object} payload             The data of the response
     * @apiSuccess {Number} payload.id          The unique item id
     * @apiSuccess {String} payload.itemName    The item name
     * @apiSuccess {Number} payload.brandId     The id of the brand (brand is a user also)
     * @apiSuccess {Object} payload.brand       The brand which the item belongs to
     * @apiSuccess {String} payload.brand.name  The name of the brand
     * @apiSuccess {String} payload.brand.logo  The logo url of the brand
     * @apiSuccess {Number} payload.onsale      Check if the item is on sale. 1: on sale
     * @apiSuccess {Number} payload.saleTime    The sale time of the item
     * @apiSuccess {String} payload.defaultImage    The default image of the item
     * @apiSuccess {String} payload.extraImage    The extra images of the item
     * @apiSuccess {Number} payload.price       The price of the item
     * @apiSuccess {Number} payload.salePrice   The sale price of the item
     * @apiSuccess {Number} payload.salePercent The sale percent of the item
     * @apiSuccess {String} payload.color       The color of the item
     * @apiSuccess {String} payload.size        The size of the item
     * @apiSuccess {String} payload.productLink The product link of the item
     * @apiSuccess {String} payload.sku         The sku of the item
     * @apiSuccess {String} payload.description The description of the item
     *
     * @apiSuccessExample {json} Success 200 (Response Example):
     * {
     *      "code": 0
     *      "message": "Operation Successful",
     *      "payload": {
     *          "id": 1
     *          "itemName": "LV Bag",
     *          "brandId": 1,
     *          "brand":{
     *              "name": "LV",
     *              "logo": "http://img.cdn.whatsmodecom/images/LV_logo.png"
     *          },
     *          "onsale": 1,
     *          "status": 1,
     *          "ctime": 1234567890123,
     *          "utime": 1234567890123,
     *          "saleTime": 1452367890123,
     *          "defaultImage": "http://img.cdn.whatsmodecom/images/item_1_image_1.png",
     *          "extraImage": "http:///item_1_image_3.png, http:///item_1_image_3.png,...",
     *          "price": 12.5,
     *          "salePrice": 11.5,
     *          "salePercent": 10,
     *          "views": 12,
     *          "shares": 123,
     *          "color": "ffffff,ffff33",
     *          "size": "L,S,M",
     *          "productLink": ""http://lv.com/item1",
     *          "sku": "123",
     *          "description": "wonder"
     *      }
     * }
     *
     * @apiError {Number} code      The code of the message
     * @apiError {String} message   The error message content of the response
     * @apiError {Object} payload   The data of the response
     *
     * @apiErrorExample {json} Error 4xx (Response Example):
     * {
     *      "code": 404
     *      "message": "Not Found",
     *      "payload": null
     * }
     *
     */
    @RequestMapping(value = "/items/{itemId}", method = RequestMethod.GET)
    public Response getItem(@PathVariable int itemId) {
        Response res = itemService.getItem(itemId);
        return res;
    }

    /**
     * @api {GET} /v3/items?itemId={} Get a list of items
     * @apiName listItems
     * @apiGroup Item
     * @apiVersion 3.0.0
     * @apiDescription This api is used to get a list of items
     *
     * @apiPermission USER, ANONYMOUS
     *
     * @apiParam {Number} [itemId=null]     The unique item id or id list
     * @apiParam {Number} [limit=0]         The limit number of the item list
     * @apiParam {Number} [offset=0]        The offset of the item list
     * @apiParam {String} [country=US]      The country code of the items
     *
     * @apiHeader {String} X-Access-Token    The unique auth token of the user
     *
     * @apiExample Example usage:
     * curl -i GET http://api.whatsmode.com/v3/items
     * -h "Authorization:Bearer eyJ1c2VySWQiOj.AsInVzZXJuYW1lIj.oiNTMyNzQ5NTUzQH"
     *
     * @apiSuccess {Number} code                The code of the message
     * @apiSuccess {String} message             The message content of the response
     * @apiSuccess {Object[]} payload           The data of the response
     * @apiSuccess {Number} payload.id          The unique item id
     * @apiSuccess {String} payload.itemName    The item name
     * @apiSuccess {Number} payload.brandId     The id of the brand (brand is a user also)
     * @apiSuccess {Object} payload.brand       The brand which the item belongs to
     * @apiSuccess {String} payload.brand.name  The name of the brand
     * @apiSuccess {String} payload.brand.logo  The logo url of the brand
     * @apiSuccess {Number} payload.onsale      Check if the item is on sale. 1: on sale
     * @apiSuccess {Number} payload.saleTime    The sale time of the item
     * @apiSuccess {String} payload.defaultImage  The default image of the item
     * @apiSuccess {String} payload.extraImage    The extra images of the item
     * @apiSuccess {Number} payload.price       The price of the item
     * @apiSuccess {Number} payload.salePrice   The sale price of the item
     * @apiSuccess {Number} payload.salePercent The sale percent of the item
     * @apiSuccess {String} payload.color       The color of the item
     * @apiSuccess {String} payload.size        The size of the item
     * @apiSuccess {String} payload.productLink The product link of the item
     * @apiSuccess {String} payload.sku         The sku of the item
     * @apiSuccess {String} payload.description The description of the item
     *
     * @apiSuccessExample {json} Success 200 (Response Example):
     * {
     *      "code": 0
     *      "message": "Operation Successful",
     *      "payload": [
     *          {
     *              "id": 1
     *              "itemName": "LV Bag",
     *              "brandId": 1,
     *              "brand":{
     *                  "name": "LV",
     *                  "logo": "http://img.cdn.whatsmodecom/images/LV_logo.png"
     *              },
     *              "onsale": 1,
     *              "status": 1,
     *              "ctime": 1234567890123,
     *              "utime": 1234567890123,
     *              "saleTime": 1452367890123,
     *              "defaultImage": "http://img.cdn.whatsmodecom/images/item_1_image_1.png",
     *              "extraImage": "http:///item_1_image_3.png, http:///item_1_image_3.png,...",
     *              "price": 12.5,
     *              "salePrice": 11.5,
     *              "salePercent": 10,
     *              "views": 12,
     *              "shares": 123,
     *              "color": "ffffff,ffff33",
     *              "size": "L,S,M",
     *              "productLink": ""http://lv.com/item1",
     *              "sku": "123",
     *              "description": "wonder"
     *          },
     *      ......
     *      ]
     * }
     *
     * @apiError {Number} code      The code of the message
     * @apiError {String} message   The error message content of the response
     * @apiError {Object} payload   The data of the response
     *
     * @apiErrorExample {json} Error 4xx (Response Example):
     * {
     *      "code": 404
     *      "message": "Not Found",
     *      "payload": null
     * }
     *
     */
    @JsonView(View.Summary.class)
    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public Response listItems(@RequestParam(value = "limit", required = false) Integer limit,
                              @RequestParam(value = "offset", required = false) Integer offset,
                              @RequestParam(value = "country", required = false) String country,
                              @RequestParam(value = "brandId", required = false) Integer brandId) {
        Response res = itemService.listItems(limit, offset, country, brandId);
        return res;
    }
}
