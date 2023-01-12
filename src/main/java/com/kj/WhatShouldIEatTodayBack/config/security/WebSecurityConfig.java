//package com.kj.WhatShouldIEatTodayBack.config.security;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//
//@Configuration
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .formLogin()
//                .loginPage("/auth/Login")
//                .and()
//                .requiresChannel(channel -> channel.anyRequest().requiresSecure());
//
//        http.authorizeRequests().anyRequest().authenticated();
//    }
//}
