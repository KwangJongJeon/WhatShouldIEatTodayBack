package com.kj.WhatShouldIEatTodayBack;

import com.kj.WhatShouldIEatTodayBack.controller.dto.RegisterFormDto;
import com.kj.WhatShouldIEatTodayBack.domain.repository.MemberRepository;
import com.kj.WhatShouldIEatTodayBack.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;
    private final AuthService authService;
    String email = "tester";
    String password = "test123!@#";

    /**
     * 테스트용 데이터
     */
    @PostConstruct
    public void init() {
        RegisterFormDto registerFormDto = new RegisterFormDto();

        registerFormDto.setMemberEmail(email);
        registerFormDto.setMemberPw(password);
        registerFormDto.setMemberName("tester");
        registerFormDto.setPhoneNumber("01012345678");

        authService.register(registerFormDto);
    }
}
