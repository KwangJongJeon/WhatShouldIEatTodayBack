package com.kj.WhatShouldIEatTodayBack.dto;

import lombok.Getter;
import lombok.Setter;


/**
 * Kakao API로부터 데이터를 저장하는 Dto입니다.
 * 해당 페이지의 API를 참고했습니다.
 * https://developers.kakao.com/docs/latest/ko/local/dev-guide#search-by-keyword
 *
 * @author KwangJong Jeon
 * @since 0.1
 */
@Getter @Setter
public class ResponseDocument {
    private String id;
    private String place_name;
    private String category_group_code;
    private String category_group_name;
    private String phone;
    private String address_name;
    private String road_address_name;
    private String x;
    private String y;
    private String place_url;
    private String distance;
}
