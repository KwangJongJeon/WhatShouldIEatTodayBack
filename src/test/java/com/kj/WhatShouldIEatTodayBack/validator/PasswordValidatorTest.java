package com.kj.WhatShouldIEatTodayBack.validator;

import com.kj.WhatShouldIEatTodayBack.controller.dto.RegisterFormDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

class PasswordValidatorTest {

    private Validator validator;
    private ValidatorFactory factory;

    private String EMAIL = UUID.randomUUID().toString();
    private String NAME = "TESTER";
    private String PHONE_NUMBER = "01012345678";

    @BeforeEach
    private void setUp() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterEach
    public void close() {
        factory.close();
    }

    @Test
    @DisplayName("패스워드의 사이즈가 8~20자리가 아니면 violation이 발생합니다")
    void sizeValid() {
        // given
        RegisterFormDto registerFormDto = new RegisterFormDto();
        registerFormDto.setMemberEmail(EMAIL);
        registerFormDto.setMemberPw("a0!");
        registerFormDto.setMemberName(NAME);
        registerFormDto.setPhoneNumber(PHONE_NUMBER);

        // when
        Set<ConstraintViolation<RegisterFormDto>> violations = validator.validate(registerFormDto);

        // then
        assertThat(violations).isNotEmpty();
    }

    @Test
    @DisplayName("패스워드에 영문자, 특수문자, 숫자가 포함되어있지 않으면 violation이 발생합니다.")
    void patternValid() {
        // given
        RegisterFormDto registerFormDto = new RegisterFormDto();
        registerFormDto.setMemberEmail(EMAIL);
        registerFormDto.setMemberPw("12345678");
        registerFormDto.setMemberName(NAME);
        registerFormDto.setPhoneNumber(PHONE_NUMBER);

        // when
        Set<ConstraintViolation<RegisterFormDto>> violations = validator.validate(registerFormDto);

        // then
        assertThat(violations).isNotEmpty();
    }

}