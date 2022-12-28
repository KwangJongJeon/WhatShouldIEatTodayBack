package com.kj.WhatShouldIEatTodayBack.service.crawler;

import java.util.List;


// TODO: region이 아닌 lotAddress를 통해 크롤링 하는 메소드가 필요합니다.
public interface CrawlStore {

    /**
     * @param storeName 검색하고자 하는 스토어의 이름
     * @return 해당 상점이 가지고있는 메뉴의 List가 반환됩니다.
     */
    List<Menu> crawlMenu(String storeName);

    /**
     * @param region    주소 (서울, 대전 ... etc)
     * @param storeName 검색하고자 하는 스토어의 이름
     * @return Menu의 List와 상점의 전화번호가 포함된 CrawlResultDto가 리턴됩니다.
     */
    CrawlResultDto crawlWithRegion(String region, String storeName);

    /**
     *
     * @param lotAddress
     * @param storeName
     * @return
     */
    CrawlResultDto crawlWithLotAddress(String lotAddress, String storeName);
}
