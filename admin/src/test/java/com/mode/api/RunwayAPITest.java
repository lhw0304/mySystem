package com.mode.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasKey;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mode.init.MvcConfig;
import com.mode.init.MybatisConfig;
import com.mode.init.SecurityConfig;
import com.mode.init.ServiceConfig;

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
public class RunwayAPITest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void createRunwayHeadlineTest() throws Exception{
        InputStream is = new FileInputStream(new File("/tmp/test.jpg"));
        MockMultipartFile coverImage = new MockMultipartFile("defaultImage", is);
        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/v2/runways/headlines").file
                (coverImage).param("userId", "30033").param("brandId", "3").param("title", "Test " +
                "title").param("description", "Test runway description").param("itemIds",
                "11485-11486-11487-11488").param("sortOrder", "30").param("defaultImageCropper","1-1-10-10").param
                ("countries","ds"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.message.code").value(0))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateRunwayHeadlineTest() throws Exception{
        InputStream is = new FileInputStream(new File("/tmp/test.jpg"));

        MockMultipartFile coverImage = new MockMultipartFile("defaultImage", is);

        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/v2/runways/headlines/70").file
                (coverImage).param("userId", "30033").param("brandId", "3").param("title", "Test " +
                "title").param("description", "Test runway description").param("itemIds",
                "11485-11486-11487-11488").param("sortOrder", "80").param("defaultImageCropper",
                "1-1-10-10").param("countries","ds"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message.code").value(0))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getRunwayHeadlineTest() throws Exception{
        mockMvc.perform(get("/v2/runways/headlines/31"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message.code").value(0))
                .andExpect(jsonPath("$.payload.runway", hasKey("runwayId")))
                .andExpect(jsonPath("$.payload.runway", hasKey("userId")))
                .andExpect(jsonPath("$.payload.runway", hasKey("ctime")))
                .andExpect(jsonPath("$.payload.runway", hasKey("title")))
                .andExpect(jsonPath("$.payload.runway", hasKey("description")))
                .andExpect(jsonPath("$.payload.runway", hasKey("status")))
                .andExpect(jsonPath("$.payload.runway", hasKey("source")))
                .andExpect(jsonPath("$.payload.runway", hasKey("utime")))
                .andExpect(jsonPath("$.payload.runway", hasKey("brandId")))
                .andDo(MockMvcResultHandlers.print());
    }
}
