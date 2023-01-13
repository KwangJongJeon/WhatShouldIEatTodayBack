package com.kj.WhatShouldIEatTodayBack.controller.review;

import com.kj.WhatShouldIEatTodayBack.service.review.ReviewCreateRequestDto;
import com.kj.WhatShouldIEatTodayBack.service.review.ReviewCreateResponseDto;
import com.kj.WhatShouldIEatTodayBack.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;

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
    public String reviewDetail() {
        return "/page/reviewDetail";
    }






}