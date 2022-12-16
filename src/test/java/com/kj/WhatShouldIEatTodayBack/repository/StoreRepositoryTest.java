package com.kj.WhatShouldIEatTodayBack.repository;

import com.kj.WhatShouldIEatTodayBack.domain.store.Store;
import com.kj.WhatShouldIEatTodayBack.domain.store.StoreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
public class StoreRepositoryTest {

    @Autowired
    StoreRepository storeRepository;

    @Test
    void findByIdTest() {
        Store store = storeRepository.findById(1L).get();
        if(store == null) System.out.println("isNull!");
        else System.out.println(store.getName());

    }
}
