package com.kj.WhatShouldIEatTodayBack.domain.store;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Data
public class RecommendationResultDto {
    private final String name;
    private final String region;
    private final String divisionOne;
    private final String divisionTwo;
    private final String divisionThree;
    private final String lotAddress;
    private final String streetAddress;
    private final double latitude;
    private final double longitude;
}
