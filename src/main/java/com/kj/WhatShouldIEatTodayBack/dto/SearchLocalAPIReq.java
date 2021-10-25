package com.kj.WhatShouldIEatTodayBack.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

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
}
