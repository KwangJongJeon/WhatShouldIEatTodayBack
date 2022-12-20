package com.kj.WhatShouldIEatTodayBack.domain.store.respository;

import com.kj.WhatShouldIEatTodayBack.domain.store.QStore;
import com.kj.WhatShouldIEatTodayBack.domain.store.Store;
import com.kj.WhatShouldIEatTodayBack.service.CoordinateRange;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class StoreRepositoryImpl implements StoreRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;

    public StoreRepositoryImpl(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Store save(Store store) {
        em.persist(store);
        return store;
    }

    @Override
    public Optional<Store> findById(Long id) {
        Store store = em.find(Store.class, id);
        return Optional.ofNullable(store);
    }

    /**
     * @param coordinateRange latitude의 시작과 끝, longitude의 시작과 끝을 가지고 있는 Dto
     * @return coordinateRange의 조건에 맞는 모든 스토어의 리스트를 리턴합니다.
     */
    @Override
    public List<Store> findAllInRange(CoordinateRange coordinateRange) {
        double latitudeStart = coordinateRange.getLatitudeStart().doubleValue();
        double latitudeEnd = coordinateRange.getLatitudeEnd().doubleValue();
        double longitudeStart = coordinateRange.getLongitudeStart().doubleValue();
        double longitudeEnd = coordinateRange.getLongitudeEnd().doubleValue();

        QStore store = QStore.store;

        return query
                .select(store)
                .from(store)
                .where(
                        betweenLatitude(latitudeStart, latitudeEnd),
                        betweenLongitude(longitudeStart, longitudeEnd)
                      )
                .fetch();
    }

    private BooleanExpression betweenLatitude(double latitudeStart, double latitudeEnd) {
        return QStore.store.latitude.between(latitudeStart, latitudeEnd);
    }

    private BooleanExpression betweenLongitude(double longitudeStart, double longitudeEnd) {
        return QStore.store.longitude.between(longitudeStart, longitudeEnd);
    }
}
