package com.kj.WhatShouldIEatTodayBack.service.review;

import com.kj.WhatShouldIEatTodayBack.domain.member.Member;
import com.kj.WhatShouldIEatTodayBack.domain.review.Review;
import com.kj.WhatShouldIEatTodayBack.domain.review.repository.ReviewRepository;
import com.kj.WhatShouldIEatTodayBack.domain.store.Store;
import com.kj.WhatShouldIEatTodayBack.service.review.dto.ReviewCreateRequestDto;
import com.kj.WhatShouldIEatTodayBack.service.review.dto.ReviewCreateResponseDto;
import com.kj.WhatShouldIEatTodayBack.service.review.dto.ReviewResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewCreateResponseDto save(ReviewCreateRequestDto reviewCreateRequestDto) {
        Review review = Review.builder()
                .content(reviewCreateRequestDto.getContent())
                .member(reviewCreateRequestDto.getMember())
                .store(reviewCreateRequestDto.getStore())
                .build();

        Review savedReview = reviewRepository.save(review);

        ReviewCreateResponseDto responseDto = ReviewCreateResponseDto.builder()
                .id(savedReview.getId())
                .member(savedReview.getMember())
                .store(savedReview.getStore())
                .content(savedReview.getContent())
                .build();

        return responseDto;
    }


    public ReviewCreateRequestDto updateById(Long id, String content) {
        Review review = reviewRepository.updateContentById(id, content);
        return ReviewCreateRequestDto.builder()
                .content(review.getContent())
                .member(review.getMember())
                .store(review.getStore())
                .build();
    }

    public List<ReviewResponseDto> findByMember(Member member) {
        List<Review> reviewByMember = reviewRepository.findReviewByMember(member);

        return convertToReviewResponseDtoList(reviewByMember);
    }

    public List<ReviewResponseDto> findByStore(Store store) {
        List<Review> reviewsByStore = reviewRepository.findReviewByStore(store);

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
