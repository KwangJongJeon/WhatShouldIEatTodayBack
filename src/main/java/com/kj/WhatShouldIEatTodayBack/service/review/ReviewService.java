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
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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




    private List<ReviewResponseDto> convertToReviewResponseDtoList(List<Review> reviewsByStore) {
        List<ReviewResponseDto> reviewResponseDtos = new ArrayList<>();
        for (Review review : reviewsByStore) {
            reviewResponseDtos.add(ReviewResponseDto.builder()
                    .id(review.getId())
                    .content(review.getContent())
                    .member(review.getMember())
                    .store(review.getStore())
                    .build());
        }

        return reviewResponseDtos;
    }


}
