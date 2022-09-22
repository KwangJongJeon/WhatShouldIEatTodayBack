package com.kj.WhatShouldIEatTodayBack.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;


// TODO: 나중에 DTO 폴더를 따로 만들어 분리해야합니다.
@Data
public class LoginFormDto {

    @NotBlank
    private String memberEmail;
    @NotBlank
    private String memberPw;
}
