package com.kj.WhatShouldIEatTodayBack.service.review.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
@Data
public class ReviewUpdateRequestDto {
    private final long id;
    private final String content;
}
