package com.kj.WhatShouldIEatTodayBack.controller.auth;

import com.github.dockerjava.api.model.Bind;
import com.kj.WhatShouldIEatTodayBack.controller.dto.*;
import com.kj.WhatShouldIEatTodayBack.domain.member.Member;
import com.kj.WhatShouldIEatTodayBack.service.auth.AuthService;
import com.kj.WhatShouldIEatTodayBack.service.auth.MemberInfoDetailDto;
import com.kj.WhatShouldIEatTodayBack.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.Binding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute LoginFormDto loginFormDto) {
        return "page/auth/login";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginFormDto loginFormDto, BindingResult result,
                        @RequestParam(defaultValue = "/") String redirectURL,
                        HttpServletRequest request) {

        if(result.hasErrors()) {
            return "page/auth/login";
        }

        Member loginMember = authService.login(loginFormDto.getMemberEmail(), loginFormDto.getMemberPw());

        if(loginMember == null) {
            result.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "page/auth/login";
        }

        // 로그인 성공 처리
        // 세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
        HttpSession session = request.getSession();

        // 세션에 로그인 회원 정보를 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:" + redirectURL;
    }

    @GetMapping("/register")
    public String registerForm(@ModelAttribute RegisterFormDto registerFormDto) {
        return "page/auth/register";
    }

    @PostMapping("/register")
    public String register(@Validated @ModelAttribute RegisterFormDto registerFormDto, BindingResult result) {

        if(!authService.checkMemberIsUnique(registerFormDto, result)) {
            result.rejectValue("memberEmail", "duplicate", "이미 존재하는 아이디입니다.");
        }

        if(result.hasErrors()) {
            return "page/auth/register";
        }

        authService.register(registerFormDto);

        return "redirect:/";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if(session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }

    @GetMapping("/checkUser")
    public String checkPasswordForm(@ModelAttribute CheckFormDto checkFormDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        checkFormDto.setMemberEmail(member.getMemberEmail());
        return "auth/checkPassword";
    }


    @PostMapping("/checkUser")
    public String checkPassword(@ModelAttribute CheckFormDto checkFormDto) {
        Member loginMember = authService.login(checkFormDto.getMemberEmail(), checkFormDto.getMemberPw());
        if(loginMember == null) {
            return "page/checkPassword";
        }


        return "redirect:/auth/editUser";
    }

//    @GetMapping("/editUser")
//    public String editUserForm(@ModelAttribute EditUserFormDto editUserFormDto, HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
//        if(member == null) {
//            return "redirect:/";
//        }
//
//        editUserFormDto.setMemberEmail(member.getMemberEmail());
//        editUserFormDto.setMemberName(member.getName());
//        editUserFormDto.setNickName(member.getNickName());
//        editUserFormDto.setPhoneNumber(member.getPhone1()+member.getPhone2()+member.getPhone3());
//
//
//        return "page/auth/editUser";
//    }

    @GetMapping("/editUser")
    public String editUserForm(Authentication authentication, @ModelAttribute MemberInfoDetailDto memberInfoDetailDto) {

        MemberInfoDetailDto detail = authService.getMemberInfoDetail(authentication);

        memberInfoDetailDto.setName(detail.getName());
        memberInfoDetailDto.setMemberEmail(detail.getMemberEmail());
        memberInfoDetailDto.setAuthorities(detail.getAuthorities());
        memberInfoDetailDto.setPhoneNumber(detail.getPhoneNumber());
        memberInfoDetailDto.setNickName(detail.getNickName());
        memberInfoDetailDto.setPhotosImagePath(detail.getPhotosImagePath());

        return "page/auth/editUser";
    }
//
//    @PostMapping("/editUser")
//    public String editUser(@ModelAttribute EditUserFormDto editUserFormDto, BindingResult result, HttpServletRequest request) {
//
//        if(!authService.checkMemberNicknameIsUnique(editUserFormDto.getMemberEmail(), editUserFormDto.getNickName(), result)) {
//            result.rejectValue("nickName", "duplicate", "이미 존재하는 닉네임입니다.");
//        }
//
//        if(result.hasErrors()) {
//            return "page/editUser";
//        }
//
//        log.info("test");
//
//        Member updatedMember = authService.updateUser(editUserFormDto);
//        request.getSession().setAttribute(SessionConst.LOGIN_MEMBER, updatedMember);
//
//
//        return "redirect:/";
//    }

    @GetMapping("/editUserNickName")
    public String editUserNickNameForm(@ModelAttribute EditNickNameDto editNickNameDto, BindingResult result) {
        return "page/auth/editUserNickName";
    }

    @PostMapping("/editUserNickName")
    public String editUserNickName(@Validated @ModelAttribute EditNickNameDto editNickNameDto, BindingResult result, Authentication authentication) {

        if(!authService.checkMemberNicknameIsUnique(editNickNameDto.getNickName(), result)) {
            result.rejectValue("nickName", "duplicate", "이미 존재하는 닉네임입니다.");
        }

        if(result.hasErrors()) {
            return "/page/auth/editUserNickName";
        }

        authService.changeNickname(authentication, editNickNameDto.getNickName());

        return "redirect:/auth/editUser";
    }
    @GetMapping("/editUserPhoneNumber")
    public String editPhoneNumberForm(@ModelAttribute EditPhoneNumberDto editPhoneNumberDto, BindingResult result) {
        return "page/auth/editUserPhoneNumber";
    }

    @PostMapping("/editUserPhoneNumber")
    public String editPhoneNumber(@Validated @ModelAttribute EditPhoneNumberDto editPhoneNumberDto, BindingResult result, Authentication authentication) {

        if(result.hasErrors()) {
            return "page/auth/editUserPhoneNumber";
        }

        authService.changePhoneNumber(authentication, editPhoneNumberDto.getPhoneNumber());

        return "redirect:/auth/editUser";
    }

    @GetMapping("/editUserPassword")
    public String editUserPasswordForm(@ModelAttribute EditPasswordDto editPasswordDto) {
        return "/page/auth/editUserPassword";
    }

    @PostMapping("/editUserPassword")
    public String editUserPassword(@Validated @ModelAttribute EditPasswordDto editPasswordDto, BindingResult result, Authentication authentication) {

        if(!editPasswordDto.getPassword().equals(editPasswordDto.getPasswordConfirm())) {
            result.rejectValue("password", "notEqual", "두 패스워드가 다릅니다.");
        }
        if(result.hasErrors()) {
            return "/page/auth/editUserPassword";
        }

        authService.changePassword(authentication, editPasswordDto.getPassword());

        return "redirect:/auth/editUser";
    }


    @GetMapping("/editUserPhoto")
    public String editUserPhotoForm() {
        return "/page/auth/editUserPhoto";
    }


    @PostMapping("/editUserPhoto")
    public String editUserPhoto(@RequestParam("photo") MultipartFile multipartFile, Authentication authentication) {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        authService.changePhoto(authentication, fileName, multipartFile);
        return "redirect:/auth/editUser";
    }
}
