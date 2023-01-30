package com.kj.WhatShouldIEatTodayBack.service.review;

import com.kj.WhatShouldIEatTodayBack.domain.member.Member;
import com.kj.WhatShouldIEatTodayBack.domain.member.repository.MemberRepository;
import com.kj.WhatShouldIEatTodayBack.domain.review.Review;
import com.kj.WhatShouldIEatTodayBack.domain.review.repository.ReviewRepository;
import com.kj.WhatShouldIEatTodayBack.domain.store.Store;
import com.kj.WhatShouldIEatTodayBack.domain.store.respository.StoreRepository;
import com.kj.WhatShouldIEatTodayBack.enums.MemberRole;
import com.kj.WhatShouldIEatTodayBack.service.review.dto.ReviewCreateRequestDto;
import com.kj.WhatShouldIEatTodayBack.service.review.dto.ReviewResponseDto;
import com.kj.WhatShouldIEatTodayBack.service.review.dto.ReviewUpdateRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest
class ReviewServiceTest {

    @Autowired
    ReviewService reviewService;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    StoreRepository storeRepository;

    @BeforeEach
    void beforeEach() {
        Member member = Member.builder()
                .memberEmail("reviewTester")
                .memberPw("reviewTester")
                .name("reviewTester")
                .nickName("reviewTester")
                .phone1("010")
                .phone2("0000")
                .phone3("0000")
                .memberRole(MemberRole.USER)
                .build();

        memberRepository.save(member);
    }

    @Test
    @DisplayName("리뷰를 저장 할 수 있다.")
    void save() {
        Member member = memberRepository.findByMemberEmail("reviewTester").get();
        Store store = storeRepository.findById(1L).get();

        String content = "createTest";
        ReviewCreateRequestDto reviewCreateRequestDto = ReviewCreateRequestDto.builder()
                .content(content)
                .memberEmail("reviewTester")
                .storeId(1L)
                .build();

        reviewService.save(reviewCreateRequestDto);

        Review review = reviewRepository.findAll().get(0);
        assertThat(review.getContent()).isEqualTo(content);
    }

    @Test
    @DisplayName("리뷰의 아이디를 통해 리뷰를 검색 할 수 있다.")
    void updateById() {
        // given
        String content = "createTest";
        ReviewCreateRequestDto reviewCreateRequestDto = ReviewCreateRequestDto.builder()
                .content(content)
                .memberEmail("reviewTester")
                .storeId(1L)
                .build();

        long id = reviewService.save(reviewCreateRequestDto).getId();


        ReviewResponseDto result = reviewService.findById(id);

        assertThat(result.getContent()).isEqualTo(content);
    }

    @Test
    @DisplayName("유저가 쓴 리뷰를 검색 할 수 있다")
    void findByMember() {
        // given
        Member member = memberRepository.findByMemberEmail("reviewTester").get();
        Store store = storeRepository.findById(1L).get();

        String content = "createTest";
        ReviewCreateRequestDto reviewCreateRequestDto = ReviewCreateRequestDto.builder()
                .content(content)
                .memberEmail("reviewTester")
                .storeId(1L)
                .build();

        reviewService.save(reviewCreateRequestDto);

        // when
        ReviewResponseDto result = reviewService.findByMember(member).get(0);

        // then
        assertThat(result.getContent()).isEqualTo(content);
    }

    @Test
    @DisplayName("상점에 있는 리뷰를 검색 할 수 있다.")
    void findByStore() {
        // given
        Member member = memberRepository.findByMemberEmail("reviewTester").get();
        Store store = storeRepository.findById(1L).get();

        String content = "createTest";
        ReviewCreateRequestDto reviewCreateRequestDto = ReviewCreateRequestDto.builder()
                .content(content)
                .memberEmail("reviewTester")
                .storeId(1L)
                .build();

        reviewService.save(reviewCreateRequestDto);

        // when
        ReviewResponseDto result = reviewService.findByStore(1L).get(0);

        // then
        assertThat(result.getContent()).isEqualTo(content);
    }

    @Test
    @DisplayName("상점에 있는 여러 리뷰를 검색 할 수 있다.")
    void findReviewsByStore() {
        // given
        Member member = memberRepository.findByMemberEmail("reviewTester").get();
        Store store = storeRepository.findById(1L).get();

        String content = "createTest";
        ReviewCreateRequestDto reviewCreateRequestDto = ReviewCreateRequestDto.builder()
                .content(content)
                .memberEmail("reviewTester")
                .storeId(1L)
                .build();

        reviewService.save(reviewCreateRequestDto);

        String contentTwo = "createTest2";
        ReviewCreateRequestDto reviewCreateRequestDtoTwo = ReviewCreateRequestDto.builder()
                .content(contentTwo)
                .memberEmail("reviewTester")
                .storeId(1L)
                .build();

        reviewService.save(reviewCreateRequestDtoTwo);



        // when
        ReviewResponseDto resultOne = reviewService.findByStore(1L).get(0);
        ReviewResponseDto resultTwo = reviewService.findByStore(1L).get(1);

        // then
        assertThat(resultOne.getContent()).isEqualTo(content);
        assertThat(resultTwo.getContent()).isEqualTo(contentTwo);
    }

    @Test
    @DisplayName("리뷰의 내용이 정상적으로 업데이트된다")
    void updateReviewContent() {
        // given
        String content = "createTest";
        String expectedContent = "updateTest";

        ReviewCreateRequestDto reviewCreateRequestDto = ReviewCreateRequestDto.builder()
                .content(content)
                .memberEmail("reviewTester")
                .storeId(1L)
                .build();

        long id = reviewService.save(reviewCreateRequestDto).getId();

        ReviewUpdateRequestDto reviewUpdateRequestDto = new ReviewUpdateRequestDto(id, expectedContent);


        // when
        Long updatedId = reviewService.updateReviewContent(reviewUpdateRequestDto);


        // then
        String updatedContent = reviewService.findById(updatedId).getContent();
        assertThat(updatedContent).isEqualTo(expectedContent);
    }

}