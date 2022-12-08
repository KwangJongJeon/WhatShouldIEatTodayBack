package com.kj.WhatShouldIEatTodayBack.controller.dto;

import com.kj.WhatShouldIEatTodayBack.enums.CategoryTypes;
import lombok.Data;

import java.util.List;

/**
 * Recommendation 폼에서 사용하기 위한 Dto입니다.
 * 구성 요소
 * range: 유저가 탐색을 원하는 범위
 * categories: 유저가 원하는 음식의 종류(ex, 한식, 중식 양식 등)
 */
@Data
public class RecommendationDto {

    private int range;
    private List<String> categories;
    private String latitude;
    private String longitude;
}
