package com.kj.WhatShouldIEatTodayBack.service;

import lombok.Data;

@Data
public class RecommendationResultDto {

    private int id;
    private String name;
    private String region;

    private String divisionOne;
    private String divisionTwo;
    private String divisionThree;

    private String lotAddress;
    private String streetAddress;

    private int latitude;
    private int longitude;
}
