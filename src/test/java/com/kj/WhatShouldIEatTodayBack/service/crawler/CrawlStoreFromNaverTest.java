package com.kj.WhatShouldIEatTodayBack.service.crawler;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


class CrawlStoreFromNaverTest {

    @DisplayName("정상적으로 크롤링되어진다.")
    @Test
    public void crawlFromNaverMap() {
        CrawlStoreFromNaver crawler = new CrawlStoreFromNaver();
        List<Menu> menus = crawler.crawlMenu("이태리국시");

        assertThat(menus.size()).isGreaterThan(0);
    }


    @DisplayName("지역과 함께 쿼리가 넘어왔을 경우 정상적으로 크롤링 되어진다.")
    @Test
    public void crawlWithRegion() {
        CrawlStoreFromNaver crawler = new CrawlStoreFromNaver();
        CrawlResultDto crawlResultDto = crawler.crawlWithRegion("대전", "이태리국시");
        List<Menu> menus = crawlResultDto.getMenuList();
        String phoneNumber = crawlResultDto.getPhoneNumber();

        assertThat(menus.size()).isGreaterThan(0);
        assertThat(phoneNumber).isNotNull();
    }

    @DisplayName("지번과 함께 쿼리가 넘어왔을 경우 정상적으로 크롤링 되어진다.")
    @Test
    void crawlWithLotAddress() {
        CrawlStoreFromNaver crawler = new CrawlStoreFromNaver();
        CrawlResultDto crawlResultDto = crawler.crawlWithLotAddress("대전 서구 둔산동", "이태리국시");
        List<Menu> menus = crawlResultDto.getMenuList();
        String phoneNumber = crawlResultDto.getPhoneNumber();

        assertThat(menus.size()).isGreaterThan(0);
        assertThat(phoneNumber).isNotNull();
    }

//    @Test
//    void getURLTest() {
//        CrawlStoreFromNaver crawlStoreFromNaver = new CrawlStoreFromNaver();
//        String urlFromRegion = crawlStoreFromNaver.getURLFromRegion("대전", "이태리국시");
//        System.out.println(urlFromRegion);
//    }
}