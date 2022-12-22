package com.kj.WhatShouldIEatTodayBack.controller.recommendation;

import com.kj.WhatShouldIEatTodayBack.controller.dto.RecommendationRequestDto;
import com.kj.WhatShouldIEatTodayBack.domain.member.Member;
import com.kj.WhatShouldIEatTodayBack.domain.store.RecommendationResultDto;
import com.kj.WhatShouldIEatTodayBack.enums.CategoryTypes;
import com.kj.WhatShouldIEatTodayBack.service.RecommendationService;
import com.kj.WhatShouldIEatTodayBack.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    @ModelAttribute("categories")
    public Map<String, String> categories() {
        Map<String, String> categories = new LinkedHashMap<>();

        for (CategoryTypes type : CategoryTypes.values()) {
            categories.put(type.name(), type.getDescription());
        }

        return categories;
    }

    @GetMapping("/recommendation")
    public String recommendation(HttpServletRequest request,
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
    public String recommendationResult(HttpServletRequest request,
                                       @ModelAttribute RecommendationRequestDto recommendationRequestDto,
                                       Model model) {
        HttpSession session = request.getSession(false);

        log.info("lat: {}", recommendationRequestDto.getLatitude());
        log.info("long: {}", recommendationRequestDto.getLongitude());



        for (String category : recommendationRequestDto.getCategories()) {
            log.info("category: {}", category);
        }

        if(session != null) {
            Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
            model.addAttribute("member", loginMember);
        }

        RecommendationResultDto recommendationResultDto = recommendationService.recommendation(recommendationRequestDto);
        model.addAttribute(recommendationResultDto);

        System.out.println("recommendationResult = " + recommendationResultDto);

        return "page/recommendation";
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
