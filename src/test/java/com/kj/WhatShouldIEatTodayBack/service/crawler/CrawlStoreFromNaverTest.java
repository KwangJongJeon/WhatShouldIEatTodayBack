package com.kj.WhatShouldIEatTodayBack.service.crawler;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


class CrawlStoreFromNaverTest {

    @DisplayName("정상적으로 메뉴가 크롤링되어진다.")
    @Test
    public void crawlMenuFromNaverMap() {
        CrawlStoreFromNaver crawler = new CrawlStoreFromNaver();
        List<Menu> menus = crawler.crawlMenu("이태리국시");

        Assertions.assertThat(menus.size()).isGreaterThan(0);
    }


    @DisplayName("지역과 함께 쿼리가 넘어왔을 경우 메뉴가 정상적으로 크롤링 되어진다.")
    @Test
    public void crawlMenuWithRegion() {
        CrawlStoreFromNaver crawler = new CrawlStoreFromNaver();
        List<Menu> menus = crawler.crawlMenuWithRegion("대전", "이태리국시");

        Assertions.assertThat(menus.size()).isGreaterThan(0);
    }
}