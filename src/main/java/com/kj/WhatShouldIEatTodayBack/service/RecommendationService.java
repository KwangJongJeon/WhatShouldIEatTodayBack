package com.kj.WhatShouldIEatTodayBack.service;

import com.kj.WhatShouldIEatTodayBack.exception.RandomIsNotWorkedProperlyException;
import com.kj.WhatShouldIEatTodayBack.controller.dto.RecommendationRequestDto;
import com.kj.WhatShouldIEatTodayBack.domain.store.RecommendationResultDto;
import com.kj.WhatShouldIEatTodayBack.domain.store.Store;
import com.kj.WhatShouldIEatTodayBack.domain.store.respository.StoreRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Random;

public class RecommendationService {

    private final StoreRepository storeRepository;

    public RecommendationService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    /**
     * 해당 공식에 따라 값을 구했습니다.
     * https://kj97.tistory.com/98
     * @param latitude
     * @param longitude
     * @param rangeIn
     * @return
     */
    public CoordinateRange getRange(String latitude, String longitude, int rangeIn) {

        BigDecimal latitudeCenter = new BigDecimal(latitude);
        BigDecimal longitudeCenter = new BigDecimal(longitude);


        /**
         * Distance는 들어온 range값이 meter단위이므로
         * range를 km단위로 만들기 위해 1000을 나눈 값입니다.
         */
        BigDecimal distance = new BigDecimal(rangeIn).divide(BigDecimal.valueOf(100), 6, RoundingMode.FLOOR);
        BigDecimal RADIUS = BigDecimal.valueOf(6371.009);
        BigDecimal latitudeCos = BigDecimal.valueOf(Math.cos(latitudeCenter.doubleValue()));

        BigDecimal latitudeStart = latitudeCenter.subtract(distance.divide(RADIUS, 6, RoundingMode.FLOOR));
        BigDecimal latitudeEnd = latitudeCenter.add(distance.divide(RADIUS, 6, RoundingMode.FLOOR));
        BigDecimal longitudeStart = longitudeCenter.subtract(distance.divide(latitudeCos.multiply(RADIUS), 6, RoundingMode.FLOOR));
        BigDecimal longitudeEnd = longitudeCenter.add(distance.divide(latitudeCos.multiply(RADIUS), 6, RoundingMode.FLOOR));

        return new CoordinateRange(latitudeStart, latitudeEnd, longitudeStart, longitudeEnd);
    }

    /**
     *
     * @param recommendationRequestDto 클라이언트의 좌표, 카테고리를 포함한 DTO
     * @return 해당 카테고리와 좌표를 포함한 랜덤
     */
    private List<Store> getDataListByCategoryFromDB(RecommendationRequestDto recommendationRequestDto) {

        CategoryResolver categoryResolver = new CategoryResolver();

        String latitudeCenter = recommendationRequestDto.getLatitude();
        String longitudeCenter = recommendationRequestDto.getLongitude();
        List<String> categories = categoryResolver.resolve(recommendationRequestDto.getCategories());
        int range = recommendationRequestDto.getRange();

        CoordinateRange coordinateRange = getRange(latitudeCenter, longitudeCenter, range);


        return storeRepository.findByCategoryAndInRange(coordinateRange, categories);
    }


    /**
     * @param recommendationRequestDto 클라이언트의 좌표, 카테고리를 포함한 Dto
     * @return RequestDto로 들어온 조건에 맞는 값을 검색하고, 결과 중에서 랜덤하게 값을 뽑아 ResultDto로 반환해줍니다.
     */
    public RecommendationResultDto recommendation(RecommendationRequestDto recommendationRequestDto) {
        List<Store> storeList = getDataListByCategoryFromDB(recommendationRequestDto);
        int size = storeList.size();

        Random rand = new Random();
        int selectedStoreIdx = rand.nextInt(size);

        try {
            Store store = storeList.get(selectedStoreIdx);
            RecommendationResultDto resultDto = RecommendationResultDto.builder()
                    .name(store.getName())
                    .region(store.getRegion())
                    .divisionOne(store.getDivisionOne())
                    .divisionTwo(store.getDivisionTwo())
                    .divisionThree(store.getDivisionThree())
                    .lotAddress(store.getLotAddress())
                    .streetAddress(store.getStreetAddress())
                    .latitude(store.getLatitude())
                    .longitude(store.getLongitude()).build();

            return resultDto;
        } catch (OutOfMemoryError e) {
            throw new RandomIsNotWorkedProperlyException("추천도중 잘못된 인덱스가 반환되었습니다.", e);
        }
    }
}
