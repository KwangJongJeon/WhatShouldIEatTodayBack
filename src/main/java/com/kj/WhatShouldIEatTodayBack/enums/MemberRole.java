package com.kj.WhatShouldIEatTodayBack.enums;

import com.google.common.collect.Sets;
import javassist.Loader;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public enum MemberRole {
    USER(Sets.newHashSet()),
    ADMIN(Sets.newHashSet());

    public final Set<MemberPermission> permissions;

    public Set<MemberPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthority() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());

        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
