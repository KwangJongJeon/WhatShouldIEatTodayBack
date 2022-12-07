package com.kj.WhatShouldIEatTodayBack.api.controller;

import com.kj.WhatShouldIEatTodayBack.dto.api.RecommendServiceReq;
import com.kj.WhatShouldIEatTodayBack.dto.api.ResponseDocument;
import com.kj.WhatShouldIEatTodayBack.api.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 식사 추천기능을 하는 API Controller
 * @author KwangJong Jeon
 * @since 0.1
 */
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/api")
@RestController
public class RecommendationAPIController {

    private final RecommendationService recommendationService;


    /**
     * @param userData 프론트앤드에서 받아오는 데이터 값
     * @return ResponseDocument - 카카오 API에서 데이터를 받아 추천이 완료된 데이터를 보냅니다.
     */
    @RequestMapping(value = "/recommendation")
    public ResponseDocument recommendation(@RequestBody RecommendServiceReq userData)  {
        log.info(userData.toString());
        log.info("Recommendation Request is arrived");
        log.info("latitude: " + userData.getLatitude());
        return recommendationService.recommendationService(userData);
    }

}
