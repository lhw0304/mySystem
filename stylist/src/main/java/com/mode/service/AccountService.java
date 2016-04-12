package com.mode.service;

import java.util.List;

import com.mode.config.Response;
import com.mode.entity.Account;

/**
 * Created by zhaoweiwei on 15/11/17.
 */
public interface AccountService {

    /**
     * Return a access token string after successfully login.
     *
     * @param username
     * @param password
     * @return
     */
    public Response login(String username, String password);

    /**
     * Change password
     *
     * @param username
     * @param oldPassword
     * @param newPassword
     * @return
     */
    public Response changePassword(String username, String oldPassword, String newPassword);

    public List<Account> listStylists();

}
