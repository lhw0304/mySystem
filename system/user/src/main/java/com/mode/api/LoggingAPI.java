package com.mode.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mode.base.BaseConfig;
import com.mode.base.Response;
import com.mode.entity.UserActionLog;
import com.mode.service.LoggingService;
import com.sun.javafx.Logging;

/**
 * Created by Lei on 3/14/16.
 */
@RestController
@RequestMapping("/v3")
public class LoggingAPI {

    @Autowired
    private LoggingService loggingService;

    /**
     * @api {POST} /v3/logs Log actions of the user
     * @apiName logging
     * @apiGroup Logging
     * @apiVersion 3.0.0
     * @apiDescription This api is used to log
     *
     * @apiPermission All
     *
     * @apiExample Example usage:
     * curl -i "http://api.whatsmode.com/v3/logs"
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
    @RequestMapping(value = "/logs", method = RequestMethod.POST)
    public Response logging(@RequestBody UserActionLog[] userActionLogs) {
        Response res = loggingService.logging(userActionLogs);
        return res;
    }
}
