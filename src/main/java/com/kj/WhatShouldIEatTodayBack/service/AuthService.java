package com.kj.WhatShouldIEatTodayBack.service;

import com.kj.WhatShouldIEatTodayBack.controller.dto.RegisterFormDto;
import com.kj.WhatShouldIEatTodayBack.domain.Member;
import com.kj.WhatShouldIEatTodayBack.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

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


}
