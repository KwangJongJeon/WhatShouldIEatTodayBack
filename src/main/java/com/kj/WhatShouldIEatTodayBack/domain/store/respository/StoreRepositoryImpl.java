package com.kj.WhatShouldIEatTodayBack.domain.store.respository;

import com.kj.WhatShouldIEatTodayBack.exception.NoSearchResultException;
import com.kj.WhatShouldIEatTodayBack.exception.recommendation.CoordinateIsNotValid;
import com.kj.WhatShouldIEatTodayBack.domain.store.QStore;
import com.kj.WhatShouldIEatTodayBack.domain.store.Store;
import com.kj.WhatShouldIEatTodayBack.service.recommendation.CoordinateRange;
import com.querydsl.core.BooleanBuilder;
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

    @Override
    public List<Store> findByCategoryAndInRange(CoordinateRange coordinateRange, List<String> categories) {
        double latitudeStart = coordinateRange.getLatitudeStart().doubleValue();
        double latitudeEnd = coordinateRange.getLatitudeEnd().doubleValue();
        double longitudeStart = coordinateRange.getLongitudeStart().doubleValue();
        double longitudeEnd = coordinateRange.getLongitudeEnd().doubleValue();

        QStore store = QStore.store;

        BooleanBuilder builder = new BooleanBuilder();
        BooleanBuilder likeBuilder = new BooleanBuilder();

        // 위도 경도 조건 추가
        builder.and(betweenLatitude(latitudeStart, latitudeEnd));
        builder.and(betweenLongitude(longitudeStart, longitudeEnd));

        // 입력된 카테고리를 순회하며 LIKE 조건을 추가합니다.
        for (String category : categories) {
            likeBuilder.or(QStore.store.divisionTwo.like("%" + category + "%"));
        }

        builder.and(likeBuilder);



        return query
                .select(store)
                .from(store)
                .where(builder)
                .fetch();
    }

    @Override
    public List<Store> findByStoreName(String storeName) {
        QStore store = QStore.store;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(likeName(storeName));

        return query
                .select(store)
                .from(store)
                .where(builder)
                .fetch();
    }

    private BooleanExpression likeName(String storeName) {
        BooleanExpression result = QStore.store.name.like("%" + storeName + "%");
        return result;
    }

    private BooleanExpression betweenLatitude(double latitudeStart, double latitudeEnd) {
        BooleanExpression result = QStore.store.latitude.between(latitudeStart, latitudeEnd);

        if(result == null) {
            throw new CoordinateIsNotValid("잘못된 위도, 경도 입력입니다.");
        }

        return result;
    }

    private BooleanExpression betweenLongitude(double longitudeStart, double longitudeEnd) {
        BooleanExpression result = QStore.store.longitude.between(longitudeStart, longitudeEnd);

        if(result == null) {
            throw new CoordinateIsNotValid("잘못된 위도, 경도 입력입니다.");
        }

        return result;
    }

}
