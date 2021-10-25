package com.kj.WhatShouldIEatTodayBack.api.controller;

import com.kj.WhatShouldIEatTodayBack.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping(value = "/api")
@RestController
public class RecommendationController {

    private RecommendationService recommendationService;


}
