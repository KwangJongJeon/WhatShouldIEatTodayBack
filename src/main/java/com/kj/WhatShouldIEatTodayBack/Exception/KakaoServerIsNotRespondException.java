package com.kj.WhatShouldIEatTodayBack.Exception;

public class KakaoServerIsNotRespondException extends RuntimeException{
    public KakaoServerIsNotRespondException(String message) {
        super(message);
    }
}
