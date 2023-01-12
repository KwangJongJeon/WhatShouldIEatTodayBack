package com.kj.WhatShouldIEatTodayBack.security;

import com.kj.WhatShouldIEatTodayBack.domain.member.Member;
import com.kj.WhatShouldIEatTodayBack.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@RequiredArgsConstructor
@Service
public class JpaUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;


    @Override
    public CustomUserDetails loadUserByUsername(String memberEmail) throws UsernameNotFoundException {
        Supplier<UsernameNotFoundException> s = () -> new UsernameNotFoundException("Problem during authentication!");

        Member member = memberRepository.findByMemberEmail(memberEmail).orElseThrow(s);

        return new CustomUserDetails(member);
    }
}
