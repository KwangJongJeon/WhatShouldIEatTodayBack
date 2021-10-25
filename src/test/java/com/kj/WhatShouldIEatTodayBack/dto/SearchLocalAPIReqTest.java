package com.kj.WhatShouldIEatTodayBack.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class SearchLocalAPIReqTest {

    @Autowired
    MockMvc mockMvc;

    @Value("${kakao.secret.restAPIKey}")
    private String kakaoRestAPIKey;

    @Value("${kakao.url.search.local}")
    private String kakaoLocalSearchUrl;


    @DisplayName("카카오 API가 요구하는 양식에 맞게 요청을 보냈다.")
    @Test
    void checkRequestIsValid() throws Exception {
        String query = "한식";
        String latitude = "36.3376482";
        String longitude = "127.3933957";
        String radius = "2000";

        SearchLocalAPIReq searchLocalAPIReq = new SearchLocalAPIReq(query, latitude, longitude, radius);
        URI uri = UriComponentsBuilder
                .fromUriString(kakaoLocalSearchUrl)
                .queryParam(searchLocalAPIReq.makeMultiValueMap())
                .build()
                .encode()
                .toUri();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", kakaoRestAPIKey);

        HttpEntity<Object> httpEntity = new HttpEntity<>(httpHeaders);

        mockMvc.perform(MockMvcRequestBuilders.get(uri).headers(httpHeaders)).andExpect(status().is2xxSuccessful());
    }

}