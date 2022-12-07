package com.kj.WhatShouldIEatTodayBack.controller.recommendation;

import com.kj.WhatShouldIEatTodayBack.domain.member.Member;
import com.kj.WhatShouldIEatTodayBack.session.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class RecommendationController {

    @GetMapping("/recommendation")
    public String recommendation(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        if(session != null) {
            Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
            model.addAttribute("member", loginMember);
        }

        return "page/recommendation";
    }

    @PostMapping("/recommendation")
    public String recommendationResult(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

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
