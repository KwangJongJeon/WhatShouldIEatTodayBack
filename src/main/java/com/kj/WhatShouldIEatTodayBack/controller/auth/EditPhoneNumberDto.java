package com.kj.WhatShouldIEatTodayBack.controller.auth;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class EditPhoneNumberDto {

    @NotBlank
    @Pattern(regexp = "[0-9]{10,11}", message = "10~11자리 숫자만 입력 가능합니다.")
    private String phoneNumber;
}
