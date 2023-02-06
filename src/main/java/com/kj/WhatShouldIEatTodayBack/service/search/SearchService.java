package com.kj.WhatShouldIEatTodayBack.service.search;


import com.kj.WhatShouldIEatTodayBack.domain.store.Store;
import com.kj.WhatShouldIEatTodayBack.domain.store.respository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchService {

    private final StoreRepository storeRepository;

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
}
