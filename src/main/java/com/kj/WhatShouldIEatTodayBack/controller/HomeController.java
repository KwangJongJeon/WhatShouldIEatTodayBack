package com.kj.WhatShouldIEatTodayBack.controller;


import com.kj.WhatShouldIEatTodayBack.controller.dto.LoginFormDto;
import com.kj.WhatShouldIEatTodayBack.domain.Member;
import com.kj.WhatShouldIEatTodayBack.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);

        // 로그인 되어있을 경우
        if(session != null) {
            Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
            log.info("nickName = {}", loginMember.getNickName());
            model.addAttribute("member", loginMember);
        }

        return "page/home";
    }

    @GetMapping("/test")
    public String test() {
        return "page/home";
    }

    @GetMapping("/test2")
    public String test2() {
        return "fragment/footer";
    }

    @GetMapping("/test3")
    public String test3(@ModelAttribute LoginFormDto loginFormDto) {
        return "page/login";
    }

}
