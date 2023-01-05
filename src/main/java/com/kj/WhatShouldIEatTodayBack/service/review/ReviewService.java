package com.kj.WhatShouldIEatTodayBack.service.review;

import com.kj.WhatShouldIEatTodayBack.domain.review.Review;
import com.kj.WhatShouldIEatTodayBack.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewDto saveReview(ReviewDto reviewDto) {
        Review review = Review.builder()
                .content(reviewDto.getContent())
                .member(reviewDto.getMember())
                .store(reviewDto.getStore())
                .build();

        reviewRepository.save(review);

        return reviewDto;
    }


//    public ReviewDto updateReview(ReviewUpdateRequestDto requestDto) {
//        long id = requestDto.getId();
//        String content = requestDto.getContent();
//        Optional<Review> reviewOpt = reviewRepository.findById(id);
//        if(reviewOpt.isEmpty()) return null;
//
//        Review review = reviewOpt.get();
//        reviewRepository.update()
//    }
}
