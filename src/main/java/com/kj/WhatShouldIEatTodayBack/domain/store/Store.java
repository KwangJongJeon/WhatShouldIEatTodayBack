package com.kj.WhatShouldIEatTodayBack.domain.store;

import lombok.*;

import javax.persistence.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "store")
@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

//    @Builder
//    public Store(String name, String region,
//                 String divisionOne, String divisionTwo, String divisionThree,
//                 String lotAddress, String streetAddress,
//                 double latitude, double longitude) {
//        this.name = name;
//        this.region = region;
//        this.divisionOne = divisionOne;
//        this.divisionTwo = divisionTwo;
//        this.divisionThree = divisionThree;
//        this.lotAddress = lotAddress;
//        this.streetAddress = streetAddress;
//        this.latitude = latitude;
//        this.longitude = longitude;
//    }
}
