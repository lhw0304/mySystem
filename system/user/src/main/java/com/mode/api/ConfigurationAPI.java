package com.mode.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mode.base.BaseConfig;
import com.mode.base.Response;
import com.mode.security.AuthenticationUser;
import com.mode.service.ConfigurationService;

/**
 * Created by Lei on 3/14/16.
 */
@RestController
@RequestMapping("/v3")
public class ConfigurationAPI {

    @Autowired
    private ConfigurationService configurationService;

    /**
     * @api {GET} /v3/configs?limit={}&offset={}&country={}&type={} Get a list of configurations
     * @apiName config
     * @apiGroup Configuration
     * @apiVersion 3.0.0
     * @apiDescription This api is used to get a list of configurations
     *
     * @apiPermission ALL
     *
     * @apiParam {Number} [limit=10]        The limit number of the feeds
     * @apiParam {Number} [offset=0]        The offset of the feeds response
     * @apiParam {String} [country=US]      The country code of the results
     * @apiParam {String} [type=null]       The type of the config
     *
     * @apiExample Example usage:
     * GET
     * "http://api.whatsmode.com/v3/configs?limit=10&offset=0&country=US&type=userConfig"
     *
     * @apiSuccess {Number} code                The code of the message
     * @apiSuccess {String} message             The message content of the response
     * @apiSuccess {Object[]} payload           The data of the response
     * @apiSuccess {Number} payload.id          The unique id of the config
     * @apiSuccess {String} payload.type        The Type of the config
     * @apiSuccess {String} payload.attributeName   The name of the config
     * @apiSuccess {String} payload.attributeValue  The value of the config
     * @apiSuccess {String} payload.country     The country of the config
     *
     * @apiSuccessExample {json} Success 200 (Response):
     * {
     *      "code": 0
     *      "message": "Operation Successful",
     *      "payload": [
     *          {
     *              "id": 1,
     *              "type": "userConfig",
     *              "attributeName": "message1",
     *              "attributeValue": "Operation Failed.",
     *              "country": "US"
     *          },
     *          ....
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
    @RequestMapping(value = "/configs", method = RequestMethod.GET)
    public Response config(@RequestParam(value = "limit", required = false, defaultValue = "10")
                               Integer limit,
                           @RequestParam(value = "offset", required = false, defaultValue = "0")
                           Integer offset,
                           @RequestParam(value = "country", required = false, defaultValue = "US")
                               String country,
                           @RequestParam(value = "type", required = false) String type,
                           @AuthenticationPrincipal AuthenticationUser user){
        Response res = configurationService.config(user.getUserId(), type, country, limit, offset);
        return res;
    }
}
