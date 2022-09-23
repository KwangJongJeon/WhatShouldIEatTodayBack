package com.kj.WhatShouldIEatTodayBack.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
    String message() default "패스워드가 올바르지 않습니다.";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
