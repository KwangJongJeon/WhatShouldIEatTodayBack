package com.kj.WhatShouldIEatTodayBack.domain.review.repository;

import com.kj.WhatShouldIEatTodayBack.domain.member.Member;
import com.kj.WhatShouldIEatTodayBack.domain.review.Review;
import com.kj.WhatShouldIEatTodayBack.domain.store.Store;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ReviewRepositoryImpl implements ReviewRepository  {

    private final EntityManager em;
    private final JPAQueryFactory query;

    public ReviewRepositoryImpl(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Review save(Review review) {
        em.persist(review);
        return review;
    }

    @Override
    public Review update(Review review) {
        Long id = review.getId();
        Review foundReview = em.find(Review.class, id);
        foundReview.setContent(review.getContent());
        return foundReview;
    }

    @Override
    public Review updateContentById(Long id, String content) {
        Review foundReview = em.find(Review.class, id);
        foundReview.setContent(content);
        return foundReview;
    }

    @Override
    public Optional<Review> findById(Long id) {
        String query = "select r from Review r" +
                " join fetch r.member m" +
                " join fetch r.store s" +
                " where r.id = " + id;
        Review review = em.createQuery(query, Review.class).getSingleResult();
        return Optional.ofNullable(review);
    }

    @Override
    public List<Review> findAll() {
        String query = "select r from Review r" +
                " join fetch r.member m" +
                " join fetch r.store";
        return em.createQuery(query).getResultList();
    }


    @Override
    public List<Review> findReviewByMember(Member member) {
        String jpql = "select r from Review r" +
                " join fetch r.member m" +
                " join fetch r.store s" +
                " where r.member.id = " + member.getId();

        List<Review> result = em.createQuery(jpql, Review.class).getResultList();
        return result;
    }

    @Override
    public List<Review> findReviewByStore(Store store) {
        String jpql = "select r from Review r" +
                " join fetch r.member m" +
                " join fetch r.store s" +
                " where r.store.id = " + store.getId();

        List<Review> result = em.createQuery(jpql, Review.class).getResultList();
        return result;
    }

    @Override
    public List<Review> findReviewByStore(Long storeId) {

        String jpql = "select r from Review r" +
                " join fetch r.member m" +
                " join fetch r.store s" +
                " where r.store.id = " + storeId;

        List<Review> result = em.createQuery(jpql, Review.class).getResultList();
        return result;
    }
}
