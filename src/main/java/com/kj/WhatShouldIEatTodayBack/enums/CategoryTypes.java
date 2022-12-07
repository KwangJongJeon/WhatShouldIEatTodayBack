package com.kj.WhatShouldIEatTodayBack.enums;

public enum CategoryTypes {
    KOREAN("한식"),
    WESTERN("양식"),
    JAPANESE("일식"),
    CHINESE("중식");

    private final String description;

    CategoryTypes(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
