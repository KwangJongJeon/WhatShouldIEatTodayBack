package com.kj.WhatShouldIEatTodayBack.api.service;

import com.kj.WhatShouldIEatTodayBack.exception.UserSessionIsNotValid;
import com.kj.WhatShouldIEatTodayBack.domain.member.Member;
import com.kj.WhatShouldIEatTodayBack.domain.member.repository.MemberRepository;
import com.kj.WhatShouldIEatTodayBack.dto.memberDTO.MemberCheckResponseDto;
import com.kj.WhatShouldIEatTodayBack.dto.memberDTO.MemberEditRequestDto;
import com.kj.WhatShouldIEatTodayBack.dto.memberDTO.MemberRequestDto;
import com.kj.WhatShouldIEatTodayBack.dto.memberDTO.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class AuthServiceAPI {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Long register(MemberRequestDto memberRequestDto) {
        Member member = memberRequestDto.toEntityExceptPassword();
        member.encodePassword(passwordEncoder.encode(memberRequestDto.getMemberPw()));
        return memberRepository.save(member).getId();
    }

    public MemberCheckResponseDto getMemberCheck(Principal principal) {
        try {
            log.info("1");
            Optional<Member> member = memberRepository.findByMemberEmail(principal.getName());
            log.info("2");
            log.info("member: " + member.get().getMemberEmail());
            MemberCheckResponseDto memberCheckResponseDto = new MemberCheckResponseDto(member.get());
            return memberCheckResponseDto;
        } catch (Exception e) {
            throw new UserSessionIsNotValid("유저 세션이 올바르지 않습니다.");
        }
    }

    public MemberResponseDto getMember(Principal principal) {
        try {
            Optional<Member> member = memberRepository.findByMemberEmail(principal.getName());
            log.info("member: " + member.get().getMemberEmail());
            MemberResponseDto memberResponseDto = new MemberResponseDto(member.get());
            return memberResponseDto;
        } catch (Exception e) {
            throw new UserSessionIsNotValid("유저 세션이 올바르지 않습니다.");
        }
    }

    @Transactional
    public ResponseEntity editMember(MemberEditRequestDto memberEditRequestDto) {
        try {
            Optional<Member> member = memberRepository.findByMemberEmail(memberEditRequestDto.getMemberEmail());
            member.get().changeNickName(memberEditRequestDto.getNickName());
            member.get().changePhoneNumber(memberEditRequestDto.getPhoneNumber());
            return new ResponseEntity("SUCCESS", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }
}
