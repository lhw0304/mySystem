package com.mode.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.mode.Application;
import com.mode.entity.Account;

/**
 * Created by zhaoweiwei on 16/3/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class AccountDAOTest {

    @Autowired
    private AccountDAO accountDAO;

//    @Test
//    public void testCreateAccount() {
//        long now = System.currentTimeMillis();
//        Account account = new Account();
//        account.setActivateKey(12345);
//        account.setCtime(now);
//        account.setEmail("testdao@gmail.com");
//        account.setExpireTime(now);
//        account.setMobile("12345678901");
//        account.setPassword("12345");
//        account.setResetPasswordKey(12345);
//        account.setRole("user");
//        account.setSource("mode");
//        account.setStatus(1);
//        account.setUtime(now);
//        account.setUsername("testdao");
//        account.setAccessToken("eyJ1c2VySWQiOjAsInVzZXJuYW1lIjoiQW5raXQubmlyYWxhMTQzQGdtYWlsLmNvbSIsInRva2VuIjpudWxsLCJleHBpcmVzIjoxNDY2OTIwODIzMDc1LCJyb2xlIjoiVVNFUiJ9");
//        System.out.println
//                ("eyJ1c2VySWQiOjAsInVzZXJuYW1lIjoiQW5raXQubmlyYWxhMTQzQGdtYWlsLmNvbSIsInRva2VuIjpudWxsLCJleHBpcmVzIjoxNDY2OTIwODIzMDc1LCJyb2xlIjoiVVNFUiJ9".length());
//        accountDAO.createAccount(account);
//        System.out.println(account.getId());
//    }

    @Test
    public void testGetAccount() {
        long now = System.currentTimeMillis();
        Account account = new Account();
        account.setUsername("peter1");
        account.setEmail("testdao@gmail.com");
//        account.setMobile("12345678901");
//        account.setId(1);
        account.setUtime(now);
        Account user = accountDAO.getAccount(account);
        System.out.println("user_id = " + user.getId());
    }




}
