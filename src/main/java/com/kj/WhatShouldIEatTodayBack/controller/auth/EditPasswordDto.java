package com.kj.WhatShouldIEatTodayBack.controller.auth;

import com.kj.WhatShouldIEatTodayBack.validator.Password;
import lombok.Data;


@Data
public class EditPasswordDto {
    @Password
    private String password;

    @Password
    private String passwordConfirm;
}
