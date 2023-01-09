package com.kj.WhatShouldIEatTodayBack.service.review;

import com.kj.WhatShouldIEatTodayBack.domain.member.Member;
import com.kj.WhatShouldIEatTodayBack.domain.store.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * review 생성에 응답하기 위한 Dto입니다
 *
 * id: 아이디
 * content: 리뷰의 내용
 * member: 리뷰를 쓴 멤버
 * store: 리뷰가 적용된 상점
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReviewCreateResponseDto {
    private long id;
    private String content;
    private Member member;
    private Store store;
}
