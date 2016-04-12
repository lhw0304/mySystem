package com.mode.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mode.base.BaseConfig;
import com.mode.base.Response;
import com.mode.security.AuthenticationUser;
import com.mode.service.CreditZoneService;

/**
 * Created by Lei on 3/14/16.
 */
@RestController
@RequestMapping("/v3")
public class CreditZoneAPI {

    @Autowired
    private CreditZoneService creditZoneService;

    /**
     * @api {GET} /v3/creditzones?limit={}&offset={}&country={} Get a list of lucky wheel items
     * @apiName listCreditZoneItems
     * @apiGroup CreditZone
     * @apiVersion 3.0.0
     * @apiDescription This api is used to get a list of lucky wheel items
     *
     * @apiPermission ALL
     *
     * @apiParam {Number} [limit=10]        The limit number of the feeds
     * @apiParam {Number} [offset=0]        The offset of the feeds response
     * @apiParam {String} [country=US]      The country code of the results
     *
     * @apiExample Example usage:
     * GET
     * "http://api.whatsmode.com/v3/creditzones?limit=10&offset=0&country=US"
     *
     * @apiSuccess {Number} code                The code of the message
     * @apiSuccess {String} message             The message content of the response
     * @apiSuccess {Object} payload             The data of the response
     *
     * @apiSuccessExample {json} Success 200 (Response):
     * {
     *      "code": 0
     *      "message": "Operation Successful",
     *      "payload": {
     *
     *      }
     * }
     *
     * @apiError {Number} code      The code of the message
     * @apiError {String} message   The error message content of the response
     * @apiError {Object} payload   The data of the response
     *
     * @apiErrorExample {json} Error 4xx (Response):
     * {
     *      "code": 404
     *      "message": "Not Found",
     *      "payload": null
     * }
     *
     */
    @RequestMapping(value = "/creditzones", method = RequestMethod.GET)
    public Response listCreditZoneItems(@RequestParam(value = "limit", required = false,
                                                defaultValue = "10") Integer limit,
                                        @RequestParam(value = "offset", required = false,
                                                defaultValue = "0") Integer offset,
                                        @RequestParam(value = "country", required = false,
                                                defaultValue = "US") String country){
        Response res = creditZoneService.listCreditZoneItems(limit, offset, country);
        return res;
    }

    /**
     * @api {POST} /v3/creditzones/{creditzonesId}?amount={} Spin a lucky wheel
     * @apiName redeemItemInCreditZone
     * @apiGroup CreditZone
     * @apiVersion 3.0.0
     * @apiDescription This api is used to spin a lucky wheel and get a result
     *
     * @apiPermission USER
     *
     *
     * @apiParam {Number} creditzonesId The unique credit zone id
     * @apiParam {Number} costCredit    The credits to cost for redeeming items
     * @apiParam {Number} [amount=0]    The amount of the usd
     *
     * @apiHeader {String} X-Access-Token    The unique auth token of the user
     *
     * @apiExample Example usage:
     * POST
     * "http://api.whatsmode.com/v3/creditzones/1?amount=0"
     * -h "X-Access-Token: eyJ1c2VySWQiOj.AsInVzZXJuYW1lIj.oiNTMyNzQ5NTUzQH;"
     *
     * @apiSuccess {Number} code                The code of the message
     * @apiSuccess {String} message             The message content of the response
     * @apiSuccess {Object} payload             The data of the response
     * @apiSuccess {String} payload.prizeDetail The detail of the prize
     *
     * @apiSuccessExample {json} Success 200 (Response):
     * {
     *      "code": 0
     *      "message": "Operation Successful",
     *      "payload": {
     *          "prizeDetail": "Wonderful dress!"
     *      }
     * }
     *
     * @apiError {Number} code      The code of the message
     * @apiError {String} message   The error message content of the response
     * @apiError {Object} payload   The data of the response
     *
     * @apiErrorExample {json} Error 4xx (Response):
     * {
     *      "code": 404
     *      "message": "Not Found",
     *      "payload": null
     * }
     *
     */
    @RequestMapping(value = "/creditzones/{creditzonesId}", method = RequestMethod.POST)
    public Response redeemItemInCreditZone(@AuthenticationPrincipal AuthenticationUser user,
                                           @PathVariable Integer creditzonesId,
                                           @RequestParam("costCredit") Integer costCredit,
                                           @RequestParam(value = "amount", required = false,
                                                   defaultValue = "0") Integer amount){
        Response res = creditZoneService.redeemItemInCreditZone(user.getUserId(),creditzonesId,
                costCredit, amount);
        return res;
    }
}
