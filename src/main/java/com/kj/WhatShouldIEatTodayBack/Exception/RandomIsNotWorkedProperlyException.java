package com.kj.WhatShouldIEatTodayBack.Exception;

// TODO: 에러를 처리할 컨트롤러를 만들어야합니다.
public class RandomIsNotWorkedProperlyException extends RuntimeException {

    public RandomIsNotWorkedProperlyException(String message, Throwable cause) {
        super(message, cause);
    }
}
