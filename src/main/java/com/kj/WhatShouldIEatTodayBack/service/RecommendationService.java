package com.kj.WhatShouldIEatTodayBack.service;

import com.kj.WhatShouldIEatTodayBack.domain.store.respository.StoreRepository;
import org.springframework.data.geo.Distance;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
        BigDecimal distance = new BigDecimal(rangeIn).divide(BigDecimal.valueOf(1000), 6, RoundingMode.FLOOR);
        BigDecimal RADIUS = BigDecimal.valueOf(6371.009);

        BigDecimal latitudeStart = latitudeCenter.subtract(distance.divide(RADIUS, 6, RoundingMode.FLOOR));
        BigDecimal latitudeEnd = latitudeCenter.add(distance.divide(RADIUS, 6, RoundingMode.FLOOR));
        BigDecimal longitudeStart = longitudeCenter.add(distance);
        BigDecimal longitudeEnd = longitudeCenter.subtract(distance);

        return new CoordinateRange(latitudeStart, latitudeEnd, longitudeStart, longitudeEnd);
    }

    private void getDataListFromDB() {

    }
}
