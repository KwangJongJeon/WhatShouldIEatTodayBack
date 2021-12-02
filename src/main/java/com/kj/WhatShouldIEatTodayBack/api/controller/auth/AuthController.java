package com.kj.WhatShouldIEatTodayBack.api.controller.auth;

import com.kj.WhatShouldIEatTodayBack.dto.memberDTO.MemberRequestDto;
import com.kj.WhatShouldIEatTodayBack.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/auth")
public class AuthController {

    private final AuthService authService;

    @RequestMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody MemberRequestDto memberRequestDto) {
        // TODO: 메시지 단순 String이 아니라 MemberResponseDto를 사용해 반환하도록 할 필요가 있음.
        log.info("register request is arrived!");
        HttpStatus httpStatus;
        String message = "FAILURE";
        try {
            authService.register(memberRequestDto);
            httpStatus = HttpStatus.OK;
            message = "SUCCESS";

        } catch(Exception e) {
            httpStatus = HttpStatus.UNAUTHORIZED;
        }

        return new ResponseEntity<String>(message, httpStatus);
    }

}
