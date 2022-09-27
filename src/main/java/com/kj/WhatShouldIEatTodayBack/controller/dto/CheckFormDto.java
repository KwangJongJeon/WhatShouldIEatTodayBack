package com.kj.WhatShouldIEatTodayBack.controller.dto;

import com.kj.WhatShouldIEatTodayBack.validator.Password;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CheckFormDto {

    @NotBlank
    private String memberEmail;

    @Password
    private String memberPw;
}
