package com.kj.WhatShouldIEatTodayBack.service;

import com.kj.WhatShouldIEatTodayBack.controller.dto.RegisterFormDto;
import com.kj.WhatShouldIEatTodayBack.domain.Member;
import com.kj.WhatShouldIEatTodayBack.domain.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class AuthServiceTest {

    @Autowired
    AuthService authService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @Transactional
    void register() {

        // given
        RegisterFormDto registerFormDto = new RegisterFormDto();
        registerFormDto.setMemberEmail("test");
        registerFormDto.setMemberPw("test123");
        registerFormDto.setMemberName("tester");
        registerFormDto.setPhoneNumber("01012345678");

        Member member = registerFormDto.makeEntityExceptPassword();
        member.encodePassword(passwordEncoder.encode("tester123"));

        // when
        authService.register(registerFormDto);


        // then
        assertThat(memberRepository.findByMemberEmail("test").get().getMemberEmail()).isEqualTo("test");
    }

    @Test
    @Transactional
    void login() {

        // given
        RegisterFormDto registerFormDto = new RegisterFormDto();
        registerFormDto.setMemberEmail("test");
        registerFormDto.setMemberPw("test123");
        registerFormDto.setMemberName("tester");
        registerFormDto.setPhoneNumber("01012345678");

        Member member = registerFormDto.makeEntityExceptPassword();
        member.encodePassword(passwordEncoder.encode("tester123"));

        authService.register(registerFormDto);
        Member expected = memberRepository.findByMemberEmail("test").get();

        // when
        Member loginMember = authService.login("test", "test123");

        assertThat(loginMember).isEqualTo(expected);
    }


}