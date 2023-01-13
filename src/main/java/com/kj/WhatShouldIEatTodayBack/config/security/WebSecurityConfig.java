package com.kj.WhatShouldIEatTodayBack.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/auth/login").permitAll()
                .and()
                    .authorizeRequests()
                    .antMatchers("/css/**").permitAll()
                .and()
                    .authorizeRequests()
                    .antMatchers("/js/**").permitAll()
                .and()
                    .formLogin()
                    .loginPage("/auth/login")
                    .usernameParameter("memberEmail")
                    .passwordParameter("memberPw");

//        http.formLogin();

        http.authorizeRequests()
                .anyRequest()
                .authenticated();


    }
}
