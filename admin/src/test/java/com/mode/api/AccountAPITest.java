package com.mode.api;


import org.junit.Test;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.mode.api.base.AbstractAccountAPITest;
import com.mode.entity.Account;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AccountAPITest extends AbstractAccountAPITest {

    @Test
    public void authenticateTest() throws Exception {
        Account account = getAccount();
        accountDAO.createAccount(account);

        mockMvc.perform(post(URL).header("username", USERNAME)
                .header("password", PASSWORD))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message.code").value(0))
                .andDo(MockMvcResultHandlers.print());
    }
}