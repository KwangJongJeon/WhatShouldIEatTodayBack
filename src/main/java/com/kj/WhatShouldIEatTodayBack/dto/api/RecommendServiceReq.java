package com.kj.WhatShouldIEatTodayBack.dto.api;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 프론트앤드에서 들어온 데이터를 담는 Dto
 */
@Getter @Setter
@Data
public class RecommendServiceReq {
    private String latitude;
    private String longitude;
    private String range;
    private List<String> categories;
}
