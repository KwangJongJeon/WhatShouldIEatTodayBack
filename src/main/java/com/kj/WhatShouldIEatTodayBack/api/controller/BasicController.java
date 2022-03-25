package com.kj.WhatShouldIEatTodayBack.api.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin(origins = "https://whatishouldeat.com")
public class BasicController {
    @RequestMapping(value="/")
    public String hello() {
        return "hello";
    }
}
