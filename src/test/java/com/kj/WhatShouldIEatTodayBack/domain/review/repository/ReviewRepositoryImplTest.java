package com.kj.WhatShouldIEatTodayBack.domain.review.repository;

import com.kj.WhatShouldIEatTodayBack.domain.member.Member;
import com.kj.WhatShouldIEatTodayBack.domain.member.repository.MemberRepository;
import com.kj.WhatShouldIEatTodayBack.domain.review.Review;
import com.kj.WhatShouldIEatTodayBack.domain.store.Store;
import com.kj.WhatShouldIEatTodayBack.domain.store.respository.StoreRepository;
import com.kj.WhatShouldIEatTodayBack.enums.MemberRole;
import org.junit.jupiter.api.BeforeAll;
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

//        Member reviewMember = memberRepository.findByMemberEmail("reviewTester").get();
//        Store reviewStore = storeRepository.findById(1L).get();
//
//        Review review = Review.builder()
//                .member(reviewMember)
//                .store(reviewStore)
//                .content("Hello!")
//                .build();
//
//        reviewRepository.save(review);
    }

    @Test
    void save() {
        Member reviewMember = memberRepository.findByMemberEmail("reviewTester").get();
        Store reviewStore = storeRepository.findById(1L).get();

        Review review = Review.builder()
                .member(reviewMember)
                .store(reviewStore)
                .content("Hello!")
                .build();

        reviewRepository.save(review);

        Review result = reviewRepository.findReviewByStore(reviewStore).get(0);

        assertThat(result).isNotNull();
    }

    @Test
    void update() {
        Member reviewMember = memberRepository.findByMemberEmail("reviewTester").get();
        Store reviewStore = storeRepository.findById(1L).get();

        Review review = Review.builder()
                .member(reviewMember)
                .store(reviewStore)
                .content("Hello!")
                .build();

        reviewRepository.save(review);

        review.setContent("Update!");

        reviewRepository.update(review);

        Review result = reviewRepository.findAll().get(0);

        assertThat(result.getContent()).isEqualTo("Update!");
    }

    @Test
    void updateContentById() {
        Member reviewMember = memberRepository.findByMemberEmail("reviewTester").get();
        Store reviewStore = storeRepository.findById(1L).get();

        Review review = Review.builder()
                .member(reviewMember)
                .store(reviewStore)
                .content("Hello!")
                .build();

        reviewRepository.save(review);

        reviewRepository.updateContentById(1L, "Update!");

        Review result = reviewRepository.findAll().get(0);

        assertThat(result.getContent()).isEqualTo("Update!");
    }

    @Test
    void findById() {
        Member reviewMember = memberRepository.findByMemberEmail("reviewTester").get();
        Store reviewStore = storeRepository.findById(1L).get();

        Review review = Review.builder()
                .member(reviewMember)
                .store(reviewStore)
                .content("Hello!")
                .build();

        reviewRepository.save(review);

        Review foundReview = reviewRepository.findById(1L).get();

        assertThat(foundReview.getContent()).isEqualTo("Hello!");
    }

    @Test
    void findReviewByMember() {
        Member reviewMember = memberRepository.findByMemberEmail("reviewTester").get();
        Store reviewStore = storeRepository.findById(1L).get();

        Review review = Review.builder()
                .member(reviewMember)
                .store(reviewStore)
                .content("Hello!")
                .build();

        reviewRepository.save(review);

        Review result = reviewRepository.findReviewByMember(member).get(0);

        assertThat(result.getMember().getMemberEmail()).isEqualTo("reviewTester");
    }

    @Test
    void findReviewByStore() {
        Member reviewMember = memberRepository.findByMemberEmail("reviewTester").get();
        Store reviewStore = storeRepository.findById(1L).get();

        Review review = Review.builder()
                .member(reviewMember)
                .store(reviewStore)
                .content("Hello!")
                .build();

        reviewRepository.save(review);

        Review result = reviewRepository.findReviewByStore(reviewStore).get(0);

        assertThat(result.getStore().getName()).isEqualTo("큰손의정부부대찌개");
    }
}