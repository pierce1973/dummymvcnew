package com.example.restmvc.restmvcdemo.controller;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.notification.RunListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.Base64Utils;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Ignore
    public void getHello() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/").
                accept(MediaType.APPLICATION_JSON).param("name", "Joe")).
                andExpect(status().isOk());
    }

    @Test
    public void whenRootPathThenVerifyAuthenticationRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/")).
                andExpect(status().is(401));
    }

    @Test
    public void whenPostSearchAndAuthorizedThenVerifyRedirect() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST,
                "/postSearch").param("search", "search")
                .header(HttpHeaders.AUTHORIZATION,
                        "Basic " + Base64Utils.encodeToString("dummy:dummy".getBytes())))
                .andExpect(redirectedUrl("result?search=search"));
    }

    @Test
    public void whenCalledWithAuthenticationThenVerifyModelParametersAndView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET, "/result")
        .header(HttpHeaders.AUTHORIZATION, "Basic "
        + Base64Utils.encodeToString("dummy:dummy".getBytes()))
        .param("search", "1"))
                .andExpect(view().name("resultPage"))
                .andExpect(model().attribute("searchId", "1"));
    }
}
