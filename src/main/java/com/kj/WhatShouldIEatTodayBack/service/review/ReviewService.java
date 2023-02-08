package com.kj.WhatShouldIEatTodayBack.service.review;

import com.kj.WhatShouldIEatTodayBack.domain.member.Member;
import com.kj.WhatShouldIEatTodayBack.domain.member.repository.MemberRepository;
import com.kj.WhatShouldIEatTodayBack.domain.review.Review;
import com.kj.WhatShouldIEatTodayBack.domain.review.repository.ReviewRepository;
import com.kj.WhatShouldIEatTodayBack.domain.store.Store;
import com.kj.WhatShouldIEatTodayBack.domain.store.respository.StoreRepository;
import com.kj.WhatShouldIEatTodayBack.service.review.dto.ReviewCreateRequestDto;
import com.kj.WhatShouldIEatTodayBack.service.review.dto.ReviewCreateResponseDto;
import com.kj.WhatShouldIEatTodayBack.service.review.dto.ReviewResponseDto;
import com.kj.WhatShouldIEatTodayBack.service.review.dto.ReviewUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;


    /**
     *
     * @param reviewCreateRequestDto 리뷰를 생성하기 위한 Dto, 컨텐츠, 멤버 이메일, 스토어 아이디 포함
     * @return 저장을 완료한 뒤 완료한 데이터를 리턴함
     */
    public ReviewCreateResponseDto save(ReviewCreateRequestDto reviewCreateRequestDto) {
        Optional<Member> member = memberRepository.findByMemberEmail(reviewCreateRequestDto.getMemberEmail());
        Optional<Store> store = storeRepository.findById(reviewCreateRequestDto.getStoreId());

        if(member.isPresent() && store.isPresent()) {
            Review review = Review.builder()
                    .content(reviewCreateRequestDto.getContent())
                    .member(member.get())
                    .store(store.get())
                    .build();

            Review savedReview = reviewRepository.save(review);
            ReviewCreateResponseDto responseDto = ReviewCreateResponseDto.builder()
                    .id(savedReview.getId())
                    .member(savedReview.getMember())
                    .store(savedReview.getStore())
                    .content(savedReview.getContent())
                    .build();

            return responseDto;

        } else {
            return null;
        }
    }

    public Review findById(Long id) {
        Optional<Review> reviewOpt = reviewRepository.findById(id);

        if(reviewOpt.isEmpty()) return null;

        return reviewOpt.get();
    }


    public List<ReviewResponseDto> findByMember(Member member) {
        List<Review> reviewByMember = reviewRepository.findReviewByMember(member);

        return convertToReviewResponseDtoList(reviewByMember);
    }

    public List<ReviewResponseDto> findByStore(Store store) {
        List<Review> reviewsByStore = reviewRepository.findReviewByStore(store);

        return convertToReviewResponseDtoList(reviewsByStore);
    }

    public List<ReviewResponseDto> findByStore(Long id) {
        List<Review> reviewsByStore = reviewRepository.findReviewByStore(id);

        return convertToReviewResponseDtoList(reviewsByStore);
    }

    @Transactional
    public Long updateReviewContent(ReviewUpdateRequestDto requestDto) {
        Optional<Review> reviewOpt = reviewRepository.findById(requestDto.getId());

        if(reviewOpt.isEmpty()) return null;

        Review review = reviewOpt.get();
        review.setContent(requestDto.getContent());
        return review.getId();
    }




    private List<ReviewResponseDto> convertToReviewResponseDtoList(List<Review> reviewsByStore) {
        List<ReviewResponseDto> reviewResponseDtos = new ArrayList<>();
        for (Review review : reviewsByStore) {
            reviewResponseDtos.add(ReviewResponseDto.builder()
                    .id(review.getId())
                    .content(review.getContent())
                    .nickName(review.getMember().getNickName())
                    .storeName(review.getStore().getName())
                    .build());
        }

        return reviewResponseDtos;
    }


}
