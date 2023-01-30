package com.kj.WhatShouldIEatTodayBack.service.search;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SearchServiceTest {

    @Autowired SearchService searchService;

    @Test
    @DisplayName("상점의 이름을 통해 성공적으로 검색이 수행됩니다.")
    void searchStoreByName() {
        // given
        String keyword = "큰손"; // 데이터 베이스의 첫 번째 데이터 이름이 '큰손의정부부대찌개'

        // when
        List<SearchStoreResponseDto> result = searchService.searchStoreByName(keyword);

        // then
        assertThat(result).isNotNull();
        assertThat(result.get(0).getName()).isEqualTo("큰손의정부부대찌개");
    }
}