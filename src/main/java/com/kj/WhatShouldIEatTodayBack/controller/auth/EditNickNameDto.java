package com.kj.WhatShouldIEatTodayBack.controller.auth;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class EditNickNameDto {

    @NotBlank
    @Size(max = 15, message = "15글자 이하의 닉네임만 가능합니다.")
    private String nickName;
}
