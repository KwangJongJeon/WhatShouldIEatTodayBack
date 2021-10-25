package com.kj.WhatShouldIEatTodayBack.service;

import com.kj.WhatShouldIEatTodayBack.dto.RecommendServiceReq;
import com.kj.WhatShouldIEatTodayBack.dto.ResponseDocument;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationService {

    public ResponseDocument recommendationService(RecommendServiceReq recommendServiceReq) {
        return new ResponseDocument();
    }

    private String selectCategoryRandomly(List<String> categories) {
        return "";
    }
}
