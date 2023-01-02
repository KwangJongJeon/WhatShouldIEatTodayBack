package com.kj.WhatShouldIEatTodayBack.exception.recommendation;

/**
 * Recoomendation에서 잘못된 위도와 경도 정보가 넘어왔을 때 발생하는 예외
 */
public class CoordinateIsNotValid extends RuntimeException {

    public CoordinateIsNotValid() {
        super();
    }

    public CoordinateIsNotValid(String message) {
        super(message);
    }

    public CoordinateIsNotValid(String message, Throwable cause) {
        super(message, cause);
    }

    public CoordinateIsNotValid(Throwable cause) {
        super(cause);
    }

    protected CoordinateIsNotValid(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
