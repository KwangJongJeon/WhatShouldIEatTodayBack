package com.kj.WhatShouldIEatTodayBack.controller.auth;

import com.kj.WhatShouldIEatTodayBack.controller.dto.LoginFormDto;
import com.kj.WhatShouldIEatTodayBack.controller.dto.RegisterFormDto;
import com.kj.WhatShouldIEatTodayBack.domain.Member;
import com.kj.WhatShouldIEatTodayBack.service.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthService authService;

    @Test
    void loginForm() throws Exception {
        mvc.perform(get("/auth/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("auth/login"))
                .andReturn();
    }

    @Test
    @Transactional
    void login() throws Exception {
        // given
        String memberEmail = registerMember();
        String password = "test123";

        LoginFormDto loginFormDto = new LoginFormDto();
        loginFormDto.setMemberEmail(memberEmail);
        loginFormDto.setMemberPw(password);

        // when, then
        mvc.perform(post("/auth/login")
                .flashAttr("loginFormDto", loginFormDto))
                .andExpect(status().is3xxRedirection())
                .andReturn();
    }

    @Test
    @Transactional
    void loginFail() throws Exception {
        // given
        String memberEmail = registerMember();
        String password = "wrongPassword";

        LoginFormDto loginFormDto = new LoginFormDto();
        loginFormDto.setMemberEmail(memberEmail);
        loginFormDto.setMemberPw(password);

        // when, then
        mvc.perform(post("/auth/login")
                        .flashAttr("loginFormDto", loginFormDto))
                .andExpect(status().isOk())
                .andExpect(view().name("auth/login"))
                .andReturn();
    }

    @Test
    void registerForm() throws Exception {
        mvc.perform(get("/auth/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("auth/register"))

                .andReturn();
    }

    @Test
    @Transactional
    void register() throws Exception {
        // given
        RegisterFormDto registerFormDto = new RegisterFormDto();
        registerFormDto.setMemberEmail("test");
        registerFormDto.setMemberPw("test123");
        registerFormDto.setMemberName("tester");
        registerFormDto.setPhoneNumber("01012345678");

        // when, then
        mvc.perform(post("/auth/register")
                .flashAttr("registerFormDto", registerFormDto))
                .andExpect(status().is3xxRedirection())
                .andReturn();
    }

    @Test
    @Transactional
    void registerFail() throws Exception {
        // given
        RegisterFormDto registerFormDto = new RegisterFormDto();
        registerFormDto.setMemberEmail("test");
        registerFormDto.setMemberName("tester");
        registerFormDto.setPhoneNumber("01012345678"); // password를 공백으로 한 상황

        // when, then
        mvc.perform(post("/auth/register")
                        .flashAttr("registerFormDto", registerFormDto))
                .andExpect(status().isOk())
                .andExpect(view().name("auth/register"))
                .andReturn();
    }

    private String registerMember() {

        RegisterFormDto registerFormDto = new RegisterFormDto();
        registerFormDto.setMemberEmail("test");
        registerFormDto.setMemberPw("test123");
        registerFormDto.setMemberName("tester");
        registerFormDto.setPhoneNumber("01012345678");

        Member member = registerFormDto.makeEntityExceptPassword();
        member.encodePassword(passwordEncoder.encode("tester123"));

        authService.register(registerFormDto);
        return registerFormDto.getMemberEmail();
    }
}