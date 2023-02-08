package com.kj.WhatShouldIEatTodayBack.controller;


import com.kj.WhatShouldIEatTodayBack.controller.dto.LoginFormDto;
import com.kj.WhatShouldIEatTodayBack.service.auth.AuthService;
import com.kj.WhatShouldIEatTodayBack.service.auth.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {

    private final AuthService authService;

    @GetMapping("/")
    public String home(Authentication authentication, Model model) {

        MemberInfoDto memberInfoDto = authService.getMemberInfo(authentication);
        model.addAttribute("memberInfoDto", memberInfoDto);

        return "page/home";
    }
}
