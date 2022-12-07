package com.kj.WhatShouldIEatTodayBack.dto.api;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RequiredArgsConstructor
@Data
@Slf4j
public class SearchLocalAPIReq {
    private final String query;
    private final String x; // 중심좌표의 x값 혹은 longitude, 특정 지역을 중심으로 검색하려고 할 경우 radius와 함께 사용
    private final String y; // 중심좌표의 y값 혹은 latitude
    private final String radius;
    private String categoryGroupCode;
    private String rect;
    private int page;
    private int size;
    private String sort;

    public MultiValueMap<String, String> makeMultiValueMap() {
        LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        map.add("query", query);
        map.add("x", x);
        map.add("y", y);
        map.add("radius", radius);

        if(rect != null) {
            map.add("rect", rect);
        }

        if(page != 0) {
            map.add("page", String.valueOf(page));
        }

        if(size != 0) {
            map.add("sort", sort);
        }

        if(categoryGroupCode != null) {
            map.add("sort", sort);
        }

        return map;
    }
}
