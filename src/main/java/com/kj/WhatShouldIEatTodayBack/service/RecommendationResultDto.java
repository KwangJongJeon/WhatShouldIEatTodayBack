package com.kj.WhatShouldIEatTodayBack.service;

import com.kj.WhatShouldIEatTodayBack.service.crawler.Menu;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Builder
@Data
public class RecommendationResultDto {

    private final int id;
    private final String name;
    private final String region;

    private final String divisionOne;
    private final String divisionTwo;
    private final String divisionThree;

    private final String lotAddress;
    private final String streetAddress;

    private final List<Menu> menuList;

    private final double latitude;
    private final double longitude;
}
