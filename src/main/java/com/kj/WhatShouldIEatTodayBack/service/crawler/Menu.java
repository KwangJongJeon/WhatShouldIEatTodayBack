package com.kj.WhatShouldIEatTodayBack.service.crawler;

import lombok.Data;

@Data
public class Menu {
    String name;
    int price;

    public Menu(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
