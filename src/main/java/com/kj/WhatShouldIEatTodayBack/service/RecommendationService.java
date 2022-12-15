package com.kj.WhatShouldIEatTodayBack.service;

import java.math.BigDecimal;

public class RecommendationService {

    /**
     * 해당 공식에 따라 값을 구했습니다.
     * https://kj97.tistory.com/98
     * @param latitude
     * @param longitude
     * @param rangeIn
     * @return
     */
    private CoordinateRange getRange(String latitude, String longitude, int rangeIn) {

        BigDecimal latitudeCenter = new BigDecimal(latitude);
        BigDecimal longitudeCenter = new BigDecimal(longitude);


        /**
         * Distance는 들어온 range값이 meter단위이므로
         * range를 km단위로 만들기 위해 1000을 나눈 값입니다.
         */
        BigDecimal distance = new BigDecimal(rangeIn);
        BigDecimal RADIUS = BigDecimal.valueOf(6371.009);

        BigDecimal latitudeStart = latitudeCenter.subtract(distance.divide(RADIUS));
        BigDecimal latitudeEnd = latitudeCenter.add(distance.divide(RADIUS));
        BigDecimal longitudeStart = longitudeCenter.add(distance);
        BigDecimal longitudeEnd = longitudeCenter.subtract(distance);

        return new CoordinateRange(latitudeStart, latitudeEnd, longitudeStart, longitudeEnd);
    }

    private void getDataListFromDB() {

    }
}
