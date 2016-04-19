package com.mode.service.impl;


import com.mode.config.*;
import com.mode.dao.*;
import com.mode.entity.Account;
import com.mode.entity.MultiSelect;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private ProfileDAO profileDAO;

    @Autowired
    private CheckDAO checkDAO;

    @Autowired
    private CompletionDAO completionDAO;

    @Autowired
    private SingleSelectDAO singleSelectDAO;

    @Autowired
    private MultiSelectDAO multiSelectDAO;

    @Autowired
    private ShortAnswerDAO shortAnswerDAO;

    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authManager;

    public Account getAccountByUsername(String username) {
        return accountDAO.getAccountByUsername(username);
    }

    private String updateAccessToken(String username, String role) {
        long expires = System.currentTimeMillis() + BaseConfig.EXPIRE_IN_TWO_WEEKS;
        String token = TokenHandler.createToken(username,role,expires);
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

    @Override
    public Response getProfile(Integer userId) {
        Response res = new Response();
//        try {
            Profile profile = profileDAO.getProfileByUserId(userId);
            if (profile == null) {
                res.setMessage(Message.NO_MORE_SINGLE_SELECT);
                return res;
            }
            Integer checkCount = checkDAO.countCheck(userId);
            Integer completionCount = completionDAO.countCompletion(userId);
            Integer singleCount = singleSelectDAO.countSingleSelect(userId);
            Integer multiCount = multiSelectDAO.countMultiSelect(userId);
            Integer shortCount = shortAnswerDAO.countShortAnswer(userId);
            Map<String, Object> payload = new HashMap<>();
            payload.put("profile", profile);
            payload.put("checkCount", checkCount);
            payload.put("completionCount", completionCount);
            payload.put("singleCount", singleCount);
            payload.put("multiCount", multiCount);
            payload.put("shortCount", shortCount);
            res.setPayload(payload);
            res.setMessage(Message.SUCCESS);
//        } catch (Exception e) {
//            e.printStackTrace();
//            res.setMessage(Message.FAILURE);
//        }
        return res;
    }

    public Profile getProfileByUserId(Integer userId) {
        return profileDAO.getProfileByUserId(userId);
    }

    @Override
    public Response addAccount(String username, MultipartHttpServletRequest mRequest) {

        Response res = new Response();
        /* Check if this account already exists. */
        if(accountDAO.getAccountByUsername(username) != null){
            res.setMessage(Message.DUPLICATE_USER);
            return res;
        }
        String password = mRequest.getParameter("password");
        String nickname = mRequest.getParameter("nickname");
        String description = mRequest.getParameter("description");

        // Wrap up an account object
        final Long now = System.currentTimeMillis();
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setCtime(now);
        account.setUtime(now);
        account.setLocked(BaseConfig.ACCOUNT_NORMAL);
        account.setRole(BaseConfig.ROLE_USER);
        account.setStatus(Status.NORMAL.getCode());

        // Wrap up a profile object
        Profile profile = new Profile();
        profile.setNickname(nickname);
        profile.setDescription(description);
        profile.setCtime(now);
        profile.setUtime(now);

        // create the account and profile
        Account ret = createAccountInternal(account, profile);
//        if (ret == null) {
//            res.setMessage(Message.DATABASE);
//            return res;
//        }
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
//        try {
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
            profileDAO.createProfile(p);

            /* Set the successful response */
            return createdAcct;
//        } catch (Exception e) {
//            return null;
//        }
    }

    @Override
    public Response updateProfile(Integer userId, MultipartHttpServletRequest mRequest) {
        Response res = new Response();
        try {
            String nickname = mRequest.getParameter("nickname");
            String description = mRequest.getParameter("description");
            Profile profile = new Profile();
            profile.setUserId(userId);
            profile.setNickname(nickname);
            profile.setDescription(description);
            Integer success = profileDAO.updateProfile(profile);
            if (success == 0) {
                res.setMessage(Message.DATABASE);
                return res;
            }
            res.setMessage(Message.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.CATCH);
        }
        return res;
    }

    @Override
    public Response changePassword(Integer userId, String oldPassword, String newPassword) {
        Response res = new Response();

        Account account = accountDAO.getAccountByUserId(userId);
        if (account == null) {
            res.setMessage(Message.USER_NOT_EXIST);
            return res;
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
    public Response resetPassword(String username, String password) {
        Response res = new Response();
        try {
            Account account = accountDAO.getAccountByUsername(username);
            if (account == null) {
                res.setMessage(Message.USER_NOT_EXIST);
                return res;
            }
            Integer userId = account.getUserId();
            Integer success = accountDAO.updatePassword(userId, password);
            if (success == 0) {
                res.setMessage(Message.FAILURE);
            } else {
                res.setMessage(Message.SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.CATCH);
        }
        return res;
    }

    @Override
    public Response deleteAccount(String username) {
        Response res = new Response();
        try {
            Account account = accountDAO.getAccountByUsername(username);
            Integer userId = account.getUserId();
            profileDAO.deleteProfile(userId);
            accountDAO.deleteAccount(userId);
            res.setMessage(Message.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            res.setMessage(Message.CATCH);
        }
        return res;
    }
}