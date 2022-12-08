package com.kj.WhatShouldIEatTodayBack.controller.recommendation;

import com.kj.WhatShouldIEatTodayBack.controller.dto.RecommendationDto;
import com.kj.WhatShouldIEatTodayBack.domain.member.Member;
import com.kj.WhatShouldIEatTodayBack.enums.CategoryTypes;
import com.kj.WhatShouldIEatTodayBack.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
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
public class RecommendationController {

    @ModelAttribute("categories")
    public Map<String, String> categories() {
        Map<String, String> categories = new LinkedHashMap<>();
        categories.put("KOREAN", "한식");
        categories.put("WESTERN", "양식");
        categories.put("JAPANESE", "일식");
        categories.put("CHINESE", "중식");

        return categories;
    }

    @GetMapping("/recommendation")
    public String recommendation(HttpServletRequest request,
                                 @ModelAttribute RecommendationDto recommendationDto,
                                 Model model) {

        HttpSession session = request.getSession(false);

        if(session != null) {
            Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
            model.addAttribute("member", loginMember);
        }

        return "page/recommendation";
    }

    @PostMapping("/recommendation")
    public String recommendationResult(HttpServletRequest request, @ModelAttribute RecommendationDto recommendationDto, Model model) {
        HttpSession session = request.getSession(false);

        List<String> categories = recommendationDto.getCategories();
        for (String category : categories) {
            log.info("category: {}", category);
        }

        if(session != null) {
            Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
            model.addAttribute("member", loginMember);
        }

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
