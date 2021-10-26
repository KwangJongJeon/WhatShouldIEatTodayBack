package com.kj.WhatShouldIEatTodayBack.api.controller;


import com.kj.WhatShouldIEatTodayBack.Exception.KakaoServerIsNotRespondException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 예외 처리 핸들러
 *  @author KwangJong Jeon
 *  @since 0.1
 */
@ControllerAdvice
public class ExceptionController {

    /**
     * @param exception KakaoServerIsNotRespondException을 인자로 받습니다.
     * @return 502 Bad Gateway Http status code를 리턴합니다.
     */
    @ExceptionHandler(KakaoServerIsNotRespondException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ResponseEntity<String> handleKakaoIsNotRespondException(KakaoServerIsNotRespondException exception) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(exception.getMessage());
    }
}
