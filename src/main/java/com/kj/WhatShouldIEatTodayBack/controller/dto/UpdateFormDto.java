package com.kj.WhatShouldIEatTodayBack.controller.dto;

import com.kj.WhatShouldIEatTodayBack.validator.Password;
import lombok.Data;
import org.checkerframework.common.aliasing.qual.Unique;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateFormDto {

    @NotBlank
    @Unique
    private String memberEmail;

    @Password
    private String memberPw;

    @NotBlank
    private String memberNickName;

}
