package com.kj.WhatShouldIEatTodayBack.exception;

// TODO: 에러를 처리할 컨트롤러를 만들어야합니다.
public class InvalidCategoryName extends RuntimeException {

    public InvalidCategoryName(String message, Throwable cause) {
        super(message, cause);
    }
}
