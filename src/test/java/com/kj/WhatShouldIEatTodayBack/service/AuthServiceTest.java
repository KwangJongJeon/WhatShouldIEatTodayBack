package com.kj.WhatShouldIEatTodayBack.service;

import com.kj.WhatShouldIEatTodayBack.controller.dto.RegisterFormDto;
import com.kj.WhatShouldIEatTodayBack.domain.Member;
import com.kj.WhatShouldIEatTodayBack.domain.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class AuthServiceTest {

    @Autowired
    AuthService authService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    String email = UUID.randomUUID().toString();
    String password = UUID.randomUUID().toString();

    @Test
    @Transactional
    @DisplayName("중복된 아이디가 있을 경우 회원가입이 거부된다.")
    void checkMemberIsUnique() {
        // given
        BindingResult result = new BeanPropertyBindingResult(null, "controller");

        RegisterFormDto registerFormDto = new RegisterFormDto();
        registerFormDto.setMemberEmail(email);
        registerFormDto.setMemberPw(password);
        registerFormDto.setMemberName("tester");
        registerFormDto.setPhoneNumber("01012345678");

        Member member = registerFormDto.makeEntityExceptPassword();
        member.encodePassword(passwordEncoder.encode(password));

        authService.register(registerFormDto);

        assertThat(authService.checkMemberIsUnique(registerFormDto, result)).isEqualTo(false);
    }

    @Test
    @Transactional
    @DisplayName("정상적으로 회원가입 서비스가 진행된다")
    void register() {

        // given
        RegisterFormDto registerFormDto = new RegisterFormDto();
        registerFormDto.setMemberEmail(email);
        registerFormDto.setMemberPw(password);
        registerFormDto.setMemberName("tester");
        registerFormDto.setPhoneNumber("01012345678");

        Member member = registerFormDto.makeEntityExceptPassword();
        member.encodePassword(passwordEncoder.encode(password));

        // when
        authService.register(registerFormDto);


        // then
        assertThat(memberRepository.findByMemberEmail(email).get().getMemberEmail()).isEqualTo(email);
    }

    @Test
    @Transactional
    @DisplayName("정상적으로 로그인 서비스가 진행된다.")
    void login() {

        // given
        RegisterFormDto registerFormDto = new RegisterFormDto();
        registerFormDto.setMemberEmail(email);
        registerFormDto.setMemberPw(password);
        registerFormDto.setMemberName("tester");
        registerFormDto.setPhoneNumber("01012345678");

        Member member = registerFormDto.makeEntityExceptPassword();
        member.encodePassword(passwordEncoder.encode(password));

        authService.register(registerFormDto);
        Member expected = memberRepository.findByMemberEmail(email).get();

        // when
        Member loginMember = authService.login(email, password);

        assertThat(loginMember).isEqualTo(expected);
    }
}