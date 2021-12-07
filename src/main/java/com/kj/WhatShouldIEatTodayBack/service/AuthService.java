package com.kj.WhatShouldIEatTodayBack.service;

import com.kj.WhatShouldIEatTodayBack.Exception.UserSessionIsNotValid;
import com.kj.WhatShouldIEatTodayBack.domain.Member;
import com.kj.WhatShouldIEatTodayBack.domain.repository.MemberRepository;
import com.kj.WhatShouldIEatTodayBack.dto.memberDTO.MemberCheckResponseDto;
import com.kj.WhatShouldIEatTodayBack.dto.memberDTO.MemberRequestDto;
import com.kj.WhatShouldIEatTodayBack.dto.memberDTO.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Long register(MemberRequestDto memberRequestDto) {
        Member member = memberRequestDto.toEntityExceptPassword();
        member.encodePassword(passwordEncoder.encode(memberRequestDto.getMemberPw()));
        return memberRepository.save(member).getMemberId();
    }

    public MemberCheckResponseDto getMemberCheck(Principal principal) {
        try {
            Optional<Member> member = memberRepository.findByMemberEmail(principal.getName());
            log.info("member: " + member.get().getMemberEmail());
            MemberCheckResponseDto MemberCheckResponseDto = new MemberCheckResponseDto(member.get());
            return MemberCheckResponseDto;
        } catch (Exception e) {
            throw new UserSessionIsNotValid("유저 세션이 올바르지 않습니다.");
        }
    }
}
