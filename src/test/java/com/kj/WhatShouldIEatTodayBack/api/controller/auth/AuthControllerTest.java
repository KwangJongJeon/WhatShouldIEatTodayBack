package com.kj.WhatShouldIEatTodayBack.api.controller.auth;

import com.kj.WhatShouldIEatTodayBack.api.controller.RecommendationController;
import com.kj.WhatShouldIEatTodayBack.domain.Member;
import com.kj.WhatShouldIEatTodayBack.domain.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@AutoConfigureMockMvc
@SpringBootTest
class AuthControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    RecommendationController recommendationController;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("회원가입이 정상적으로 진행된다")
    void registerIsWorkProperly() throws Exception {
        //given
        String memberRegisterData =
                "{\"memberEmail\": \"test@naver.com\", \n" +
                "\"memberPw\": \"password\",\n" +
                "\"memberName\": \"KJ\",\n" +
                "\"phoneNumber\": \"01012345678\"}";



        // when
        mockMvc.perform(
                post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(memberRegisterData))
                .andExpect(status().isOk())
                .andDo(print());

        List<Member> members = memberRepository.findAll();

        // then
        Member member = members.get(0);
        Assertions.assertThat(member).isNotNull();
    }

    @Test
    @DisplayName("잘못된 회원가입 정보가 들어올 경우 예외를 반환한다.")
    void wrongRegisterDataIsGiven() throws Exception {
        //given
        String wrongMemberRegisterData =
                "{\"memberEmail\": \"test@naver.com\", \n" +
                        "\"memberPw\": \"password\",\n" +
                        "\"memberName\": \"KJ\",\n}" ;



        // when, then
        mockMvc.perform(
                post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(wrongMemberRegisterData))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

}