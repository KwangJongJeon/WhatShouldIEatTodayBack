package com.kj.WhatShouldIEatTodayBack.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebAppConfiguration
@SpringBootTest
class ExceptionControllerTest  {

    MockMvc mockMvc;

    @Autowired RecommendationController recommendationController;


    @BeforeEach
    void beforeEach() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(recommendationController)
                .setControllerAdvice(new ExceptionController())
                .build();
    }

    @DisplayName("카테고리에 ")
    @Test
    void exceptionHandlingTest() throws Exception {


        String userData = "{\n" +
                "\"latitude\": \"36.342038853926766\",\n" +
                "\"longitude\": \"127.38479286399112\",\n" +
                "\"range\": \"1000\", \n" +
                "\"categories\": [\"KoreanFood\", \"JapaneseFood\", \"InvalidCategory\"]\n" +
                "}";


        mockMvc.perform(
                get("/api/recommendation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userData))
                .andExpect(status().is4xxClientError())
                .andDo(print());

    }



}