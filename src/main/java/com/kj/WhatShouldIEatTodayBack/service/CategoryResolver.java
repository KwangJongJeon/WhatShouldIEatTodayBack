package com.kj.WhatShouldIEatTodayBack.service;

import com.kj.WhatShouldIEatTodayBack.enums.CategoryTypes;

import java.util.ArrayList;
import java.util.List;

public class CategoryResolver {

    /**
     * @param categories 영어로 들어오는 카테고리 명 (ex. KOREAN, CHINESE)
     * @return 영어를 한글로 번역합니다 (ex. KOREAN -> 한식)
     */
    public List<String> resolve(List<String> categories) {

        List<String> result = new ArrayList<>();

        for (String category : categories) {
            result.add(CategoryTypes.valueOf(category).getDescription());
        }

        return result;
    }

}
