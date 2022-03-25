//package com.kj.WhatShouldIEatTodayBack.domain.repository;
//
//import com.kj.WhatShouldIEatTodayBack.domain.Member;
//import com.kj.WhatShouldIEatTodayBack.enums.MemberRole;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.*;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//class MemberRepositoryTest {
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @AfterEach
//    void cleanup() {
//        memberRepository.deleteAll();
//    }
//
//    @DisplayName("DB에 멤버가 성공적으로 회원가입 된다.")
//    @Test
//    void signUp() {
//        // given
//        String email = "test@test.com";
//        String password = "password";
//        String name = "KJ";
//        MemberRole role = MemberRole.USER;
//
//        memberRepository.save(Member.builder()
//                .memberEmail(email)
//                .memberPw(password)
//                .name(name)
//                .phone1("010")
//                .phone2("1234")
//                .phone3("5678")
//                .memberRole(role)
//                .build()
//        );
//
//        // when
//        List<Member> memberList = memberRepository.findAll();
//
//        // then
//        Member member = memberList.get(0);
//        System.out.println("member.getMemberEmail() = " + member.toString());
//        assertThat(member.getMemberEmail()).isEqualTo(email);
//        assertThat(member.getMemberPw()).isEqualTo(password);
//        assertThat(member.getName()).isEqualTo(name);
//    }
//
//    @DisplayName("멤버의 닉네임이 정상적으로 변경된다.")
//    @Test
//    void updateNickName() {
//        // given
//        String email = "test@test.com";
//        String password = "password";
//        String name = "KJ";
//        String expectedNickName = "nickname";
//        MemberRole role = MemberRole.USER;
//
//        memberRepository.save(Member.builder()
//                .memberEmail(email)
//                .memberPw(password)
//                .name(name)
//                .phone1("010")
//                .phone2("1234")
//                .phone3("5678")
//                .memberRole(role)
//                .build()
//        );
//
//        // when
//        List<Member> memberList = memberRepository.findAll();
//
//        // then
//        Member member = memberList.get(0);
//        member.changeNickName(expectedNickName);
//        assertThat(member.getNickName()).isEqualTo(expectedNickName);
//    }
//
//    @DisplayName("멤버의 패스워드가 정상적으로 변경된다.")
//    @Test
//    void updatePassword() {
//        // given
//        String email = "test@test.com";
//        String password = "password";
//        String name = "KJ";
//        String expectedPassword = "changedPassword";
//        MemberRole role = MemberRole.USER;
//
//        memberRepository.save(Member.builder()
//                .memberEmail(email)
//                .memberPw(password)
//                .name(name)
//                .phone1("010")
//                .phone2("1234")
//                .phone3("5678")
//                .memberRole(role)
//                .build()
//        );
//
//        // when
//        List<Member> memberList = memberRepository.findAll();
//
//        // then
//        Member member = memberList.get(0);
//        member.changePassword(expectedPassword);
//        assertThat(member.getMemberPw()).isEqualTo(expectedPassword);
//    }
//
//    @DisplayName("멤버의 역할이 정상적으로 변경된다.")
//    @Test
//    void updateRole() {
//        // given
//        String email = "test@test.com";
//        String password = "password";
//        String name = "KJ";
//        MemberRole role = MemberRole.USER;
//        MemberRole expectedRole = MemberRole.ADMIN;
//
//        memberRepository.save(Member.builder()
//                .memberEmail(email)
//                .memberPw(password)
//                .name(name)
//                .phone1("010")
//                .phone2("1234")
//                .phone3("5678")
//                .memberRole(role)
//                .build()
//        );
//
//        // when
//        List<Member> memberList = memberRepository.findAll();
//
//        // then
//        Member member = memberList.get(0);
//        member.changeMemberRole(expectedRole);
//        assertThat(member.getMemberRole()).isEqualTo(expectedRole);
//    }
//}