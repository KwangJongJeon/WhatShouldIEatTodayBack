package com.kj.WhatShouldIEatTodayBack.exception;


/**
 * 카카오 서버가 응답하지 않을 경우를 위한 예외
 * 응답하지 않을경우 Http 상태코드 '502' BAD_GATEWAY를 반환한다
 */
public class KakaoServerIsNotRespondException extends RuntimeException{

    /**
     * @param message 에러 메세지
     */
    public KakaoServerIsNotRespondException(String message) {
        super(message);
    }
}
