package com.kj.WhatShouldIEatTodayBack.exception;

/**
 * 유효하지 않은 세션이 들어왔을 경우에 나타나는 에러
 */
public class UserSessionIsNotValid extends RuntimeException{

    public UserSessionIsNotValid(String message) {
        super(message);
    }
}
