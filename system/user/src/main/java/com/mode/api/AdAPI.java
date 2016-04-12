package com.mode.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mode.base.BaseConfig;
import com.mode.base.Response;
import com.mode.service.AdService;

/**
 * Created by Lei on 3/14/16.
 */
@RestController
@RequestMapping("/v3")
public class AdAPI {

    @Autowired
    private AdService adService;

    /**
     * @api {GET} /v3/ads?limit={}&offset={}&country={}&displayPage={} Get a list of ads
     * @apiName listAds
     * @apiGroup Advertisement
     * @apiVersion 3.0.0
     * @apiDescription This api is used to get a list of ads
     *
     * @apiPermission USER, ANONYMOUS
     *
     * @apiParam {Number} [limit=10]            The limit number of the feeds
     * @apiParam {Number} [offset=0]            The offset of the feeds response
     * @apiParam {String} [country=US]          The country where the ad presents
     * @apiParam {String} [displayPage=startup] Where the ad displays
     *
     * @apiHeader {String} X-Access-Token    The unique auth token of the user
     *
     * @apiExample Example usage:
     * GET
     * "http://api.whatsmode.com/v3/ads?limit=10&offset=0&country=US&displayPage=startup"
     * -h "Authorization:Bearer eyJ1c2VySWQiOj.AsInVzZXJuYW1lIj.oiNTMyNzQ5NTUzQH;"
     *
     * @apiSuccess {Number} code                 The code of the message
     * @apiSuccess {String} message              The message content of the response
     * @apiSuccess {Object} payload              The data of the response
     * @apiSuccess {Number} payload.id           The unique id of the ad
     * @apiSuccess {String} payload.type         The type of the ad. 'discovery' or 'startup'
     * @apiSuccess {String} payload.displayPage  The display page of the ad
     * @apiSuccess {Number} payload.status       Show if the ad is enabled. 0:disabled, 1:enabled
     * @apiSuccess {String} payload.defaultImage The default cover image of the ad
     * @apiSuccess {String} payload.title        The title of the ad
     * @apiSuccess {String} payload.description  The detail description of the ad
     * @apiSuccess {Object[]} payload.content       The content elements of the feed
     * @apiSuccess {String} payload.content.type    The type of the content element
     * @apiSuccess {String} payload.content.content The content of the element
     * @apiSuccess {String} payload.content.defaultImage The image url
     * @apiSuccess {String} payload.content.widthHeightRatio The image w-h ratio
     * @apiSuccess {String} payload.content.items   The item id list split by ','
     * @apiSuccess {String} payload.content.title   The title of url element
     * @apiSuccess {String} payload.content.description The description of url element
     * @apiSuccess {String} payload.content.url     The url of the url element
     *
     * @apiSuccessExample {json} Success 200 (Response Example):
     * {
     *      "code": 0
     *      "message": "Operation Successful",
     *      "payload": [
     *          {
     *              "id": 1,
     *              "type": "article",
     *              "displayPage": "discovery",
     *              "status": 0,
     *              "defaultImage": "http://img.cdn.whatsmodecom/images/image_4.png",
     *              "title": "100% off, Come on",
     *              "description": "Just Today, you can get everything for free.",
     *              "content": [
     *                  {
     *                      "type": "text",
     *                      "content": " MODE now is opening up a special feature—all!",
     *                      "bold": ""
     *                  },
     *                  {
     *                      "type": "image",
     *                      "defaultImage": "http://img.cdn.whatsmode.com/images/9e62bd97a346.gif",
     *                      "widthHeightRatio": "1:1.42",
     *                      "url": "",
     *                      "items": "",
     *                      "points": []
     *                  }
     *              ]
     *          }
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
    @RequestMapping(value = "/ads", method = RequestMethod.GET)
    public Response listAds(@RequestParam(value = "limit", required = false, defaultValue = "10")
                                Integer limit,
                            @RequestParam(value = "offset", required = false, defaultValue = "0")
                            Integer offset,
                            @RequestParam(value = "country", required = false, defaultValue =
                                    "US") String country,
                            @RequestParam(value = "displayPage", required = false, defaultValue =
                                    "startup") String displayPage){
        Response res = adService.listAds(displayPage, country, limit, offset);
        return res;
    }
}
