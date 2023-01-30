package com.kj.WhatShouldIEatTodayBack.domain.store.respository;

import com.kj.WhatShouldIEatTodayBack.domain.store.Store;
import com.kj.WhatShouldIEatTodayBack.service.recommendation.CoordinateRange;

import java.util.List;
import java.util.Optional;

public interface StoreRepository {

    public Store save(Store store);
    public Optional<Store> findById(Long id);
    public List<Store> findAllInRange(CoordinateRange coordinateRange);
    public List<Store> findByCategoryAndInRange(CoordinateRange coordinateRange, List<String> categories);
    public List<Store> findByStoreName(String storeName);
}
