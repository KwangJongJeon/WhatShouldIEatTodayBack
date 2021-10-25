package com.kj.WhatShouldIEatTodayBack.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
public class RecommendServiceReq {
    String latitude;
    String longitude;
    String range;
    ArrayList<String> categories;
}
