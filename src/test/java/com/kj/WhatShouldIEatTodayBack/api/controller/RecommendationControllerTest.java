package com.kj.WhatShouldIEatTodayBack.api.controller;

import com.kj.WhatShouldIEatTodayBack.dto.RecommendServiceReq;
import com.kj.WhatShouldIEatTodayBack.dto.ResponseDocument;
import com.kj.WhatShouldIEatTodayBack.service.RecommendationService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebAppConfiguration
@AutoConfigureMockMvc
@SpringBootTest
class RecommendationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    RecommendationService recommendationService;

    @Autowired
    RecommendationController recommendationController;

    @Test
    @DisplayName("컨트롤러에 유저 데이터가 정상적으로 들어오지 않았을 경우 400 상태이상 코드를 리턴한다.")
    void reqDtoExceptionHandling2() throws Exception {
        String failedUserData = "\"latitude\": \"36.342038853926766\",\n" +
                "\"longitude\": \"127.38479286399112\",\n" +
                "\"categories\": \"[koreanFood, japaneseFood]\"";

        ResultActions resultActions = mockMvc.perform(
                get("/api/recommendation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(failedUserData))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @DisplayName("정상적인 값이 들어올 경우 값을 반환한다.")
    void controllerFunctionTest() {
        // given
        RecommendServiceReq recommendServiceReq = new RecommendServiceReq();

        ArrayList<String> categoriesData = new ArrayList<>();
        categoriesData.add("KoreanFood");
        categoriesData.add("JapaneseFood");

        recommendServiceReq.setCategories(categoriesData);
        recommendServiceReq.setLatitude("36.342038853926766");
        recommendServiceReq.setLongitude("127.38479286399112");

        recommendServiceReq.setRange("1000");

        // when
        ResponseDocument result = recommendationController.recommendation(recommendServiceReq);

        // then
        Assertions.assertThat(result).isNotNull();

    }
}