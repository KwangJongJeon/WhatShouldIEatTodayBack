package com.kj.WhatShouldIEatTodayBack.dto.api;

import lombok.Data;

/**
 * ResponseDocument의 하위 클래스로 메타 정보를 포함합니다.
 */
@Data
public class ResponseMeta {

    private int total_count;
    private int pageable_count;
    private boolean is_end;
    private RegionInfo same_number;
}
