package com.mode.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mode.config.Response;
import com.mode.service.AccountService;

/**
 * Created by zhaoweiwei on 15/11/17.
 */
@RestController
@RequestMapping("/v2")
public class AccountAPI {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AccountService accountService;

    /**
     * @api {post} /v2/login Login via username and password
     * @apiName login
     * @apiGroup Account
     * @apiHeader {String} username User's unique username, phone number or email
     * @apiHeader {String} password User's password
     * @apiVersion 2.0.0
     * @apiSuccessExample {json} Success 200:
     * {
     * "message":{
     * "code": 0,
     * "description": "Operation succeeded."
     * },
     * "payload":{
     * "loginUser":{
     * "userId": 30026,
     * "username": "z_w_w@hotmail.com",
     * "token":"eyJ1c2VySWQi...",
     * "expires": 1436760194172,
     * "role": "STYLIST"
     * }
     * }
     * }
     * @apiErrorExample {json} Error:
     * {
     * "message": {
     * "code": 3,
     * "description": "This user already exists."
     * },
     * "payload": null
     * }
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response authenticate(@RequestHeader("username") String username,
                                 @RequestHeader("password") String password) {
        Response res = accountService.login(username, password);
        logger.info("/v2/login, {}, {}, {}", username, password, res.getMessage());
        return res;
    }

    /**
     * @api {post} /v2/changePassword Change password
     * @apiName changePassword
     * @apiGroup Account
     * @apiHeader {String} username User's unique username, phone number or email
     * @apiHeader {String} oldPassword User's old password
     * @apiHeader {String} newPassword User's new password
     * @apiVersion 2.0.0
     * @apiSuccessExample {json} Success 200:
     * {
     * "message":{
     * "code": 0,
     * "description": "Operation succeeded."
     * },
     * "payload":{
     * "loginUser":{
     * "userId": 30026,
     * "username": "z_w_w@hotmail.com",
     * "token":"eyJ1c2VySWQi...",
     * "expires": 1436760194172,
     * "role": "STYLIST"
     * }
     * }
     * }
     * @apiErrorExample {json} Error:
     * {
     * "message": {
     * "code": 401,
     * "description": "Unauthorized to access"
     * },
     * "payload": null
     * }
     */
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public Response changePassword(@RequestHeader("username") String username,
                                   @RequestHeader("oldPassword") String oldPassword,
                                   @RequestHeader("newPassword") String newPassword) {
        Response res = accountService.changePassword(username, oldPassword, newPassword);
        logger.info("/v2/login, {}, {}, {}, {}", username, oldPassword, newPassword, res
                .getMessage());
        return res;
    }

}
