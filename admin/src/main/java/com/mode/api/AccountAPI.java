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


@RestController
@RequestMapping("/account")
public class AccountAPI {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response authenticate(@RequestHeader("username") String username,
                                 @RequestHeader("password") String password) {
        Response res = accountService.Login(username, password);
        logger.info("/account/login, {}, {}, {}", res.getMessage(), username, password);
        return res;
    }

}