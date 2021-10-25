package com.kj.WhatShouldIEatTodayBack.api.controller;

import com.kj.WhatShouldIEatTodayBack.dto.RecommendServiceReq;
import com.kj.WhatShouldIEatTodayBack.dto.ResponseDocument;
import com.kj.WhatShouldIEatTodayBack.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping(value = "/api")
@RestController
public class RecommendationController {

    private final RecommendationService recommendationService;


    @RequestMapping(value = "/recommendation")
    public ResponseDocument recommendation(@RequestBody RecommendServiceReq userData) {

        return new ResponseDocument();
    }

}
