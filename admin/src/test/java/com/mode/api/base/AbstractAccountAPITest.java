package com.mode.api.base;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mode.dao.AccountDAO;
import com.mode.entity.Account;
import com.mode.init.TestDAOConfig;
import com.mode.init.TestMvcConfig;
import com.mode.init.TestMybatisConfig;
import com.mode.init.TestSecurityConfig;
import com.mode.init.TestServiceConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TestMvcConfig.class, TestMybatisConfig.class, TestSecurityConfig
        .class, TestServiceConfig.class, TestDAOConfig.class})
public abstract class AbstractAccountAPITest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    protected AccountDAO accountDAO;

    protected MockMvc mockMvc;

    protected static final String USERNAME = "mode123456";
    protected static final String PASSWORD = "mode123456";
    protected static final Integer STARUS = 1;
    protected static final Integer LOCKED = 0;
    protected static final Long CTIME = 1443509325329l;
    protected static final Long UTIME = 1443509325329l;
    protected static final String ROLE = "USER";
    protected static final String ACCESSTOKEN = "CZY";
    protected static final Long EXPIRE_TIME = 1443509325329l;

    protected static final String URL = "/v2/login/";

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected Account getAccount() {
        Account account = new Account();

        account.setUsername(USERNAME);
        account.setPassword(PASSWORD);
        account.setCtime(CTIME);
        account.setUtime(UTIME);
        account.setLocked(LOCKED);
        account.setAccessToken(ACCESSTOKEN);
        account.setRole(ROLE);
        account.setStatus(STARUS);
        account.setExpireTime(EXPIRE_TIME);

        return account;
    }

}
