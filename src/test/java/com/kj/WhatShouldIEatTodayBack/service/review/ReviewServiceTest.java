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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

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
    void save() {
        Member member = memberRepository.findByMemberEmail("reviewTester").get();
        Store store = storeRepository.findById(1L).get();

        String content = "createTest";
        ReviewCreateRequestDto reviewCreateRequestDto = ReviewCreateRequestDto.builder()
                .content(content)
                .member(member)
                .store(store)
                .build();

        reviewService.save(reviewCreateRequestDto);

        Review review = reviewRepository.findAll().get(0);
        assertThat(review.getContent()).isEqualTo(content);
    }

    @Test
    void updateById() {
        Member member = memberRepository.findByMemberEmail("reviewTester").get();
        Store store = storeRepository.findById(1L).get();

        String beforeContent = "createTest";
        String updatedContent = "updateTest";
        ReviewCreateRequestDto reviewCreateRequestDto = ReviewCreateRequestDto.builder()
                .content(beforeContent)
                .member(member)
                .store(store)
                .build();

        reviewService.save(reviewCreateRequestDto);
        Long id = reviewRepository.findAll().get(0).getId();


        reviewService.updateById(id, updatedContent);


        String result = reviewRepository.findAll().get(0).getContent();
        assertThat(result).isEqualTo(updatedContent);
    }

    @Test
    void findByMember() {
        Member member = memberRepository.findByMemberEmail("reviewTester").get();
        Store store = storeRepository.findById(1L).get();

        String content = "findTest";

        ReviewCreateRequestDto reviewCreateRequestDto = ReviewCreateRequestDto.builder()
                .content(content)
                .member(member)
                .store(store)
                .build();

        reviewService.save(reviewCreateRequestDto);

        ReviewResponseDto result = reviewService.findByMember(member).get(0);
        assertThat(result.getContent()).isEqualTo(content);
    }

    @Test
    void findByStore() {
        Member member = memberRepository.findByMemberEmail("reviewTester").get();
        Store store = storeRepository.findById(1L).get();

        String content = "findByStoreTest";

        ReviewCreateRequestDto reviewCreateRequestDto = ReviewCreateRequestDto.builder()
                .content(content)
                .member(member)
                .store(store)
                .build();

        reviewService.save(reviewCreateRequestDto);

        ReviewResponseDto result = reviewService.findByStore(store).get(0);

        assertThat(result.getContent()).isEqualTo(content);
    }
}