package com.kj.WhatShouldIEatTodayBack.service.review;

import com.kj.WhatShouldIEatTodayBack.domain.member.Member;
import com.kj.WhatShouldIEatTodayBack.domain.review.Review;
import com.kj.WhatShouldIEatTodayBack.domain.review.repository.ReviewRepository;
import com.kj.WhatShouldIEatTodayBack.domain.store.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewCreateRequestDto save(ReviewCreateRequestDto reviewCreateRequestDto) {
        Review review = Review.builder()
                .content(reviewCreateRequestDto.getContent())
                .member(reviewCreateRequestDto.getMember())
                .store(reviewCreateRequestDto.getStore())
                .build();

        reviewRepository.save(review);

        return reviewCreateRequestDto;
    }


    public ReviewCreateRequestDto updateById(Long id, ReviewCreateRequestDto reviewCreateRequestDto) {
        Review review = reviewRepository.updateContentById(id, reviewCreateRequestDto.getContent());
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
