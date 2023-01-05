package com.kj.WhatShouldIEatTodayBack.service;

import com.kj.WhatShouldIEatTodayBack.controller.dto.RecommendationRequestDto;
import com.kj.WhatShouldIEatTodayBack.service.recommendation.CoordinateRange;
import com.kj.WhatShouldIEatTodayBack.service.recommendation.RecommendationResultDto;
import com.kj.WhatShouldIEatTodayBack.service.recommendation.RecommendationService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class RecommendationServiceTest {

    @Autowired
    RecommendationService recommendationService;

    @Test
    void getRange() {
        String latitude = "36.349";
        String longitude = "127.3848";
        int distance = 1000;

        CoordinateRange range = recommendationService.getRange(latitude, longitude, distance);
        System.out.println("range.getLatitudeStart() = " + range.getLatitudeStart());
        System.out.println("range.getLatitudeEnd() = " + range.getLatitudeEnd());
        System.out.println("range.getLongitudeStart() = " + range.getLongitudeStart());
        System.out.println("range.getLongitudeEnd() = " + range.getLongitudeEnd());
    }

    @Test
    @DisplayName("추천이 정상적으로 진행됩니다.")
    void recommendation() {
        RecommendationRequestDto recommendationRequestDto = new RecommendationRequestDto();
        List<String> categories = new ArrayList<>();
        categories.add("KOREAN");
        recommendationRequestDto.setCategories(categories);
        recommendationRequestDto.setRange(1000);
        recommendationRequestDto.setLatitude("36.3467");
        recommendationRequestDto.setLongitude("127.3848");

        RecommendationResultDto result = recommendationService.recommendation(recommendationRequestDto);

        System.out.println("result = " + result.toString());

        Assertions.assertThat(result).isNotNull();
    }
}