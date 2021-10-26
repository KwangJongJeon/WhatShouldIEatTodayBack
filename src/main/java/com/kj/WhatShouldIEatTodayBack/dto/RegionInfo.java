package com.kj.WhatShouldIEatTodayBack.dto;

import lombok.Data;

/**
 * ResponseMeta의 하위 정보입니다.
 */
@Data
public class RegionInfo {

    private String[] region;
    private String keyword;
    private String selected_region;
}
