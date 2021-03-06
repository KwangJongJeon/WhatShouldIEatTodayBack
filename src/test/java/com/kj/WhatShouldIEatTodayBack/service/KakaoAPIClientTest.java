//package com.kj.WhatShouldIEatTodayBack.service;
//
//import com.kj.WhatShouldIEatTodayBack.Exception.KakaoServerIsNotRespondException;
//import com.kj.WhatShouldIEatTodayBack.dto.SearchLocalAPIReq;
//import com.kj.WhatShouldIEatTodayBack.dto.SearchLocalAPIRes;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//class KakaoAPIClientTest {
//
//    @Autowired
//    KakaoAPIClient kakaoAPIClient;
//
//    String query = "한식";
//    String latitude = "36.3376482";
//    String longitude = "127.3933957";
//    String radius = "2000";
//
//    SearchLocalAPIReq searchLocalAPIReq = new SearchLocalAPIReq(query, latitude, longitude, radius);
//
//    @DisplayName("API로부터 객체에 값을 저장 할 수 있다.")
//    @Test
//    void sendToKakaoAPIProperly() {
//
//        SearchLocalAPIRes responseData = kakaoAPIClient.localSearch(searchLocalAPIReq);
//
//        Assertions.assertThat(responseData.getDocuments()).isNotNull();
//        Assertions.assertThat(responseData.getMeta()).isNotNull();
//
//    }
//}