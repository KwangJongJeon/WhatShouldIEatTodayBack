package com.kj.WhatShouldIEatTodayBack.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberPermission {
    USER_POST_READ("user:read"),
    USER_POST_WRITE("user:write"),
    RECOMMENDATION_READ("recommendation:write"),
    RECOMMENDATION_WRITE("recommendation:write");

    private final String permission;
}
