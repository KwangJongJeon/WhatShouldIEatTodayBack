package com.kj.WhatShouldIEatTodayBack.service;

import com.kj.WhatShouldIEatTodayBack.controller.dto.EditUserFormDto;
import com.kj.WhatShouldIEatTodayBack.controller.dto.RegisterFormDto;
import com.kj.WhatShouldIEatTodayBack.controller.dto.UpdateFormDto;
import com.kj.WhatShouldIEatTodayBack.domain.Member;
import com.kj.WhatShouldIEatTodayBack.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final EntityManagerFactory entityManagerFactory;


    /**
     * @return null 로그인 실패
     */
    public Member login(String memberEmail, String memberPassword) {
        return memberRepository.findByMemberEmail(memberEmail)
                .filter(m -> passwordEncoder.matches(memberPassword, m.getMemberPw()))
                .orElse(null);
    }

    public void register(RegisterFormDto registerFormDto) {
        Member member = registerFormDto.makeEntityExceptPassword();
        member.encodePassword(passwordEncoder.encode(registerFormDto.getMemberPw()));
        memberRepository.save(member);
    }

    public boolean checkMemberIsUnique(RegisterFormDto registerFormDto, BindingResult result) {
        Member member = registerFormDto.makeEntityExceptPassword();

        if(!memberRepository.findByMemberEmail(member.getMemberEmail()).isEmpty()) {
            result.reject(null, "이미 존재하는 아이디입니다.");
            return false;
        }

        return true;
    }

    public boolean checkMemberNicknameIsUnique(String email, String nickName, BindingResult result) {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Member> resultList = em.createQuery("select m From Member m where m.nickName = :nickName", Member.class)
                .setParameter("nickName", nickName)
                .getResultList();

        if(resultList.isEmpty()) return true;

        result.reject(null, "이미 존재하는 닉네임입니다.");

        for (Member member : resultList) {
            log.info("memberEmail = {}", member);
        }
        return false;
    }

    @Transactional
    public Member updateUser(EditUserFormDto editUserFormDto) {
        Member member = memberRepository.findByMemberEmail(editUserFormDto.getMemberEmail()).get();
        member.changeNickName(editUserFormDto.getNickName());
        member.changePhoneNumber(editUserFormDto.getPhoneNumber());

        return member;
    }
}
