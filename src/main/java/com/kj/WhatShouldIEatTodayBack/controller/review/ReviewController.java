package com.kj.WhatShouldIEatTodayBack.controller.review;

import com.kj.WhatShouldIEatTodayBack.domain.review.Review;
import com.kj.WhatShouldIEatTodayBack.service.review.dto.ReviewCreateRequestDto;
import com.kj.WhatShouldIEatTodayBack.service.review.dto.ReviewCreateResponseDto;
import com.kj.WhatShouldIEatTodayBack.service.review.ReviewService;
import com.kj.WhatShouldIEatTodayBack.service.review.dto.ReviewResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/review")
@Controller
public class ReviewController {
    private final ReviewService reviewService;


    @PostMapping("/add")
    public String addReview(ReviewCreateRequestDto reviewCreateRequestDto,
                                         RedirectAttributes redirectAttributes) {
        ReviewCreateResponseDto responseDto = reviewService.save(reviewCreateRequestDto);
        redirectAttributes.addAttribute("reviewId", responseDto.getId());

        return "redirect:/review/{reviewId}";
    }

    @GetMapping("/{reviewId}")
    public String reviewDetail(@PathVariable("reviewId") Long reviewId, Model model) {
        Review review = reviewService.findById(reviewId);

        ReviewResponseDto reviewDto = ReviewResponseDto.builder()
                .id(review.getId())
                .content(review.getContent())
                .nickName(review.getMember().getNickName())
                .storeName(review.getStore().getName()).build();

        model.addAttribute("reviewDto", reviewDto);
        return "/page/reviewDetail";
    }
}
