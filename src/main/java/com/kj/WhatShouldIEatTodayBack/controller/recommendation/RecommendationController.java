package com.kj.WhatShouldIEatTodayBack.controller.recommendation;

import com.kj.WhatShouldIEatTodayBack.controller.dto.RecommendationRequestDto;
import com.kj.WhatShouldIEatTodayBack.domain.member.Member;
import com.kj.WhatShouldIEatTodayBack.enums.CategoryTypes;
import com.kj.WhatShouldIEatTodayBack.service.auth.AuthService;
import com.kj.WhatShouldIEatTodayBack.service.auth.MemberInfoDto;
import com.kj.WhatShouldIEatTodayBack.service.recommendation.RecommendationResultDto;
import com.kj.WhatShouldIEatTodayBack.service.recommendation.RecommendationService;
import com.kj.WhatShouldIEatTodayBack.service.review.ReviewService;
import com.kj.WhatShouldIEatTodayBack.service.review.dto.ReviewResponseDto;
import com.kj.WhatShouldIEatTodayBack.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;
    private final AuthService authService;
    private final ReviewService reviewService;

    @ModelAttribute("categories")
    public Map<String, String> categories() {
        Map<String, String> categories = new LinkedHashMap<>();

        for (CategoryTypes type : CategoryTypes.values()) {
            categories.put(type.name(), type.getDescription());
        }

        return categories;
    }

    @ModelAttribute("memberInfoDto")
    public MemberInfoDto memberInfoDto(Authentication authentication) {
        return authService.getMemberInfo(authentication);
    }

    @GetMapping("/recommendation")
    public String recommendationForm(HttpServletRequest request,
                                 @ModelAttribute RecommendationRequestDto recommendationDto,
                                 Model model) {

        HttpSession session = request.getSession(false);

        if(session != null) {
            Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
            model.addAttribute("member", loginMember);
        }

        return "page/recommendation";
    }

    @PostMapping("/recommendation")
    public String recommendation(Authentication authentication,
                                       @ModelAttribute RecommendationRequestDto recommendationRequestDto,
                                       Model model) {

        for (String category : recommendationRequestDto.getCategories()) {
            log.info("category: {}", category);
        }

        MemberInfoDto memberInfo = authService.getMemberInfo(authentication);
        model.addAttribute("member", memberInfo);

        RecommendationResultDto recommendationResult = recommendationService.recommendation(recommendationRequestDto);

        if(recommendationResult == null) {
            return "page/recommendationError";
        }

        List<ReviewResponseDto> reviews = reviewService.findByStore(recommendationResult.getId());

        model.addAttribute("reviews", reviews);
        model.addAttribute("recommendationResult", recommendationResult);

        return "page/recommendationResult";
    }

    @GetMapping("/recommendationTest")
    public String recommendationResultTest(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        if(session != null) {
            Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
            model.addAttribute("member", loginMember);
        }

        return "page/recommendationTest";
    }
}
