package com.kj.WhatShouldIEatTodayBack.api.service;

import com.kj.WhatShouldIEatTodayBack.domain.member.repository.MemberDetailsDTO;
import com.kj.WhatShouldIEatTodayBack.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 유저의 인증에 필요한 클래스
 */
@RequiredArgsConstructor
@Service
public class MemberDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;


    @Override
    public UserDetails loadUserByUsername(String memberEmail) throws UsernameNotFoundException {
        MemberDetailsDTO memberDetailsDTO = memberRepository.findByMemberEmail(memberEmail)
                .stream().findFirst()
                .map(u -> new MemberDetailsDTO(u, u.getMemberRole().getGrantedAuthority()))
                .orElseThrow(() -> new UsernameNotFoundException(memberEmail + " is not founded"));

        return memberDetailsDTO;
    }
}
