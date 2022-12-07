package com.kj.WhatShouldIEatTodayBack.api.service;

import com.kj.WhatShouldIEatTodayBack.Exception.CategoryMenuIsNotRegistered;
import com.kj.WhatShouldIEatTodayBack.dto.api.RecommendServiceReq;
import com.kj.WhatShouldIEatTodayBack.dto.api.ResponseDocument;
import com.kj.WhatShouldIEatTodayBack.dto.api.SearchLocalAPIReq;
import com.kj.WhatShouldIEatTodayBack.dto.api.SearchLocalAPIRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 식사 추천 기능을 하는 API Service
 * @author KwangJong Jeon
 * @since 0.1
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class RecommendationService {


    private final KakaoAPIClient kakaoAPIClient;

    /**
     * RecommendationService의 public한 인터페이스로써 식당 추천 기능을 제공한다.
     * @param recommendServiceReq 프론트에서 제공하는 userData, 좌표와 메뉴 카테고리, 검색 범위가 포함된 데이터이다.
     * @return ResponseDocument - KakaoAPI에서 검색된 데이터 중 '한 개'의 식당 데이터
     */
    public ResponseDocument recommendationService(RecommendServiceReq recommendServiceReq) {
        ArrayList<ResponseDocument> localDocumentList = getLocalRestaurantDataFromKakaoAPI(recommendServiceReq);
        return getRandomRestaurantDocument(localDocumentList);
    }

    /**
     * KakaoAPIClient 객체를 이용해 KakaoAPI에서 식당 데이터를 검색해 리스트화된 ResponseDocument로 넘겨주는 메소드.
     * @param recommendServiceReq 프론트에서 제공하는 userData
     * @return ArrayList<ResponseDocument> 카카오 검색 결과를 묶은 리스트
     */
    private ArrayList<ResponseDocument> getLocalRestaurantDataFromKakaoAPI(RecommendServiceReq recommendServiceReq) {

        ArrayList<ResponseDocument> resultDocuments = new ArrayList<>();

        String query = selectCategoryRandomly(recommendServiceReq.getCategories());
        String x = recommendServiceReq.getLongitude();
        String y = recommendServiceReq.getLatitude();
        String radius = recommendServiceReq.getRange();

        log.info("y" + y);


        /*
         * Kakao API에 검색을 두 번 요청한다
         * 첫 번째는 검색결과의 첫 페이지를 대상으로 요청하고,
         * 두 번째는 그 외의 결과중 나머지 페이지 중 하나를 랜덤하게 뽑아 요청한다.
         */
        int count = 2;
        int page = 1;

        SearchLocalAPIReq searchLocalAPIReq;
        SearchLocalAPIRes searchLocalAPIRes;

        while(count-- > 0) {
            searchLocalAPIReq = new SearchLocalAPIReq(query, x, y, radius);
            searchLocalAPIReq.setPage(page);
            searchLocalAPIRes = kakaoAPIClient.localSearch(searchLocalAPIReq);
            page = selectRandomPages(searchLocalAPIRes.getMeta().getPageable_count());
            resultDocuments.addAll(searchLocalAPIRes.getDocuments());
        }

        return resultDocuments;
    }

    /**
     * 카카오 검색 결과인 ArrayList<Document>중 한 개를 랜덤하게 뽑는 메소드.
     * @param responseDocuments 카카오 검색 결과를 묶은 Document List
     * @return ResponseDocument - 식당 검색 결과 중 하나를 리턴한다.
     */
    private ResponseDocument getRandomRestaurantDocument(ArrayList<ResponseDocument> responseDocuments) {
        int randomValue = (int)(Math.random()*responseDocuments.size());
        return responseDocuments.get(randomValue);
    }

    /**
     * 카카오 meta data중 pageable_count를 이용해 랜덤하게 페이지를 뽑는 메소드.
     * pageable_count는 카카오 API에서 검색된 결과의 수다.
     * @param pageableCount 카카오 API에서 검색된 결과의 수
     * @return 1~pageableCount 사이의 랜덤한 수
     */
    private Integer selectRandomPages(int pageableCount) {

        // 페이지가 1페이지만 있거나 비어있을 경우 1페이지를 리턴한다.
        if(pageableCount == 1) {
            return 1;
        }

        int randomPage = 1;

        while(randomPage == 1) {
            randomPage = (int)(Math.random()*pageableCount) + 1;
        }


        return randomPage;
    }

    /**
     * 카테고리를 랜덤하게 하나 뽑아 리턴하는 메소드.
     * @param categories 유저가 보낸 데이터 중 '카테고리'에 해당하는 값들의 리스트
     * @return 카테고리 중 하나를 뽑아 리턴한다.
     *
     */
    private String selectCategoryRandomly(List<String> categories) {

        HashMap<String, Integer> permittedCategory = new HashMap<String, Integer>();
        permittedCategory.put("한식", 1);
        permittedCategory.put("중식", 1);
        permittedCategory.put("일식", 1);
        permittedCategory.put("양식", 1);

        for (String category : categories) {
            if(permittedCategory.getOrDefault(category, 0) != 1)
                throw new CategoryMenuIsNotRegistered(category + "은(는) 서비스에 등록되지 않은 카테고리입니다");
        }

        int randomValue = (int)(Math.random()*categories.size());

        String category = categories.get(randomValue);

        return category;

//        switch (category) {
//            case "KoreanFood":
//                return "한식";
//            case "JapaneseFood":
//                return "일식";
//            case "ChineseFood":
//                return "중식";
//            default:
//                throw new CategoryMenuIsNotRegistered(category + "는 서비스에 등록되지 않은 카테고리입니다");

    }
}

