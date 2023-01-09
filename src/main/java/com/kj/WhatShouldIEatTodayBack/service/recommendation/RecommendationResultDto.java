package com.kj.WhatShouldIEatTodayBack.service.recommendation;

import com.kj.WhatShouldIEatTodayBack.service.crawler.Menu;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Builder
@Data
public class RecommendationResultDto {

    private final long id;
    private final String name;
    private final String region;

    private final String divisionOne;
    private final String divisionTwo;
    private final String divisionThree;

    private final String lotAddress;
    private final String streetAddress;

    private final List<Menu> menuList;
    private final String phoneNumber;

    private final double latitude;
    private final double longitude;
}
