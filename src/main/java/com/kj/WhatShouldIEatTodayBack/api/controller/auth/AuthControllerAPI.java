package com.kj.WhatShouldIEatTodayBack.api.controller.auth;

import com.kj.WhatShouldIEatTodayBack.dto.memberDTO.MemberCheckResponseDto;
import com.kj.WhatShouldIEatTodayBack.dto.memberDTO.MemberEditRequestDto;
import com.kj.WhatShouldIEatTodayBack.dto.memberDTO.MemberRequestDto;
import com.kj.WhatShouldIEatTodayBack.dto.memberDTO.MemberResponseDto;
import com.kj.WhatShouldIEatTodayBack.api.service.AuthServiceAPI;
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
public class AuthControllerAPI {

    private final AuthServiceAPI authServiceAPI;

    @RequestMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody MemberRequestDto memberRequestDto) {
        log.info("register request is arrived!");
        HttpStatus httpStatus;
        String message = "FAILURE";
        try {
            authServiceAPI.register(memberRequestDto);
            httpStatus = HttpStatus.OK;
            message = "SUCCESS";

        } catch(Exception e) {
            httpStatus = HttpStatus.UNAUTHORIZED;
        }

        return new ResponseEntity<String>(message,httpStatus);
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/login/loginSuccess")
    @ResponseBody
    @CrossOrigin(origins = "https://whatishouldeat.com")
    public String loginSuccess(HttpServletRequest request) {
        log.info("request: ", request.toString());
        return "Success";
    }

    @RequestMapping(value = "/check")
    @ResponseBody
    public MemberCheckResponseDto check(Principal principal) {
        return authServiceAPI.getMemberCheck(principal);
    }

    @RequestMapping(value = "/getMember")
    @ResponseBody
    public MemberResponseDto getMember(Principal principal) {
        return authServiceAPI.getMember(principal);
    }

    @RequestMapping(value = "/editMember")
    @ResponseBody
    public ResponseEntity editMember(@RequestBody MemberEditRequestDto memberEditRequestDto) {
        log.info(memberEditRequestDto.toString());
        return authServiceAPI.editMember(memberEditRequestDto);
    }
}
