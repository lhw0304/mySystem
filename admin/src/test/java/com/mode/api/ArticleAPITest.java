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

import static org.hamcrest.Matchers.hasKey;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
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
public class ArticleAPITest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void createArticleHeadlineTest() throws Exception{
        InputStream is = new FileInputStream(new File("/tmp/test.jpg"));
        MockMultipartFile coverImage = new MockMultipartFile("defaultImage", is);
        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/v2/articles/headlines").file
                (coverImage).param("title", "A test title.").param("author", "By LC").param
                ("content", "<p>Test content</p>").param("relatedItems", "11485-11486-11487-11488").param
                ("sortOrder", "50").param("defaultImageCropper","1-1-10-10").param("language",
                "en").param("type","json").param("countries","en"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message.code").value(0))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getArticleTest() throws Exception{
        mockMvc.perform(get("/v2/articles/38"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message.code").value(0))
                .andExpect(jsonPath("$.payload", hasKey("utime")))
                .andExpect(jsonPath("$.payload", hasKey("author")))
                .andExpect(jsonPath("$.payload", hasKey("articleId")))
                .andExpect(jsonPath("$.payload", hasKey("relatedItems")))
                .andExpect(jsonPath("$.payload", hasKey("language")))
                .andExpect(jsonPath("$.payload", hasKey("title")))
                .andExpect(jsonPath("$.payload", hasKey("type")))
                .andExpect(jsonPath("$.payload", hasKey("content")))
                .andExpect(jsonPath("$.payload", hasKey("shares")))
                .andExpect(jsonPath("$.payload", hasKey("coverImage")))
                .andExpect(jsonPath("$.payload", hasKey("ctime")))
                .andExpect(jsonPath("$.payload", hasKey("items")))
                .andExpect(jsonPath("$.payload", hasKey("views")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateArticleTest() throws Exception{
        InputStream is = new FileInputStream(new File("/tmp/test.jpg"));
        MockMultipartFile coverImage = new MockMultipartFile("defaultImage", is);
         mockMvc.perform(MockMvcRequestBuilders.fileUpload("/v2/articles/headlines/110").file
                 (coverImage).param("title", "A test title.").param("author", "By LC").param
                 ("content", "<p>Test content</p>").param("relatedItems", "11485-11486-11487-11488").param
                 ("sortOrder", "50").param("defaultImageCropper","1-1-10-10").param("language",
                 "en").param("type","json").param("countries","en"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message.code").value(0))
                .andDo(MockMvcResultHandlers.print());
    }
}
