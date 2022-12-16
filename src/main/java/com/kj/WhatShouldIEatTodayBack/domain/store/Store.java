package com.kj.WhatShouldIEatTodayBack.domain.store;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Getter
@Table(name = "store")
@Entity
public class Store {
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String region;
    @Column(nullable = false)
    private String divisionOne;

    @Column(nullable = true)
    private String divisionTwo;
    @Column(nullable = true)
    private String divisionThree;
    @Column(nullable = true)
    private String lotAddress;

    @Column(nullable = false)
    private String streetAddress;

    @Column(nullable = false)
    double latitude;
    @Column(nullable = false)
    double longitude;

}
