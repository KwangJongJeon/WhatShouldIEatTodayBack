package com.kj.WhatShouldIEatTodayBack.dto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class SearchLocalAPIReqTest {

    @Autowired
    MockMvc mockMvc;

    @Value("${kakao.secret.restAPIKey}")
    private String kakaoRestAPIKey;

    @Value("${kakao.url.search.local}")
    private String kakaoLocalSearchUrl;


    @DisplayName("Dto가 카카오 API가 요구하는 양식을 충족한다.")
    @Test
    void checkRequestIsValid() {
        // given
        String query = "한식";
        String latitude = "36.3376482";
        String longitude = "127.3933957";
        String radius = "2000";

        SearchLocalAPIReq searchLocalAPIReq = new SearchLocalAPIReq(query, latitude, longitude, radius);
        URI uri = UriComponentsBuilder
                .fromUriString(kakaoLocalSearchUrl)
                .queryParams(searchLocalAPIReq.makeMultiValueMap())
                .build()
                .encode()
                .toUri();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", kakaoRestAPIKey);

        HttpEntity<Object> httpEntity = new HttpEntity<>(httpHeaders);
        ParameterizedTypeReference<SearchLocalAPIRes> responseType = new ParameterizedTypeReference<>() {};

        // when
        ResponseEntity<SearchLocalAPIRes> responseEntity = new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType
        );

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}