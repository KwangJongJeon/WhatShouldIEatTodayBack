package com.kj.WhatShouldIEatTodayBack.service;

import com.kj.WhatShouldIEatTodayBack.controller.dto.RegisterFormDto;
import com.kj.WhatShouldIEatTodayBack.domain.Member;
import com.kj.WhatShouldIEatTodayBack.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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


}
