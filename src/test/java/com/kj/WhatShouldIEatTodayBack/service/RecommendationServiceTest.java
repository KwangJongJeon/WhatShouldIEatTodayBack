package com.kj.WhatShouldIEatTodayBack.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

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
}