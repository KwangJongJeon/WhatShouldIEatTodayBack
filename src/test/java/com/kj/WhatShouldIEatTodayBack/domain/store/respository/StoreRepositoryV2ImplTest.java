package com.kj.WhatShouldIEatTodayBack.domain.store.respository;

import com.kj.WhatShouldIEatTodayBack.domain.store.Store;
import com.kj.WhatShouldIEatTodayBack.service.CoordinateRange;
import com.kj.WhatShouldIEatTodayBack.service.RecommendationService;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class StoreRepositoryImplTest {

    @Autowired
    StoreRepository storeRepository;
    Store store;

    @BeforeEach
    void beforeEach() {
        store = Store.builder()
                .name("Test")
                .region("서울")
                .divisionOne("음식")
                .divisionTwo("한식")
                .divisionThree("비빔밥")
                .lotAddress("Test")
                .streetAddress("Test")
                .latitude(37.62059)
                .longitude(126.699974)
                .build();
    }


    @Test
    void findById() {
        Store savedStore = storeRepository.save(store);
        Optional<Store> storeFromRepo = storeRepository.findById(savedStore.getId());

        assertThat(storeFromRepo.get().getName()).isEqualTo("Test");
    }

    @Test
    void findAllInRange() {
        BigDecimal latitudeStart = BigDecimal.valueOf(36.347431);
        BigDecimal latitudeEnd = BigDecimal.valueOf(36.350569);
        BigDecimal longitudeStart = BigDecimal.valueOf(127.377630);
        BigDecimal longitudeEnd = BigDecimal.valueOf(127.391970);

        CoordinateRange coordinateRange = new CoordinateRange(latitudeStart, latitudeEnd, longitudeStart, longitudeEnd);
        List<Store> allInRange = storeRepository.findAllInRange(coordinateRange);

        System.out.println("allInRange = " + allInRange.get(0).getName());
        assertThat(allInRange.get(0)).isNotNull();
    }
}