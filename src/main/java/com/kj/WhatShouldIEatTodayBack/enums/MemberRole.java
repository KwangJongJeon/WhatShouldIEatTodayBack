package com.kj.WhatShouldIEatTodayBack.enums;

import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;

import java.util.Set;


@RequiredArgsConstructor
public enum MemberRole {
    USER(Sets.newHashSet()),
    ADMIN(Sets.newHashSet());

    public final Set<MemberPermission> permissions;

    public Set<MemberPermission> getPermissions() {
        return permissions;
    }
}
