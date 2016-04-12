package com.mode.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mode.config.BaseConfig;
import com.mode.config.Message;
import com.mode.config.Response;
import com.mode.dao.AccountDAO;
import com.mode.dao.ProfileDAO;
import com.mode.entity.Account;
import com.mode.entity.Profile;
import com.mode.security.LoginUser;
import com.mode.service.AccountService;
import com.mode.util.TokenHandler;

/**
 * Created by zhaoweiwei on 15/11/17.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private ProfileDAO profileDAO;

    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authManager;

    public Account getAccountByUsername(String username) {
        return accountDAO.getAccountByUsername(username);
    }

    private String updateAccessToken(String username, String role) {
        long expires = System.currentTimeMillis() + BaseConfig.EXPIRE_IN_TWO_WEEKS;
        String token = TokenHandler.createToken(username, role, expires);
        final Long now = System.currentTimeMillis();
        accountDAO.updateAccessToken(token, username, expires,now);
        return token;
    }

    /**
     * Return a access token string after successfully login.
     */
    public Response login(String username, String password) {

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

    @Override
    public Response changePassword(String username, String oldPassword, String newPassword) {
        Response res = new Response();

        Account account = new Account();
        if (username.contains("@")) {
            // Login by email address
            Profile profile = profileDAO.getProfileByEmail(username);
            if (profile == null) {
                res.setMessage(Message.USER_NOT_EXIST);
                return res;
            }
            account = accountDAO.getAccountByUserId(profile.getUserId());
        } else {
            // Login by phone number
            account = getAccountByUsername(username);
            if (account == null) {
                res.setMessage(Message.USER_NOT_EXIST);
                return res;
            }
        }

        try {
            // Authenticate the user by its username and old password
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(account.getUsername(), oldPassword);
            Authentication authentication = authManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (AuthenticationException e) {
            res.setMessage(Message.UNAUTHORIZED);
            return res;
        }

        // Update password
        accountDAO.updatePassword(account.getUserId(), newPassword);

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

    @Override
    public List<Account> listStylists() {
        return accountDAO.listStylists();
    }
}
