package com.kj.WhatShouldIEatTodayBack.service;

import com.kj.WhatShouldIEatTodayBack.dto.SearchLocalAPIReq;
import com.kj.WhatShouldIEatTodayBack.dto.SearchLocalAPIRes;
import org.springframework.beans.factory.annotation.Value;

public class KakaoAPIClient {

    @Value("${kakao.secret.restAPIKey}")
    private String kakaoRestAPIKey;

    @Value("${kakao.url.search.local}")
    private String kakaoLocalSearchUrl;

    public SearchLocalAPIRes localSearch(SearchLocalAPIReq searchLocalAPIReq) {
        return new SearchLocalAPIRes();
    }
}
