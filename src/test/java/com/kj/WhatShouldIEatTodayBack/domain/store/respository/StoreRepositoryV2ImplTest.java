package com.kj.WhatShouldIEatTodayBack.domain.store.respository;

import com.kj.WhatShouldIEatTodayBack.domain.store.Store;
import com.kj.WhatShouldIEatTodayBack.service.CoordinateRange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
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
    CoordinateRange coordinateRange;

    @BeforeEach
    void beforeEach() {
        store = Store.builder()
                .name("Test")
                .region("대전")
                .divisionOne("음식")
                .divisionTwo("한식")
                .divisionThree("비빔밥")
                .lotAddress("Test")
                .streetAddress("Test")
                .latitude(36.3467)
                .longitude(127.3848)
                .build();

        BigDecimal latitudeStart = BigDecimal.valueOf(36.347431);
        BigDecimal latitudeEnd = BigDecimal.valueOf(36.350569);
        BigDecimal longitudeStart = BigDecimal.valueOf(127.377630);
        BigDecimal longitudeEnd = BigDecimal.valueOf(127.391970);

        coordinateRange = new CoordinateRange(latitudeStart, latitudeEnd, longitudeStart, longitudeEnd);
    }


    @Test
    void findById() {
        Store savedStore = storeRepository.save(store);
        Optional<Store> storeFromRepo = storeRepository.findById(savedStore.getId());

        assertThat(storeFromRepo.get().getName()).isEqualTo("Test");
    }

    @Test
    void findAllInRange() {
        List<Store> allInRange = storeRepository.findAllInRange(coordinateRange);

        System.out.println("allInRange = " + allInRange.get(0).getName());
        assertThat(allInRange.get(0)).isNotNull();
    }


    @Test
    @DisplayName("주어진 카테고리가 하나일때 정상적으로 작동합니다")
    void findByCategoryAndInRangeOne() {
        List<String> categories = new ArrayList<>();
        categories.add("한식");

        List<Store> result  = storeRepository.findByCategoryAndInRange(coordinateRange, categories);

        System.out.println(result.size());

        assertThat(result.isEmpty()).isEqualTo(false);
    }

    @Test
    @DisplayName("주어진 카테고리가 두개일때 정상적으로 작동합니다.")
    void findByCategoryAndInRangeTwo() {
        List<String> categories = new ArrayList<>();
        categories.add("한식");
        categories.add("일식");

        List<Store> result  = storeRepository.findByCategoryAndInRange(coordinateRange, categories);

        System.out.println(result.size());

        assertThat(result.isEmpty()).isEqualTo(false);
    }
}