package com.kj.WhatShouldIEatTodayBack.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class LoginFormDto {

    @NotBlank
    private String memberEmail;
    @NotBlank
    private String memberPw;
}
