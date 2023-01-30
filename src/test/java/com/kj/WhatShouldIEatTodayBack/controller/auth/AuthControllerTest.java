package com.kj.WhatShouldIEatTodayBack.controller.auth;

import com.kj.WhatShouldIEatTodayBack.controller.dto.LoginFormDto;
import com.kj.WhatShouldIEatTodayBack.controller.dto.RegisterFormDto;
import com.kj.WhatShouldIEatTodayBack.domain.member.Member;
import com.kj.WhatShouldIEatTodayBack.service.auth.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AuthControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthService authService;

    String email = UUID.randomUUID().toString();
    String password = "test123!@#";


    @Test
    void loginForm() throws Exception {
        mvc.perform(get("/auth/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("page/auth/login"))
                .andReturn();
    }

    @Test
    void login() throws Exception {
        // given
        String memberEmail = registerMember();

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
                .andExpect(status().is3xxRedirection())
                .andReturn();
    }

    @Test
    void registerForm() throws Exception {
        mvc.perform(get("/auth/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("page/auth/register"))
                .andReturn();
    }

    @Test
    void register() throws Exception {
        // given
        RegisterFormDto registerFormDto = new RegisterFormDto();
        registerFormDto.setMemberEmail(email);
        registerFormDto.setMemberPw(password);
        registerFormDto.setMemberName("tester");
        registerFormDto.setPhoneNumber("01012345678");

        // when, then
        mvc.perform(post("/auth/register")
                .flashAttr("registerFormDto", registerFormDto))
                .andExpect(status().is3xxRedirection())
                .andReturn();
    }

    @Test
    void registerFail() throws Exception {
        // given
        RegisterFormDto registerFormDto = new RegisterFormDto();
        registerFormDto.setMemberEmail(email);
        registerFormDto.setMemberPw("wrongpassword");
        registerFormDto.setMemberName("tester");
        registerFormDto.setPhoneNumber("01012345678"); // 패스워드 규칙을 어긴 상황

        // when, then
        mvc.perform(post("/auth/register")
                        .flashAttr("registerFormDto", registerFormDto))
                .andExpect(status().isOk())
                .andExpect(view().name("page/auth/register"))
                .andReturn();
    }

    @Test
    void registerFailWithEmpty() throws Exception {
        // given
        RegisterFormDto registerFormDto = new RegisterFormDto();
        registerFormDto.setMemberEmail(email);
        registerFormDto.setMemberPw("");
        registerFormDto.setMemberName("tester");
        registerFormDto.setPhoneNumber("01012345678"); // 패스워드가 공백인 상황

        // when, then
        mvc.perform(post("/auth/register")
                        .flashAttr("registerFormDto", registerFormDto))
                .andExpect(status().isOk())
                .andExpect(view().name("page/auth/register"))
                .andReturn();
    }

    private String registerMember() {

        RegisterFormDto registerFormDto = new RegisterFormDto();
        registerFormDto.setMemberEmail(email);
        registerFormDto.setMemberPw(password);
        registerFormDto.setMemberName("tester");
        registerFormDto.setPhoneNumber("01012345678");

        Member member = registerFormDto.makeEntityExceptPassword();
        member.encodePassword(passwordEncoder.encode("tester123"));

        authService.register(registerFormDto);
        return registerFormDto.getMemberEmail();
    }
}