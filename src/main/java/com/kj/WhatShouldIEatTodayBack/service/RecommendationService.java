package com.kj.WhatShouldIEatTodayBack.service;

import com.kj.WhatShouldIEatTodayBack.dto.RecommendServiceReq;
import com.kj.WhatShouldIEatTodayBack.dto.ResponseDocument;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class RecommendationService {

    public ResponseDocument recommendationService(RecommendServiceReq recommendServiceReq) {
        return new ResponseDocument();
    }
}
