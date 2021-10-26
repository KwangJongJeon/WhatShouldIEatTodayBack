package com.kj.WhatShouldIEatTodayBack.api.controller;

import com.kj.WhatShouldIEatTodayBack.Exception.KakaoServerIsNotRespondException;
import org.springframework.stereotype.Component;

@Component
public class MockKakao {

    public void exceptionThrow() {
        throw new KakaoServerIsNotRespondException("카카오 서버가 응답하지 않습니다");
    }
}
