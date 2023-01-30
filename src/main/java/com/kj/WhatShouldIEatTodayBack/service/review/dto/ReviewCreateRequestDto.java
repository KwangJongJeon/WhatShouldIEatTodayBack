package com.kj.WhatShouldIEatTodayBack.service.review.dto;

import com.kj.WhatShouldIEatTodayBack.domain.member.Member;
import com.kj.WhatShouldIEatTodayBack.domain.store.Store;
import lombok.*;

/**
 * review 생성을 요청하기 위한 Dto입니다
 *
 * content: 리뷰의 내용
 * member: 리뷰를 쓴 멤버
 * store: 리뷰가 적용된 상점
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReviewCreateRequestDto {
    private String content;
    private String memberEmail;
    private Long storeId;
}
