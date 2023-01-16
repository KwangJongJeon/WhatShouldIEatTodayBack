package com.kj.WhatShouldIEatTodayBack.service.review.dto;

import com.kj.WhatShouldIEatTodayBack.domain.member.Member;
import com.kj.WhatShouldIEatTodayBack.domain.store.Store;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * review 검색 요청에 대응하는 대답을 위한 Dto입니다.
 *
 * id : 리뷰의 id
 * content: 리뷰의 내용
 * member: 리뷰를 쓴 멤버
 * store: 리뷰가 적용된 상점
 */
@Builder
@Data
@RequiredArgsConstructor
public class ReviewResponseDto {
    private final long id;
    private final String content;
    private final Member member;
    private final Store store;
}
