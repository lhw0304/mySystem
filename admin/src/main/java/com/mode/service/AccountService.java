package com.mode.service;

import com.mode.config.Response;
import com.mode.entity.Account;

public interface AccountService {


    public Account getAccountByUsername(String username);

    public Response Login(String username, String password);

    public Response getProfile(Integer userId);

}