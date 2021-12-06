package com.kj.WhatShouldIEatTodayBack.api.controller;


import com.kj.WhatShouldIEatTodayBack.Exception.CategoryMenuIsNotRegistered;
import com.kj.WhatShouldIEatTodayBack.Exception.KakaoServerIsNotRespondException;
import com.kj.WhatShouldIEatTodayBack.Exception.UserSessionIsNotValid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 예외 처리 핸들러
 *  @author KwangJong Jeon
 *  @since 0.1
 *
 *  @TODO : 예외 로그 파일로 저장하기 -> slf4j
 */
@Slf4j
@ControllerAdvice
public class ExceptionController {

    /**
     * 카카오 서버가 응답하지 않을경우 발생하는 예외
     * @param exception KakaoServerIsNotRespondException을 인자로 받습니다.
     * @return 502 Bad Gateway Http status code를 리턴합니다.
     */
    @ExceptionHandler(KakaoServerIsNotRespondException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ResponseEntity<String> handleKakaoIsNotRespondException(KakaoServerIsNotRespondException exception) {
        log.error("KakaoServerIsNotRespondException was thrown: " + exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(exception.getMessage());
    }


    /**
     * 프론트에서 제공한 카테고리 값이 서버에 등록되어 있지 않을 경우 발생하는 예외
     * @param exception CategoryMenuIsNotRegistered를 인자로 받습니다
     * @return 400 Bad Request Http status code를 리턴합니다.
     */
    @ExceptionHandler(CategoryMenuIsNotRegistered.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleCategoryMenuIsNotRegistered(CategoryMenuIsNotRegistered exception) {
        log.error("CategoryMenuIsNotRegisteredException was thrown: " + exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    /**
     * 유효하지 않은 세션이 들어왔을 경우 발생하는 예외
     */
    @ExceptionHandler(UserSessionIsNotValid.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<String> handleUserSessionIsNotValid(UserSessionIsNotValid excpetion) {
        log.error("UserSessionIsNotValid was thrown: " + excpetion.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(excpetion.getMessage());
    }

}
