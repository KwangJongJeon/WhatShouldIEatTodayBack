package com.kj.WhatShouldIEatTodayBack.Exception;

/**
 * RecommendService에 등록하지 않은 카테고리가 입력으로 넘어왔을 경우 발생하는 예외
 */
public class CategoryMenuIsNotRegistered extends RuntimeException {

    public CategoryMenuIsNotRegistered(String message) {
        super(message);
    }
}
