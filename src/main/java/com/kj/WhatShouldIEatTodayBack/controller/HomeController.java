package com.kj.WhatShouldIEatTodayBack.controller;


import com.kj.WhatShouldIEatTodayBack.domain.Member;
import com.kj.WhatShouldIEatTodayBack.session.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);

        // 로그인 되어 있을 경우
        if(session == null) {
            return "home";
        }

        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        model.addAttribute("member", loginMember);
        return "loginHome";
    }
}
