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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {MvcConfig.class, MybatisConfig.class, SecurityConfig.class,
        ServiceConfig.class})
public class ItemAPITest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getItemTest() throws Exception {
        mockMvc.perform(get("/v2/items/11400"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message.code").value(0))
                .andExpect(jsonPath("$.payload.item", hasKey("itemId")))
                .andExpect(jsonPath("$.payload.item", hasKey("itemName")))
                .andExpect(jsonPath("$.payload.item", hasKey("merchantId")))
                .andExpect(jsonPath("$.payload.item", hasKey("brandId")))
                .andExpect(jsonPath("$.payload.item", hasKey("onsale")))
                .andExpect(jsonPath("$.payload.item", hasKey("status")))
                .andExpect(jsonPath("$.payload.item", hasKey("ctime")))
                .andExpect(jsonPath("$.payload.item", hasKey("utime")))
                .andExpect(jsonPath("$.payload.item", hasKey("saleTime")))
                .andExpect(jsonPath("$.payload.item", hasKey("defaultImage")))
                .andExpect(jsonPath("$.payload.item", hasKey("subImage")))
                .andExpect(jsonPath("$.payload.item", hasKey("extraImage1")))
                .andExpect(jsonPath("$.payload.item", hasKey("extraImage2")))
                .andExpect(jsonPath("$.payload.item", hasKey("price")))
                .andExpect(jsonPath("$.payload.item", hasKey("moreProperty")))
                .andExpect(jsonPath("$.payload.item", hasKey("sku")))
                .andExpect(jsonPath("$.payload.item", hasKey("title")))
                .andExpect(jsonPath("$.payload.item", hasKey("description")))
                .andExpect(jsonPath("$.payload.item", hasKey("size")))
                .andExpect(jsonPath("$.payload.item", hasKey("color")))
                .andExpect(jsonPath("$.payload.item", hasKey("productLink")))
                .andExpect(jsonPath("$.payload.item", hasKey("expirePeriod")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void listItemsTest() throws Exception {
        mockMvc.perform(get("/v2/items").param
                ("merchantId", "30000").param("limit", "10").param("offset", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message.code").value(0))
                .andExpect(jsonPath("$.payload.items").isArray())
                .andExpect(jsonPath("$.payload.items[0]", hasKey("itemId")))
                .andExpect(jsonPath("$.payload.items[0]", hasKey("itemName")))
                .andExpect(jsonPath("$.payload.items[0]", hasKey("merchantId")))
                .andExpect(jsonPath("$.payload.items[0]", hasKey("brandId")))
                .andExpect(jsonPath("$.payload.items[0]", hasKey("onsale")))
                .andExpect(jsonPath("$.payload.items[0]", hasKey("status")))
                .andExpect(jsonPath("$.payload.items[0]", hasKey("ctime")))
                .andExpect(jsonPath("$.payload.items[0]", hasKey("utime")))
                .andExpect(jsonPath("$.payload.items[0]", hasKey("saleTime")))
                .andExpect(jsonPath("$.payload.items[0]", hasKey("defaultImage")))
                .andExpect(jsonPath("$.payload.items[0]", hasKey("subImage")))
                .andExpect(jsonPath("$.payload.items[0]", hasKey("extraImage1")))
                .andExpect(jsonPath("$.payload.items[0]", hasKey("extraImage2")))
                .andExpect(jsonPath("$.payload.items[0]", hasKey("price")))
                .andExpect(jsonPath("$.payload.items[0]", hasKey("moreProperty")))
                .andExpect(jsonPath("$.payload.items[0]", hasKey("sku")))
                .andExpect(jsonPath("$.payload.items[0]", hasKey("title")))
                .andExpect(jsonPath("$.payload.items[0]", hasKey("description")))
                .andExpect(jsonPath("$.payload.items[0]", hasKey("size")))
                .andExpect(jsonPath("$.payload.items[0]", hasKey("color")))
                .andExpect(jsonPath("$.payload.items[0]", hasKey("productLink")))
                .andExpect(jsonPath("$.payload.items[0]", hasKey("expirePeriod")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void createItemHeadlineTest() throws Exception{
        mockMvc.perform(post("/v2/items/headlines").param("title", "A test title.").param
                ("itemId", "7").param("startTime", "1433817887000").param("endTime",
                "1438829011587").param("sortOrder", "40").param("countries","us-th"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message.code").value(0))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateItemHeadlineTest() throws Exception{
        mockMvc.perform(post("/v2/items/headlines/71").param("title", "A test title.").param
                ("itemId", "11400").param("startTime", "1433817887000").param("endTime",
                "1438829011587").param("sortOrder", "100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message.code").value(0))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateItem() throws Exception{
        mockMvc.perform(post("/v2/items/7").param("status","1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message.code").value(0))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteItemTest() throws Exception{
        mockMvc.perform(delete("/v2/items").header("itemIds","11-12-13"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message.code").value(0))
                .andDo(MockMvcResultHandlers.print());
    }
}
