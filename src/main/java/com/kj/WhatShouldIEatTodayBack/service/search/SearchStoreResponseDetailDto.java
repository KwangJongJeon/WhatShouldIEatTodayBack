package com.kj.WhatShouldIEatTodayBack.service.search;

import com.kj.WhatShouldIEatTodayBack.domain.review.Review;
import com.kj.WhatShouldIEatTodayBack.service.review.dto.ReviewResponseDto;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class SearchStoreResponseDetailDto {
    private Long id;

    private String name;
    private String region;

    private String divisionOne;
    private String divisionTwo;
    private String divisionThree;

    private String lotAddress;
    private String streetAddress;
    private List<ReviewResponseDto> reviews;

    private double latitude;
    private double longitude;

    void initReviews(List<Review> reviewList) {

        List<ReviewResponseDto> reviewResponseDtos = new ArrayList<>();

        for (Review review : reviewList) {
            reviewResponseDtos.add(ReviewResponseDto.builder()
                    .id(review.getId())
                    .content(review.getContent())
                    .nickName(review.getMember().getNickName())
                    .storeName(review.getStore().getName())
                    .build());
        }

        reviews = reviewResponseDtos;
    }
}
