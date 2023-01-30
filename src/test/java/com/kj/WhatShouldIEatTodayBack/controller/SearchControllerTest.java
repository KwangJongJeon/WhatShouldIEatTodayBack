package com.kj.WhatShouldIEatTodayBack.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class SearchControllerTest {

    @Autowired private MockMvc mvc;


    @Test
    void searchStoreForm() throws Exception {
        mvc.perform(get("/search/store"))
                .andExpect(status().isOk())
                .andExpect(view().name("page/search/store"))
                .andReturn();
    }

    @Test
    void searchStore() throws Exception {
        String keyword = "큰손";
        mvc.perform(post("/search/store/" + keyword))
                .andExpect(status().isOk())
                .andExpect(view().name("page/search/storeDetail"))
                .andReturn();
    }
}