package com.kj.WhatShouldIEatTodayBack.service;

import com.kj.WhatShouldIEatTodayBack.Exception.CategoryMenuIsNotRegistered;
import com.kj.WhatShouldIEatTodayBack.dto.RecommendServiceReq;
import com.kj.WhatShouldIEatTodayBack.dto.ResponseDocument;
import com.kj.WhatShouldIEatTodayBack.dto.SearchLocalAPIReq;
import com.kj.WhatShouldIEatTodayBack.dto.SearchLocalAPIRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RecommendationService {

    private final KakaoAPIClient kakaoAPIClient;

    public ResponseDocument recommendationService(RecommendServiceReq recommendServiceReq) {
        ArrayList<ResponseDocument> localDocumentList = getLocalRestaurantDataFromKakaoAPI(recommendServiceReq);
        return getRandomRestaurantDocument(localDocumentList);
    }

    private ArrayList<ResponseDocument> getLocalRestaurantDataFromKakaoAPI(RecommendServiceReq recommendServiceReq) {

        ArrayList<ResponseDocument> resultDocuments = new ArrayList<>();

        String query = selectCategoryRandomly(recommendServiceReq.getCategories());
        String x = recommendServiceReq.getLongitude();
        String y = recommendServiceReq.getLatitude();
        String radius = recommendServiceReq.getRange();

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

    private ResponseDocument getRandomRestaurantDocument(ArrayList<ResponseDocument> responseDocuments) {
        int randomValue = (int)(Math.random()*responseDocuments.size());
        return responseDocuments.get(randomValue);
    }

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

    private String selectCategoryRandomly(List<String> categories) {

        int randomValue = (int)(Math.random()*categories.size());

        String category = categories.get(randomValue);

        switch (category) {
            case "KoreanFood":
                return "한식";
            case "JapaneseFood":
                return "일식";
            case "ChineseFood":
                return "중식";
            default:
                throw new CategoryMenuIsNotRegistered(category + "는 서비스에 등록되지 않은 카테고리입니다");

        }
    }
}
