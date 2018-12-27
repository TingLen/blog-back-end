package com.pdl.blog;

import com.pdl.blog.controller.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Request;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginTest {
    @Autowired
    private UserController userController;
    private MockMvc mvc;
    @Before
    public void setMockMvc(){
        mvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void loginTestFalse() throws Exception {
        RequestBuilder request = post("/user/login")
                .contentType("application/json")
                .content("{\"id\":\"1\",\"username\":\"admin\",\"password\":\"root\"}");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"success\":false,\"message\":\"账号或密码错误\"}")));
    }

    @Test
    public void loginTestSuccess() throws Exception {
        RequestBuilder request = post("/user/login")
                .contentType("application/json")
                .content("{\"id\":\"1\",\"username\":\"dinglinloves\",\"password\":\"linyan1211\"}");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"success\":true,\"message\":\"登录成功\"}")));
    }

}
