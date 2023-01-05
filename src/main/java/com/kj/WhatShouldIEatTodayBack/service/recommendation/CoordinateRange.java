package com.kj.WhatShouldIEatTodayBack.service.recommendation;

import lombok.Data;

import java.math.BigDecimal;

/**
 * latitudeStart, latitudeEnd, longitudeStart, longitudeEnd는 원의 각 끝을 의미합니다.
 *      ----
 *     --LS--
 *   -LS    LE-
 *     --LE--
 *      ---
 */
@Data
public class CoordinateRange {
    private BigDecimal latitudeStart;
    private BigDecimal latitudeEnd;
    private BigDecimal longitudeStart;
    private BigDecimal longitudeEnd;

    public CoordinateRange(BigDecimal latitudeStart, BigDecimal latitudeEnd, BigDecimal longitudeStart, BigDecimal longitudeEnd) {
        this.latitudeStart = latitudeStart;
        this.latitudeEnd = latitudeEnd;
        this.longitudeStart = longitudeStart;
        this.longitudeEnd = longitudeEnd;
    }
}
