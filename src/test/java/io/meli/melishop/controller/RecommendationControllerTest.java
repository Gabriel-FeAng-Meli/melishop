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
    void testRecommend() throws Exception {

        String expectedResponse = mapper.writeValueAsString(Map.of("Recommended products for you, considering HISTORY", List.of("paper","apple","banana")));

        MockHttpServletResponse response = mvc.perform(get("/recommendation/{id}", "1").queryParam("strategy", "HISTORY").accept(MediaType.APPLICATION_JSON)).getResponse();

        String responseContent = response.getContentAsString();

        assertEquals(expectedResponse, responseContent);
        assertEquals(200, response.getStatus());

    }
}
