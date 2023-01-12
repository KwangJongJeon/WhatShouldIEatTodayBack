package com.kj.WhatShouldIEatTodayBack.security;

import com.kj.WhatShouldIEatTodayBack.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final Member member;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return member.getMemberRole().getGrantedAuthority();
    }

    @Override
    public String getPassword() {
        return member.getMemberPw();
    }

    @Override
    public String getUsername() {
        return member.getMemberEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return member.getIsEnable();
    }

    @Override
    public boolean isAccountNonLocked() {
        return member.getIsEnable();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return member.getIsEnable();
    }

    @Override
    public boolean isEnabled() {
        return member.getIsEnable();
    }
}
