package com.kj.WhatShouldIEatTodayBack.exception;

public class NoNearbyStoreException extends RuntimeException {

    public NoNearbyStoreException(String message, Throwable cause) {
        super(message, cause);
    }
}
