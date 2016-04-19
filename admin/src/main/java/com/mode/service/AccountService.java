package com.mode.service;

import com.mode.config.Response;
import com.mode.entity.Account;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface AccountService {


    public Account getAccountByUsername(String username);

    public Response Login(String username, String password);

    public Response getProfile(Integer userId);

    public Response addAccount(String username, MultipartHttpServletRequest mRequest);

    public Response updateProfile(Integer userId, MultipartHttpServletRequest mRequest);

    public Response changePassword(Integer username, String oldPassword, String newPassword);

    public Response resetPassword(String username, String password);

}