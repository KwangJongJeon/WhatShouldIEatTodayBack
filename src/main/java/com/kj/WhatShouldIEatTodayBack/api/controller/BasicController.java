package com.kj.WhatShouldIEatTodayBack.api.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BasicController {
    @RequestMapping(value="/")
    public String hello() {
        return "Hello!";
    }
}
