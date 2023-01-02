package com.kj.WhatShouldIEatTodayBack.service.crawler;

import java.util.List;


public interface CrawlStore {

    /**
     * @param storeName 검색하고자 하는 스토어의 이름
     * @return 해당 상점이 가지고있는 메뉴의 List가 반환됩니다.
     */
    @Deprecated
    List<Menu> crawlMenu(String storeName);

    /**
     * @param region    주소 (서울, 대전 ... etc)
     * @param storeName 검색하고자 하는 스토어의 이름
     * @return Menu의 List와 상점의 전화번호가 포함된 CrawlResultDto가 리턴됩니다. 검색 결과가 없을 경우 null이 리턴됩니다.
     */
    CrawlResultDto crawlWithRegion(String region, String storeName);

    /**
     *
     * @param lotAddress 지번 (ex. 대전 서구 ㅇㅇ동...)
     * @param storeName 검색하고자 하는 스토어의 이름
     * @return Menu의 List와 상점의 전화번호가 포함된 CrawlResultDto가 리턴됩니다. 검색 결과가 없을 경우 null이 리턴됩니다.
     */
    CrawlResultDto crawlWithLotAddress(String lotAddress, String storeName);
}
