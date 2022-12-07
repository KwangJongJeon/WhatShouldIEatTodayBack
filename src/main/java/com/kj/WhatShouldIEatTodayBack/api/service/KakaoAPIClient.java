package com.kj.WhatShouldIEatTodayBack.api.service;

import com.kj.WhatShouldIEatTodayBack.Exception.KakaoServerIsNotRespondException;
import com.kj.WhatShouldIEatTodayBack.dto.api.SearchLocalAPIReq;
import com.kj.WhatShouldIEatTodayBack.dto.api.SearchLocalAPIRes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * 카카오 API에 데이터를 요청하는 클라이언트 객체

 *  @author  Jeon KwangJong
 *  @since   0.1
 */
@Component
public class KakaoAPIClient {

    @Value("${kakao.secret.restAPIKey}")
    private String kakaoRestAPIKey;

    @Value("${kakao.url.search.local}")
    public String kakaoLocalSearchUrl;

    /**
     * 카카오 API에 데이터를 요청하는 클라이언트 객체
     *
     * @param searchLocalAPIReq 프론트에서 넘어온 userData
     * @exception KakaoServerIsNotRespondException 카카오 서버가 응답하지 않을 경우 발생하는 Exception
     * @return SearchLocalAPIRes 카카오 API로부터 받은 데이터를 객체에 넣은 것
     */
    public SearchLocalAPIRes localSearch(SearchLocalAPIReq searchLocalAPIReq) throws KakaoServerIsNotRespondException {

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

        ResponseEntity<SearchLocalAPIRes> responseEntity = new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType
        );

        // 카카오 서버가 응답하지 않을경우 KakaoServerIsNotRespondException 발행
        if(responseEntity.getStatusCode().is5xxServerError()) {
            throw new KakaoServerIsNotRespondException("카카오 서버가 응답하지 않습니다.");
        }

        // 클라이언트의 네트워크에 문제가 있을 경우에도 KakaoServerIsNotRespondExcetpion 발행
        if(responseEntity.getStatusCode().is4xxClientError()) {
            throw new KakaoServerIsNotRespondException("백앤드 서버의 네트워크에 문제가 있습니다.");
        }

        return responseEntity.getBody();
    }
}
