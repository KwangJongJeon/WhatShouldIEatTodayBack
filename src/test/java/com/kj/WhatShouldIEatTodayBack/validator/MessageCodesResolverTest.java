package com.kj.WhatShouldIEatTodayBack.validator;

import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;

public class MessageCodesResolverTest {

    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

    @Test
    void setMessageCodesResolver() {
        System.out.println(codesResolver.getClass());
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "member", "username", String.class);
        for (String messageCode : messageCodes) {
            System.out.println(messageCode);
        }
    }
}
