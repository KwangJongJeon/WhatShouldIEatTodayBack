package com.kj.WhatShouldIEatTodayBack.service.crawler;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 크롤링한 결과를 저장하는 Dto
 */
@Data
@RequiredArgsConstructor

public class CrawlResultDto {
    private final List<Menu> menuList;
    private final String phoneNumber;

    /**
     * @return 만약 크롤링된 결과가 없다면 빈 ArrayList를 반환합니다
     */
    public List<Menu> getMenuList() {
        if(menuList == null) return new ArrayList<>();
        return menuList;
    }

    /**
     * @return 만약 크롤링된 결과가 없다면 null을 반환합니다.
     */
    public String getPhoneNumber() {
        if(phoneNumber == null) return null;
        return phoneNumber;
    }
}
