package com.kj.WhatShouldIEatTodayBack.service;

import com.kj.WhatShouldIEatTodayBack.Exception.InvalidCategoryName;
import com.kj.WhatShouldIEatTodayBack.enums.CategoryTypes;

import java.util.ArrayList;
import java.util.List;

/**
 * 카테고리의 영문명을 한글로 변환해주는 클래스입니다.
 * resolve(List<String> categories)를 사용하면 됩니다.
 */
public class CategoryResolver {

    /**
     * @param categories 영어로 들어오는 카테고리 명 (ex. KOREAN, CHINESE)
     * @return 영어를 한글로 번역합니다 (ex. KOREAN -> 한식)
     */
    public List<String> resolve(List<String> categories) {

        List<String> result = new ArrayList<>();

        for (String category : categories) {
            try {
                result.add(CategoryTypes.valueOf(category).getDescription());
            } catch (IllegalArgumentException e) {
                throw new InvalidCategoryName("잘못된 카테고리 이름입니다.", e);
            }

        }

        return result;
    }

}
