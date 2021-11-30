package com.kj.WhatShouldIEatTodayBack.api.controller.auth;

import com.kj.WhatShouldIEatTodayBack.api.controller.RecommendationController;
import com.kj.WhatShouldIEatTodayBack.domain.Member;
import com.kj.WhatShouldIEatTodayBack.domain.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @Test
    void registerIsWorkProperly() throws Exception {
        //given
        String userRegisterData =
                "{\"memberEmail\": \"test@naver.com\", \n" +
                "\"memberPw\": \"password\",\n" +
                "\"memberName\": \"KJ\",\n" +
                "\"phoneNumber\": \"01012345678\"}";



        //when
        mockMvc.perform(
                post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userRegisterData))
                .andExpect(status().isOk())
                .andDo(print());

        List<Member> members = memberRepository.findAll();

        // then
        Member member = members.get(0);
        System.out.println(member.getMemberPw());
//        Assertions.assertThat(member).isNotNull();
    }
}