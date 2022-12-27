package com.kj.WhatShouldIEatTodayBack.service.crawler;

import java.util.List;

public interface CrawlStore {

    /**
     * @param storeName 검색하고자 하는 스토어의 이름
     * @return 해당 상점이 가지고있는 메뉴의 List가 반환됩니다.
     */
    List<Menu> crawlMenu(String storeName);

    /**
     *
     * @param region 주소 (서울, 대전 ... etc)
     * @param storeName 검색하고자 하는 스토어의 이름
     * @return
     */
    CrawlResultDto crawlMenuWithRegion(String region, String storeName);
}
