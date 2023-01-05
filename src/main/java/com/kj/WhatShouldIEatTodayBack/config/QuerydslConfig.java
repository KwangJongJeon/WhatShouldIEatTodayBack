package com.kj.WhatShouldIEatTodayBack.config;

import com.kj.WhatShouldIEatTodayBack.domain.store.respository.StoreRepository;
import com.kj.WhatShouldIEatTodayBack.domain.store.respository.StoreRepositoryImpl;
import com.kj.WhatShouldIEatTodayBack.service.recommendation.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
@RequiredArgsConstructor
public class QuerydslConfig {

    private final EntityManager em;

    @Bean
    public RecommendationService recommendationService() {
        return new RecommendationService(storeRepository());
    }

    @Bean
    public StoreRepository storeRepository() {
        return new StoreRepositoryImpl(em);
    }
}
