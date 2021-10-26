package com.kj.WhatShouldIEatTodayBack.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * 프론트앤드에서 들어온 데이터를 담는 Dto
 */
@Getter @Setter
public class RecommendServiceReq {
    String latitude;
    String longitude;
    String range;
    ArrayList<String> categories;
}
