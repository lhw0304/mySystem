package com.mode.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mode.init.MvcConfig;
import com.mode.init.MybatisConfig;
import com.mode.init.SecurityConfig;
import com.mode.init.ServiceConfig;

import static org.hamcrest.Matchers.hasKey;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {MvcConfig.class, MybatisConfig.class, SecurityConfig.class,
        ServiceConfig.class})
public class BrandAPITest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void listUserBrandTest() throws Exception {
        mockMvc.perform(get("/v2/brands"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message.code").value(0))
                .andExpect(jsonPath("$.payload.brands").isArray())
                .andExpect(jsonPath("$.payload.brands[0]", hasKey("brandName")))
                .andExpect(jsonPath("$.payload.brands[0]", hasKey("brandCname")))
                .andExpect(jsonPath("$.payload.brands[0]", hasKey("brandEname")))
                .andExpect(jsonPath("$.payload.brands[0]", hasKey("brandLogo")))
                .andExpect(jsonPath("$.payload.brands[0]", hasKey("brandTitle")))
                .andExpect(jsonPath("$.payload.brands[0]", hasKey("description")))
                .andExpect(jsonPath("$.payload.brands[0]", hasKey("merchantId")))
                .andExpect(jsonPath("$.payload.brands[0]", hasKey("sortOrder")))
                .andExpect(jsonPath("$.payload.brands[0]", hasKey("status")))
                .andExpect(jsonPath("$.payload.brands[0]", hasKey("likes")))
                .andExpect(jsonPath("$.payload.brands[0]", hasKey("ctime")))
                .andExpect(jsonPath("$.payload.brands[0]", hasKey("utime")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateBrandTest() throws Exception{
        mockMvc.perform(post("/v2/brands/2").param("status", "6"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message.code").value(0))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteBrandTest() throws Exception{
        mockMvc.perform(delete("/v2/brands/999"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message.code").value(0))
                .andDo(MockMvcResultHandlers.print());
    }
}
