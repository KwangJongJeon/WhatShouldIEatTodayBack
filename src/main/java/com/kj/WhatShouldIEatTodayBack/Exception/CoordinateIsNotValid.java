package com.kj.WhatShouldIEatTodayBack.Exception;

/**
 * Recoomendation에서 잘못된 위도와 경도 정보가 넘어왔을 때 발생하는 예외
 */
public class CoordinateIsNotValid extends RuntimeException {

    public CoordinateIsNotValid(String message, Throwable cause) {
        super(message, cause);
    }

    public CoordinateIsNotValid(String message) {
        super(message);
    }
}
