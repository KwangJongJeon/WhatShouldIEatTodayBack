//package com.kj.WhatShouldIEatTodayBack.api.controller.auth;
//
//import com.kj.WhatShouldIEatTodayBack.dto.memberDTO.MemberRequestDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RequiredArgsConstructor
//@RequestMapping(value = "/api/auth")
//@RestController
//public class AuthController {
//
//    @PostMapping(value = "/register")
//     public void register(@RequestBody MemberRequestDto memberRequestDto) {
//
//     }
//}


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
public class AuthControllerTwo {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @GetMapping("/api/auth/login")
    public String getLoginView() {
        return "login";
    }

    @GetMapping("/init")
    public String createAdmin(@ModelAttribute Member member){

        String email = "test@test.com";
        String password = passwordEncoder.encode("password");
        String name = "KJ";
        MemberRole role = MemberRole.USER;

        memberRepository.save(Member.builder()
                .memberEmail(email)
                .memberPw(password)
                .name(name)
                .phone1("010")
                .phone2("1234")
                .phone3("5678")
                .memberRole(role)
                .build()
        );

        String email2 = "admin@test.com";
        String password2 = passwordEncoder.encode("password");
        String name2 = "KJ";
        MemberRole role2 = MemberRole.ADMIN;

        memberRepository.save(Member.builder()
                .memberEmail(email2)
                .memberPw(password2)
                .name(name2)
                .phone1("010")
                .phone2("1234")
                .phone3("5678")
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
