package com.kj.WhatShouldIEatTodayBack.service;

import com.kj.WhatShouldIEatTodayBack.controller.dto.EditUserFormDto;
import com.kj.WhatShouldIEatTodayBack.controller.dto.RegisterFormDto;
import com.kj.WhatShouldIEatTodayBack.domain.Member;
import com.kj.WhatShouldIEatTodayBack.domain.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;


import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AuthServiceTest {

    @Autowired
    AuthService authService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    String email = "testTester";
    String password = "tester123!@#";

    @Test
    @Transactional
    @DisplayName("중복된 아이디가 있을 경우 회원가입이 거부된다.")
    void checkMemberIsUnique() {
        // given
        BindingResult result = new BeanPropertyBindingResult(null, "controller");

        RegisterFormDto registerFormDto = getRegisterFormDto(email);

        authService.register(registerFormDto);

        // when, then
        assertThat(authService.checkMemberIsUnique(registerFormDto, result)).isEqualTo(false);
    }


    @Test
    @Transactional
    @DisplayName("정상적으로 회원가입 서비스가 진행된다")
    void register() {

        // given
        RegisterFormDto registerFormDto = getRegisterFormDto(email);

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
        RegisterFormDto registerFormDto = getRegisterFormDto(email);

        authService.register(registerFormDto);
        Member expected = memberRepository.findByMemberEmail(email).get();

        // when
        Member loginMember = authService.login(email, password);

        assertThat(loginMember).isEqualTo(expected);
    }

    @Test
    @Transactional
    @DisplayName("정상적으로 회원의 정보가 변경된다")
    void updateUser() {
        // given
        authService.register(getRegisterFormDto(email));

        String expectedNickName = "expectedNickName";
        String expectedPhone = "01000000000";

        EditUserFormDto editUserFormDto = new EditUserFormDto();
        editUserFormDto.setMemberEmail(email);
        editUserFormDto.setNickName(expectedNickName);
        editUserFormDto.setMemberPw(password);
        editUserFormDto.setPhoneNumber(expectedPhone);

        // when
        Member member = authService.updateUser(editUserFormDto);

        // then
        assertThat(member.getNickName()).isEqualTo(expectedNickName);
        assertThat(member.getPhone1()+member.getPhone2()+member.getPhone3()).isEqualTo(expectedPhone);
    }


    /**
     * Default
     * email    - testTester
     * pw       - tester123!@#
     * name     - tester
     * phone    - 01012345678
     */
    private RegisterFormDto getRegisterFormDto(String emailInput) {
        RegisterFormDto registerFormDto = new RegisterFormDto();
        registerFormDto.setMemberEmail(emailInput);
        registerFormDto.setMemberPw(password);
        registerFormDto.setMemberName("tester");
        registerFormDto.setPhoneNumber("01012345678");

        Member member = registerFormDto.makeEntityExceptPassword();
        member.encodePassword(passwordEncoder.encode(password));
        return registerFormDto;
    }
}