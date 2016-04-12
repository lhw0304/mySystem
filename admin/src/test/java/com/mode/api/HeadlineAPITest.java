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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



/**
 * Created by Lei on 8/7/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {MvcConfig.class, MybatisConfig.class, SecurityConfig.class,
        ServiceConfig.class})
public class HeadlineAPITest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void updateHeadlineTest() throws Exception{

        mockMvc.perform(post("/v2/headlines/71").param("onOff", "1").param("sortOrder", "33"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message.code").value(0))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void listHeadlinesTest() throws Exception{
        mockMvc.perform(get("/v2/headlines").param("limit", "10").param("offset", "5"))
        .andExpect(status().isOk())
                .andExpect(jsonPath("$.message.code").value(0))
                .andExpect(jsonPath("$.payload.headlines").isArray())
                .andExpect(jsonPath("$.payload.headlines[0]", hasKey("id")))
                .andExpect(jsonPath("$.payload.headlines[0]", hasKey("objectId")))
                .andExpect(jsonPath("$.payload.headlines[0]", hasKey("type")))
                .andExpect(jsonPath("$.payload.headlines[0]", hasKey("title")))
                .andExpect(jsonPath("$.payload.headlines[0]", hasKey("status")))
                .andExpect(jsonPath("$.payload.headlines[0]", hasKey("ctime")))
                .andExpect(jsonPath("$.payload.headlines[0]", hasKey("utime")))
                .andExpect(jsonPath("$.payload.headlines[0]", hasKey("sortOrder")))
                .andExpect(jsonPath("$.payload.headlines[0]", hasKey("defaultImage")))
                .andExpect(jsonPath("$.payload.headlines[0]", hasKey("discount")))
                .andExpect(jsonPath("$.payload.headlines[0]", hasKey("expireTime")))
                .andExpect(jsonPath("$.payload.headlines[0]", hasKey("brandLogo")))
                .andExpect(jsonPath("$.payload.headlines[0]", hasKey("item1Image")))
                .andExpect(jsonPath("$.payload.headlines[0]", hasKey("item2Image")))
                .andExpect(jsonPath("$.payload.headlines[0]", hasKey("item3Image")))
                .andExpect(jsonPath("$.payload.headlines[0]", hasKey("item4Image")))
                .andExpect(jsonPath("$.payload.headlines[0]", hasKey("availableCoupon")))
                .andExpect(jsonPath("$.payload.headlines[0]", hasKey("totalCoupon")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getHeadlineTest() throws Exception{
        mockMvc.perform(get("/v2/headlines/55"))
                .andExpect(jsonPath("$.message.code").value(0))
                .andExpect(jsonPath("$.payload.headline", hasKey("id")))
                .andExpect(jsonPath("$.payload.headline", hasKey("objectId")))
                .andExpect(jsonPath("$.payload.headline", hasKey("type")))
                .andExpect(jsonPath("$.payload.headline", hasKey("title")))
                .andExpect(jsonPath("$.payload.headline", hasKey("status")))
                .andExpect(jsonPath("$.payload.headline", hasKey("ctime")))
                .andExpect(jsonPath("$.payload.headline", hasKey("utime")))
                .andExpect(jsonPath("$.payload.headline", hasKey("sortOrder")))
                .andExpect(jsonPath("$.payload.headline", hasKey("defaultImage")))
                .andExpect(jsonPath("$.payload.headline", hasKey("discount")))
                .andExpect(jsonPath("$.payload.headline", hasKey("expireTime")))
                .andExpect(jsonPath("$.payload.headline", hasKey("brandLogo")))
                .andExpect(jsonPath("$.payload.headline", hasKey("item1Image")))
                .andExpect(jsonPath("$.payload.headline", hasKey("item2Image")))
                .andExpect(jsonPath("$.payload.headline", hasKey("item3Image")))
                .andExpect(jsonPath("$.payload.headline", hasKey("item4Image")))
                .andExpect(jsonPath("$.payload.headline", hasKey("availableCoupon")))
                .andExpect(jsonPath("$.payload.headline", hasKey("totalCoupon")))
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void deleteHeadlineTest() throws Exception{
        mockMvc.perform(delete("/v2/headlines/79"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message.code").value(0))
                .andDo(MockMvcResultHandlers.print());
    }
}