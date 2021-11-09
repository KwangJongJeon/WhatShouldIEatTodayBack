package com.kj.WhatShouldIEatTodayBack.api.controller;

import com.kj.WhatShouldIEatTodayBack.domain.Member;
import com.kj.WhatShouldIEatTodayBack.domain.repository.MemberRepository;
import com.kj.WhatShouldIEatTodayBack.enums.MemberRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@RequiredArgsConstructor
@Controller
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @GetMapping("/login")
    public String getLoginView() {
        return "login";
    }

    @GetMapping("/init")
    public String createAdmin(@ModelAttribute Member member){

        String email = "user@test.com";
        String password = "password";
        String name = "KJ";
        MemberRole role = MemberRole.USER;

        memberRepository.save(Member.builder()
                .memberEmail(email)
                .memberPw(passwordEncoder.encode(password))
                .name(name)
                .memberRole(role)
                .build()
        );

        String email2 = "admin@test.com";
        String password2 = "password";
        String name2 = "KJ";
        MemberRole role2 = MemberRole.ADMIN;

        memberRepository.save(Member.builder()
                .memberEmail(email2)
                .memberPw(passwordEncoder.encode(password))
                .name(name2)
                .memberRole(role2)
                .build()
        );

        return "redirect:/login";
    }

    @GetMapping("/loginSuccess")
    public String getSuccessView() {
        return "loginSuccess";
    }
}
