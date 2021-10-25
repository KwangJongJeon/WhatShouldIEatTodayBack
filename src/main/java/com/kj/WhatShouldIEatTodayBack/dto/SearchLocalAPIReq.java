package com.kj.WhatShouldIEatTodayBack.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RequiredArgsConstructor
@Data
public class SearchLocalAPIReq {
    private final String query;
    private final String x;
    private final String y;
    private final String radius;
    private String categoryGroupCode;
    private String rect;
    private int page;
    private int size;
    private String sort;

    public MultiValueMap<String, String> makeMultiValueMap() {
        LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        map.add("query", query);
        map.add("x", y);
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
