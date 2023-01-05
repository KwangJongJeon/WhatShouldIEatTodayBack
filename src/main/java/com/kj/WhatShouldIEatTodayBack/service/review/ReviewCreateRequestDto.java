package com.kj.WhatShouldIEatTodayBack.service.review;

import com.kj.WhatShouldIEatTodayBack.domain.member.Member;
import com.kj.WhatShouldIEatTodayBack.domain.store.Store;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * review 생성을 요청하기 위한 Dto입니다
 *
 * content: 리뷰의 내용
 * member: 리뷰를 쓴 멤버
 * store: 리뷰가 적용된 상점
 */
@Builder
@Data
@RequiredArgsConstructor
public class ReviewCreateRequestDto {
    private final String content;
    private final Member member;
    private final Store store;
}
