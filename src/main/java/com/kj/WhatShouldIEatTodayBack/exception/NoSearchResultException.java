package com.kj.WhatShouldIEatTodayBack.exception;

public class NoSearchResultException extends RuntimeException {


    public NoSearchResultException() {
    }

    public NoSearchResultException(String message) {
        super(message);
    }

    public NoSearchResultException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSearchResultException(Throwable cause) {
        super(cause);
    }

    public NoSearchResultException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
