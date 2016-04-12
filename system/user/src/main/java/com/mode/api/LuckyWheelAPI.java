package com.mode.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mode.base.BaseConfig;
import com.mode.base.Response;
import com.mode.security.AuthenticationUser;
import com.mode.service.LuckyWheelService;

/**
 * Created by Lei on 3/14/16.
 */
@RestController
@RequestMapping("/v3")
public class LuckyWheelAPI {

    @Autowired
    private LuckyWheelService luckyWheelService;

    /**
     * @api {GET} /v3/luckywheels?country={} Get a list of lucky wheel items
     * @apiName listLuckyWheelItems
     * @apiGroup LuckyWheel
     * @apiVersion 3.0.0
     * @apiDescription This api is used to get a list of lucky wheel items
     *
     * @apiPermission USER,ANONYMOUS
     *
     * @apiParam {String} [country=US]   The country of the lucky wheel items
     *
     * @apiHeader {String} X-Access-Token    The unique auth token of the user
     *
     * @apiExample Example usage:
     * curl -i "http://api.whatsmode.com/v3/luckywheels"
     * -h "Authorization:Bearer eyJ1c2VySWQiOj.AsInVzZXJuYW1lIj.oiNTMyNzQ5NTUzQH;"
     *
     * @apiSuccess {Number} code                The code of the message
     * @apiSuccess {String} message             The message content of the response
     * @apiSuccess {Object[]} payload           The data of the response
     * @apiSuccess {Number} payload.id          The unique id of the prize
     * @apiSuccess {String} payload.prizeDetail The price detail
     *
     * @apiSuccessExample {json} Success 200 (Response):
     * {
     *      "code": 0
     *      "message": "Operation Successful",
     *      "payload": [
     *          {
     *              "id": 12,
     *              "prizeDetail": ""
     *          },
     *          ......
     *      ]
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
    @RequestMapping(value = "/luckywheels", method = RequestMethod.GET)
    public Response listLuckyWheelItems(@RequestParam(value = "country", required = false,
            defaultValue = "US") String country){
        Response res = luckyWheelService.listLuckyWheelItems(country);
        return res;
    }

    /**
     * @api {POST} /v3/luckywheels Spin a lucky wheel
     * @apiName spinLuckyWheel
     * @apiGroup LuckyWheel
     * @apiVersion 3.0.0
     * @apiDescription This api is used to spin a lucky wheel and get a result
     *
     * @apiPermission USER
     *
     * @apiParam {String} [country=US]  The country of the lucky wheel items
     *
     * @apiHeader {String} X-Access-Token    The unique auth token of the user
     *
     * @apiExample Example usage:
     * curl -i "http://api.whatsmode.com/v3/luckywheels"
     * -h "
     * Authorization:Bearer eyJ1c2VySWQiOj.AsInVzZXJuYW1lIj.oiNTMyNzQ5NTUzQH;
     * "
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
     *          "id": 3,
     *          "prizeDetail": ""
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
    @RequestMapping(value = "/luckywheels", method = RequestMethod.POST)
    public Response spinLuckyWheel(@AuthenticationPrincipal AuthenticationUser user,
                                   @RequestParam(value = "country", required = false,
                                           defaultValue = "US") String country){
        Response res = luckyWheelService.spinLuckyWheel(user.getUserId(), country);
        return res;
    }
}
