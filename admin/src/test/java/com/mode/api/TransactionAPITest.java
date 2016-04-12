package com.mode.api;

import com.mode.init.MvcConfig;
import com.mode.init.MybatisConfig;
import com.mode.init.SecurityConfig;
import com.mode.init.ServiceConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasKey;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by wangkang on 2015/9/22.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {MvcConfig.class, MybatisConfig.class, SecurityConfig.class,
        ServiceConfig.class})
public class TransactionAPITest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void listTransactionsTest() throws Exception{
        mockMvc.perform(get("/v2/transactions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message.code").value(0))
                .andExpect(jsonPath("$.payload.transactions").isArray())
                .andExpect(jsonPath("$.payload.transactions[0]", hasKey("transactionId")))
                .andExpect(jsonPath("$.payload.transactions[0]", hasKey("userId")))
                .andExpect(jsonPath("$.payload.transactions[0]", hasKey("amount")))
                .andExpect(jsonPath("$.payload.transactions[0]", hasKey("unit")))
                .andExpect(jsonPath("$.payload.transactions[0]", hasKey("ctime")))
                .andExpect(jsonPath("$.payload.transactions[0]", hasKey("utime")))
                .andExpect(jsonPath("$.payload.transactions[0]", hasKey("status")))
                .andExpect(jsonPath("$.payload.transactions[0]", hasKey("formerAmount")))
                .andExpect(jsonPath("$.payload.transactions[0]", hasKey("sn")))
                .andExpect(jsonPath("$.payload.transactions[0]", hasKey("comment")))
                .andExpect(jsonPath("$.payload.transactions[0]", hasKey("transactionName")))
                .andExpect(jsonPath("$.payload.transactions[0]", hasKey("transactionNumber")))
                .andExpect(jsonPath("$.payload.transactions[0]", hasKey("transactionDescription")))
                .andExpect(jsonPath("$.payload.transactions[0]", hasKey("alipayAcount")))
                .andExpect(jsonPath("$.payload.transactions[0]", hasKey("type")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateTransactionTest() throws Exception{
        mockMvc.perform(post("/v2/transactions/45").param("status","204").param("userId",
                "30257").param("amount","1000").param("merchantId","30000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message.code").value(0))
                .andDo(MockMvcResultHandlers.print());
    }
}
