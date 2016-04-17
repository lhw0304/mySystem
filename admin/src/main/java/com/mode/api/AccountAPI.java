package com.mode.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mode.config.Response;
import com.mode.service.AccountService;
import org.springframework.web.multipart.MultipartHttpServletRequest;


@RestController
@RequestMapping("/system")
public class AccountAPI {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response authenticate(@RequestHeader("username") String username,
                                 @RequestHeader("password") String password) {
        Response res = accountService.Login(username, password);
        logger.info("/v2/login, {}, {}, {}", res.getMessage(), username, password);
        return res;
    }

    @RequestMapping(value = "/profile/{userId}", method = RequestMethod.GET)
    public Response getProfile(@PathVariable Integer userId) {
        Response res = accountService.getProfile(userId);
        logger.info("/v2/profile, {}, {}, {}", res.getMessage(), userId);
        return res;
    }

    @RequestMapping(value = "/add/account", method = RequestMethod.POST)
    public Response addAccount(@RequestHeader(value = "username") String username,
                               MultipartHttpServletRequest mRequest) {
        Response res = accountService.addAccount(username, mRequest);
        logger.info("/system/add/account,{},{}", res.getMessage(), username);
        return res;
    }

    @RequestMapping(value = "/profile/{userId}", method = RequestMethod.POST)
    public Response updateProfile(@PathVariable Integer userId,
                                  MultipartHttpServletRequest mRequest) {
        Response res = accountService.updateProfile(userId, mRequest);
        logger.info("/v2/profile, {}, {}, {}", res.getMessage(), userId);
        return res;
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public Response changePassword(@RequestHeader("userId") Integer userId,
                                   @RequestHeader("oldPassword") String oldPassword,
                                   @RequestHeader("newPassword") String newPassword) {
        Response res = accountService.changePassword(userId, oldPassword, newPassword);
        logger.info("/v2/login, {}, {}, {}, {}", userId, oldPassword, newPassword, res
                .getMessage());
        return res;
    }
}