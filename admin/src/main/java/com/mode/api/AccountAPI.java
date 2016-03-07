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
@RequestMapping("/v2")
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

    @RequestMapping(value = "/users/{userId}/check", method = RequestMethod.POST)
    public Response updateMerchantAccount(@PathVariable Integer userId) {
        Response res = accountService.updateMerchantAccount(userId);
        logger.info("/v2/check,{}", res.getMessage());
        return res;
    }

    @RequestMapping(value = "/merchants", method = RequestMethod.GET)
    public Response listMerchant() {
        Response res = accountService.listMerchant();
        logger.info("/v2/merchants,{}", res.getMessage());
        return res;
    }

    @RequestMapping(value = "/stylist", method = RequestMethod.POST)
    public Response createStylistAccount(@RequestHeader(value = "email") String email,
                                         @RequestHeader(value = "password") String password,
                                         @RequestParam(value = "name") String name,
                                         @RequestParam(value = "role") String role,
                                         @RequestParam(value = "level",required = false) Integer
                                                 level,
                                         @RequestParam(value = "country",required = false) String
                                                 country){
        Response res = accountService.createStylistAccount(email, password, name, role, level,
                country);
        logger.info("/v2/stylist",res.getMessage());
        return res;
    }
    
    @RequestMapping(value = "/accountInformation", method = RequestMethod.GET)
    public Response getAccountInformation(@RequestParam(value = "userId",required = false) Integer
                                                  userId,
                                          @RequestParam(value = "username",required = false)
                                          String username,
                                          @RequestParam(value = "limit",required = false) Integer
                                                  limit,
                                          @RequestParam(value = "offset",required = false)
                                          Integer offset) {
        Response res = accountService.getAccountInformation(userId, username, limit, offset);
        logger.info("/v2/accountInformation?userId={}&username={},{}",res.getMessage(),userId,username);
        return  res;
    }
}