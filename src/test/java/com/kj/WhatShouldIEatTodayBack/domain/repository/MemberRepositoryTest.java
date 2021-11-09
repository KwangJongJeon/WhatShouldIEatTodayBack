package com.kj.WhatShouldIEatTodayBack.domain.repository;

import com.kj.WhatShouldIEatTodayBack.domain.Member;
import lombok.NoArgsConstructor;
import org.aspectj.lang.annotation.After;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @AfterEach
    void cleanup() {
        memberRepository.deleteAll();
    }

    @DisplayName("DB에 멤버가 성공적으로 회원가입 된다.")
    @Test
    void save_user() {
        // given
        String email = "test@test.com";
        String password = "password";

        memberRepository.save(Member.builder()
            .memberEmail(email)
            .memberPw(password)
            .build()
        );

        // when
        List<Member> memberList = memberRepository.findAll();

        // then
        Member member = memberList.get(0);
        System.out.println("member.getMemberEmail() = " + member.toString());
        Assertions.assertThat(member.getMemberEmail()).isEqualTo(email);
        Assertions.assertThat(member.getMemberPw()).isEqualTo(password);
    }
}