package com.kj.WhatShouldIEatTodayBack.service;

import com.kj.WhatShouldIEatTodayBack.domain.Member;
import com.kj.WhatShouldIEatTodayBack.domain.repository.MemberRepository;
import com.kj.WhatShouldIEatTodayBack.dto.memberDTO.MemberRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Long register(MemberRequestDto memberRequestDto) {
        Member member = memberRequestDto.toEntityExceptPassword();
        member.encodePassword(passwordEncoder.encode(memberRequestDto.getMemberPw()));
        return memberRepository.save(member).getMemberId();
    }
}
