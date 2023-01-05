package com.kj.WhatShouldIEatTodayBack.domain.review.repository;

import com.kj.WhatShouldIEatTodayBack.domain.member.Member;
import com.kj.WhatShouldIEatTodayBack.domain.member.repository.MemberRepository;
import com.kj.WhatShouldIEatTodayBack.domain.review.Review;
import com.kj.WhatShouldIEatTodayBack.domain.store.Store;
import com.kj.WhatShouldIEatTodayBack.domain.store.respository.StoreRepository;
import com.kj.WhatShouldIEatTodayBack.enums.MemberRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class ReviewRepositoryImplTest {

    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    StoreRepository storeRepository;

    Member member;
    Store store;


    @BeforeEach
    void beforeEach() {
        member = Member.builder()
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

        store = Store.builder()
                .name("Store")
                .region("대전")
                .divisionOne("음식")
                .divisionTwo("한식")
                .divisionThree("비빔밥")
                .lotAddress("Test")
                .streetAddress("Test")
                .latitude(36.3467)
                .longitude(127.3848)
                .build();

        storeRepository.save(store);
    }

    @Test
    void save() {
        Member member = memberRepository.findByMemberEmail("reviewTester").get();
        Store store = storeRepository.findById(1L).get();
        Review review = Review.builder()
                .member(member)
                .store(store)
                .content("Hello!")
                .build();

        reviewRepository.save(review);

        assertThat(reviewRepository.findById(1L).get()).isNotNull();
    }

    @Test
    void findById() {
        Member member = memberRepository.findByMemberEmail("reviewTester").get();
        Store store = storeRepository.findById(1L).get();
        Review review = Review.builder()
                .member(member)
                .store(store)
                .content("Hello!")
                .build();

        reviewRepository.save(review);
        Review foundReview = reviewRepository.findById(1L).get();

        assertThat(foundReview.getContent()).isEqualTo("Hello!");
    }

    @Test
    void findReviewByMember() {
        Member member = memberRepository.findByMemberEmail("reviewTester").get();
        Store store = storeRepository.findById(1L).get();
        Review review = Review.builder()
                .member(member)
                .store(store)
                .content("Hello!")
                .build();

        reviewRepository.save(review);
        Review result = reviewRepository.findReviewByMember(member).get(0);

        assertThat(result.getMember().getMemberEmail()).isEqualTo("reviewTester");
    }

    @Test
    void findReviewByStore() {
        Member member = memberRepository.findByMemberEmail("reviewTester").get();
        Store store = storeRepository.findById(1L).get();
        Review review = Review.builder()
                .member(member)
                .store(store)
                .content("Hello!")
                .build();

        reviewRepository.save(review);

        Review result = reviewRepository.findReviewByStore(store).get(0);

        assertThat(result.getStore().getName()).isEqualTo("큰손의정부부대찌개");
    }
}