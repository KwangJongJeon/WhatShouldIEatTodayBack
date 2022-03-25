//package com.kj.WhatShouldIEatTodayBack.service;
//
//import com.kj.WhatShouldIEatTodayBack.dto.RecommendServiceReq;
//import com.kj.WhatShouldIEatTodayBack.dto.ResponseDocument;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class RecommendationServiceTest {
//
//    @Autowired
//    RecommendationService recommendationService;
//
//    @Test
//    @DisplayName("ResponseDocument 타입의 데이터 '하나'가 정상적으로 리턴된다.")
//    void recommendationService() {
//        // given
//        RecommendServiceReq recommendServiceReq = new RecommendServiceReq();
//
//        ArrayList<String> categoriesData = new ArrayList<>();
//        categoriesData.add("한식");
//        categoriesData.add("일식");
//
//        recommendServiceReq.setCategories(categoriesData);
//        recommendServiceReq.setLatitude("36.342038853926766");
//        recommendServiceReq.setLongitude("127.38479286399112");
//
//        recommendServiceReq.setRange("1000");
//
//        ResponseDocument responseDocument = recommendationService.recommendationService(recommendServiceReq);
//
//        Assertions.assertThat(responseDocument).isNotNull();
//        Assertions.assertThat(responseDocument).isInstanceOf(ResponseDocument.class);
//        Assertions.assertThat(Integer.valueOf(responseDocument.getId())).isNotNull();
//    }
//}