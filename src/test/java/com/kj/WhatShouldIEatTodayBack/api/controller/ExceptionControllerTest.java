package com.kj.WhatShouldIEatTodayBack.api.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.spy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@SpringBootTest
class ExceptionControllerTest  {

    @Autowired RecommendationController recommendationController;


    @Test
    @Disabled
    void exceptionHandlingTest() throws Exception {

        String userData = "\"latitude\": \"36.342038853926766\",\n" +
                "\"longitude\": \"127.38479286399112\",\n" +
                "\"range\": \"1000\", \n" +
                "\"categories\": \"[koreanFood, japaneseFood]\"";


        MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(recommendationController)
                .setControllerAdvice(new ExceptionController()).build();


        mockMvc.perform(
                get("/api/recommendation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userData))
                .andExpect(status().is5xxServerError())
                .andDo(print());

    }



}