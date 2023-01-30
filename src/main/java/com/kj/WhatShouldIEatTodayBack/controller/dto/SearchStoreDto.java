package com.kj.WhatShouldIEatTodayBack.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SearchStoreDto {
    @NotEmpty
    private String keyword;
}
