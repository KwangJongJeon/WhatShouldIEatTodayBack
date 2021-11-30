package com.kj.WhatShouldIEatTodayBack.api.controller.auth;

import com.kj.WhatShouldIEatTodayBack.dto.memberDTO.MemberRequestDto;
import com.kj.WhatShouldIEatTodayBack.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/auth")
public class AuthController {

    private final AuthService authService;

    @RequestMapping(value = "/register")
    public HttpStatus register(@RequestBody MemberRequestDto memberRequestDto) {
        HttpStatus httpStatus;
        try {
            authService.register(memberRequestDto);
            httpStatus = HttpStatus.OK;

        } catch(Exception e) {
            httpStatus = HttpStatus.UNAUTHORIZED;
            log.info("error! : " + e.getMessage());
        }
        return httpStatus;
    }
}
