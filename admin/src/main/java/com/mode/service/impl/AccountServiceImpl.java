package com.mode.service.impl;


import com.mode.config.AppConfig;
import com.mode.config.BaseConfig;
import com.mode.config.Message;
import com.mode.config.Response;
import com.mode.dao.AccountDAO;
import com.mode.entity.Account;
import com.mode.entity.Profile;
import com.mode.security.LoginUser;
import com.mode.service.AccountService;
import com.mode.util.TokenHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authManager;

    public Account getAccountByUsername(String username) {
        return accountDAO.getAccountByUsername(username);
    }

    private String updateAccessToken(String username, String role) {
        long expires = System.currentTimeMillis() + BaseConfig.EXPIRE_IN_TWO_WEEKS;
        String token = TokenHandler.createToken(username);
        final Long now = System.currentTimeMillis();
        accountDAO.updateAccessToken(token, username, expires,now);
        return token;
    }

    /**
     * Return a access token string after successfully login.
     */
    public Response Login(String username, String password) {

        Response res = new Response();

        Account account = new Account();

        account = getAccountByUsername(username);
        if (account == null) {
            res.setMessage(Message.USER_NOT_EXIST);
            return res;
        }


        try {
            // Authenticate the user by its username and password
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(account.getUsername(), password);
            Authentication authentication = authManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (AuthenticationException e) {
            res.setMessage(Message.UNAUTHORIZED);
            return res;
        }

        // Refresh the token
        updateAccessToken(account.getUsername(), account.getRole());

        // Retrive the updated account info
        Account ret = getAccountByUsername(account.getUsername());

        // Success response
        res.setMessage(Message.SUCCESS);

        LoginUser user = new LoginUser();
        user.setUserId(ret.getUserId());
        user.setUsername(ret.getUsername());
        user.setToken(ret.getAccessToken());
        user.setExpires(ret.getExpireTime());
        user.setRole(ret.getRole());
        Map<String, LoginUser> payload = new HashMap<String, LoginUser>();
        payload.put("loginUser", user);
        res.setPayload(payload);

        return res;
    }

    private Account createAccountInternal(Account account, Profile profile) {
        try {
            /* Add a new account to database. */
            accountDAO.createAccount(account);

            /* Create an access token and save to database. */
            updateAccessToken(account.getUsername(), account.getRole());

            /* Query and double check if account has been created. */
            Account createdAcct = accountDAO.getAccountByUsername(account.getUsername());
            if ((createdAcct == null) || !createdAcct.getUsername().equals(account.getUsername())) {
                return null;
            }

            /* We'll need to add a new profile for this new account. */
            Profile p = profile;
            p.setUserId(createdAcct.getUserId());

            /* Set the successful response */
            return createdAcct;
        } catch (Exception e) {
            return null;
        }
    }

}