package com.kj.WhatShouldIEatTodayBack.api.controller.auth;

import com.kj.WhatShouldIEatTodayBack.dto.memberDTO.MemberCheckResponseDto;
import com.kj.WhatShouldIEatTodayBack.dto.memberDTO.MemberRequestDto;
import com.kj.WhatShouldIEatTodayBack.dto.memberDTO.MemberResponseDto;
import com.kj.WhatShouldIEatTodayBack.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/api/auth")
public class AuthController {

    private final AuthService authService;

    @RequestMapping(value = "/register")
    public ResponseEntity register(@RequestBody MemberRequestDto memberRequestDto) {
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

        return new ResponseEntity<String>(message,httpStatus);
    }

    @GetMapping(value = "/loginSuccess")
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:3000")
    public String loginSuccess(HttpServletRequest request) {
        log.info("request: ", request.toString());
        return "Success";
    }

    @RequestMapping(value = "/check")
    @ResponseBody
    public MemberCheckResponseDto check(Principal principal) {
        return authService.getMemberCheck(principal);
    }
}
