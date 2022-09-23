package com.kj.WhatShouldIEatTodayBack.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.MessageFormat;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    private static final int MIN_SIZE = 8;
    private static final int MAX_SIZE = 20;
    private static final String regexPasswordPattern = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])";
    private static final String regexPasswordSize = "^[A-Za-z[0-9]$@$!%*#?&]{" + MIN_SIZE + "," + MAX_SIZE + "}$";

    @Override
    public void initialize(Password constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        boolean passwordPatternIsValid = password.matches(regexPasswordPattern);
        boolean passwordSizeIsValid = password.matches(regexPasswordSize);

        if(!passwordSizeIsValid) {
            addConstraintViolation(
                    context,
                    MessageFormat.format("비밀번호의 길이는 {0}자 ~ {1}자여야 합니다.", MIN_SIZE, MAX_SIZE)
            );

            return false;
        }
        else if(!passwordPatternIsValid) {
            addConstraintViolation(
                    context,
                    MessageFormat.format("숫자, 영문자, 특수문자를 포함한 비밀번호를 입력해주세요", MIN_SIZE, MAX_SIZE)
            );

            return false;
        }

        return true;
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String msg) {
        // 기본 메시지 비활성화
        context.disableDefaultConstraintViolation();

        // 새로운 메시지 추가
        context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
    }


}
