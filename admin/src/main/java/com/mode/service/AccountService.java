package com.mode.service;

import com.mode.config.Response;
import com.mode.entity.Account;

public interface AccountService {

    /**
     * Find account info by username
     *
     * @param username
     * @return
     */
    public Account getAccountByUsername(String username);

    /**
     * Return a access token string after successfully login.
     *
     * @param username
     * @param password
     * @return
     */
    public Response Login(String username, String password);

    /**
     * check merchant
     *
     * @param userId
     * @return
     */
    public Response updateMerchantAccount(Integer userId);

    /**
     * list merchant
     *
     * @return
     */
    public Response listMerchant();

    /**
     * create stylist account
     *
     * @param email
     * @param password
     * @param name
     * @param role
     * @param level
     * @return
     */
    public Response createStylistAccount(String email, String password, String name, String role,
                                         Integer level, String country);

    /**
     * get user account information by userId or nsername
     *
     * @param userId
     * @param username
     * @return
     */
    public Response getAccountInformation(Integer userId, String username, Integer limit, Integer
            offset);
}