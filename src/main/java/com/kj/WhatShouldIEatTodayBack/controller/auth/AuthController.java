package com.kj.WhatShouldIEatTodayBack.controller.auth;

import com.kj.WhatShouldIEatTodayBack.controller.dto.LoginFormDto;
import com.kj.WhatShouldIEatTodayBack.controller.dto.RegisterFormDto;
import com.kj.WhatShouldIEatTodayBack.domain.Member;
import com.kj.WhatShouldIEatTodayBack.service.AuthService;
import com.kj.WhatShouldIEatTodayBack.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute LoginFormDto loginFormDto) {
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginFormDto loginFormDto, BindingResult result,
                        @RequestParam(defaultValue = "/") String redirectURL,
                        HttpServletRequest request) {

        if(result.hasErrors()) {
            return "auth/login";
        }

        Member loginMember = authService.login(loginFormDto.getMemberEmail(), loginFormDto.getMemberPw());

        if(loginMember == null) {
            result.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "auth/login";
        }

        // 로그인 성공 처리
        // 세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
        HttpSession session = request.getSession();

        // 세션에 로그인 회원 정보를 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:" + redirectURL;
    }

    @GetMapping("/register")
    public String registerForm(@ModelAttribute RegisterFormDto registerFormDto) {
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Validated @ModelAttribute RegisterFormDto registerFormDto, BindingResult result) {

        authService.checkMemberIsUnique(registerFormDto, result);

        if(result.hasErrors()) {
            return "auth/register";
        }

        authService.register(registerFormDto);

        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if(session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }
}
