package com.mode.service;

import com.mode.config.Response;
import com.mode.entity.Account;

public interface AccountService {


    public Account getAccountByUsername(String username);

    public Response Login(String username, String password);


    public Response updateMerchantAccount(Integer userId);

    public Response createStylistAccount(String email, String password, String name, String role,
                                         Integer level, String country);


    public Response getAccountInformation(Integer userId, String username, Integer limit, Integer
            offset);
}