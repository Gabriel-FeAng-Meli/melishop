package io.meli.melishop.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.List;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
public class RecommendationControllerTest {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private MockMvcTester mvc;

    @Test
    void testRecommend_HISTORY_USER_1() throws Exception {

        String expectedResponse = mapper.writeValueAsString(Map.of("Recommended products for you, considering HISTORY", List.of("paper","apple","banana")));

        MockHttpServletResponse response = mvc.perform(get("/recommendation/{id}", "1").queryParam("strategy", "HISTORY").accept(MediaType.APPLICATION_JSON)).getResponse();

        String responseContent = response.getContentAsString();

        assertEquals(expectedResponse, responseContent);
        assertEquals(200, response.getStatus());

    }


    @Test
    void testRecommend_HISTORY_USER_2() throws Exception {

        String expectedResponse = mapper.writeValueAsString(Map.of("Recommended products for you, considering HISTORY", List.of("rice","banana","milk")));

        MockHttpServletResponse response = mvc.perform(get("/recommendation/{id}", "2").queryParam("strategy", "HISTORY").accept(MediaType.APPLICATION_JSON)).getResponse();

        String responseContent = response.getContentAsString();

        assertEquals(expectedResponse, responseContent);
        assertEquals(200, response.getStatus());

    }

    @Test
    void testRecommend_HISTORY_USER_3() throws Exception {

        String expectedResponse = mapper.writeValueAsString(Map.of("Recommended products for you, considering HISTORY", List.of("milk","rice","banana")));

        MockHttpServletResponse response = mvc.perform(get("/recommendation/{id}", "3").queryParam("strategy", "HISTORY").accept(MediaType.APPLICATION_JSON)).getResponse();

        String responseContent = response.getContentAsString();

        assertEquals(expectedResponse, responseContent);
        assertEquals(200, response.getStatus());

    }

    @Test
    void testRecommend_POPULARITY() throws Exception {

        String expectedResponse = mapper.writeValueAsString(Map.of("Recommended products for you, considering POPULARITY", List.of("paper","rice","milk")));

        MockHttpServletResponse response = mvc.perform(get("/recommendation/{id}", "1").queryParam("strategy", "POPULARITY").accept(MediaType.APPLICATION_JSON)).getResponse();

        String responseContent = response.getContentAsString();

        assertEquals(expectedResponse, responseContent);
        assertEquals(200, response.getStatus());

    }

    @Test
    void testRecommend_SIMILARITY() throws Exception {

        String expectedResponse = mapper.writeValueAsString(Map.of("Recommended products for you, considering SIMILARITY", List.of("rice","banana","milk")));

        MockHttpServletResponse response = mvc.perform(get("/recommendation/{id}", "1").queryParam("strategy", "SIMILARITY").accept(MediaType.APPLICATION_JSON)).getResponse();

        String responseContent = response.getContentAsString();

        assertEquals(expectedResponse, responseContent);
        assertEquals(200, response.getStatus());

    }

    @Test
    void testRecommend_NON_EXISTENT_USER() throws Exception {

        MockHttpServletResponse response = mvc.perform(get("/recommendation/{id}", "7").queryParam("strategy", "").accept(MediaType.APPLICATION_JSON)).getResponse();

        assertEquals(400, response.getStatus());

    }

    @Test
    void testRecommend_EMPTY_STRING_STRATEGY() throws Exception {

        MockHttpServletResponse response = mvc.perform(get("/recommendation/{id}", "1").queryParam("strategy", "").accept(MediaType.APPLICATION_JSON)).getResponse();

        assertEquals(400, response.getStatus());

    }

    @Test
    void testRecommend_NULL_STRATEGY() throws Exception {

        MockHttpServletResponse response = mvc.perform(get("/recommendation/{id}", "1").accept(MediaType.APPLICATION_JSON)).getResponse();

        assertEquals(400, response.getStatus());

    }
}
