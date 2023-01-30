package com.kj.WhatShouldIEatTodayBack.service.search;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SearchStoreResponseDto {
    private Long id;

    private String name;
    private String region;

    private String divisionOne;
    private String divisionTwo;
    private String divisionThree;

    private String lotAddress;
    private String streetAddress;
}

