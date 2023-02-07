package com.kj.WhatShouldIEatTodayBack.service.search;


import com.kj.WhatShouldIEatTodayBack.domain.review.Review;
import com.kj.WhatShouldIEatTodayBack.domain.review.repository.ReviewRepository;
import com.kj.WhatShouldIEatTodayBack.domain.store.Store;
import com.kj.WhatShouldIEatTodayBack.domain.store.respository.StoreRepository;
import com.kj.WhatShouldIEatTodayBack.service.review.dto.ReviewResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class SearchService {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    public List<SearchStoreResponseDto> searchStoreByName(String storeName) {

        List<Store> storeList = storeRepository.findByStoreName(storeName);
        List<SearchStoreResponseDto> result = new ArrayList<>();

        for (Store store : storeList) {
            result.add(SearchStoreResponseDto.builder()
                    .id(store.getId())
                    .name(store.getName())
                    .divisionOne(store.getDivisionOne())
                    .divisionTwo(store.getDivisionTwo())
                    .divisionThree(store.getDivisionThree())
                    .lotAddress(store.getLotAddress())
                    .streetAddress(store.getStreetAddress())
                    .build());
        }

        return result;
    }

//    public List<SearchStoreResponseDto> searchStoreByNameWithPage(String storeName) {
//
//        List<Store> storeList = storeRepository.findByStoreName(storeName);
//        List<SearchStoreResponseDto> result = new ArrayList<>();
//
//        for (Store store : storeList) {
//            result.add(SearchStoreResponseDto.builder()
//                    .id(store.getId())
//                    .name(store.getName())
//                    .divisionOne(store.getDivisionOne())
//                    .divisionTwo(store.getDivisionTwo())
//                    .divisionThree(store.getDivisionThree())
//                    .lotAddress(store.getLotAddress())
//                    .streetAddress(store.getStreetAddress())
//                    .build());
//        }
//
//        return result;
//    }

    public int getResultCnt(String keyword) {
        return storeRepository.findByStoreNameCnt(keyword);
    }

    public List<SearchStoreResponseDto> searchByStoreNamePaging(String keyword, int startIndex, int pageSize) {
        List<Store> storeList = storeRepository.findByStoreNamePaging(keyword, startIndex, pageSize);
        List<SearchStoreResponseDto> result = new ArrayList<>();

        for (Store store : storeList) {
            result.add(SearchStoreResponseDto.builder()
                    .id(store.getId())
                    .region(store.getRegion())
                    .name(store.getName())
                    .divisionOne(store.getDivisionOne())
                    .divisionTwo(store.getDivisionTwo())
                    .divisionThree(store.getDivisionThree())
                    .lotAddress(store.getLotAddress())
                    .streetAddress(store.getStreetAddress())
                    .build());
        }

        return result;
    }

    @Transactional
    public SearchStoreResponseDetailDto searchByStoreId(Long id) {
        Optional<Store> storeOpt = storeRepository.findById(id);

        if(storeOpt.isEmpty()) {
            throw new NoResultException("해당 상점이 존재하지 않습니다.");
        }

        Store store = storeOpt.get();


        SearchStoreResponseDetailDto dto = SearchStoreResponseDetailDto.builder()
                .id(store.getId())
                .region(store.getRegion())
                .name(store.getName())
                .divisionOne(store.getDivisionOne())
                .divisionTwo(store.getDivisionTwo())
                .divisionThree(store.getDivisionThree())
                .lotAddress(store.getLotAddress())
                .streetAddress(store.getStreetAddress())
                .build();

        dto.initReviews(reviewRepository.findReviewByStore(store.getId()));

        return dto;
    }
}
