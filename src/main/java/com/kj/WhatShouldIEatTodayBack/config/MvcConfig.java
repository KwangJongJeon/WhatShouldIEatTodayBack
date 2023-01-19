package com.kj.WhatShouldIEatTodayBack.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }

    /**
     * 이미지 저장 디렉토리를 레지스트리에 추가
     * @param dirName 디렉터리 이름
     * @param registry 레지스트리
     */
    private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
        Path uploadDir = Paths.get(dirName);
        String uploadPath = uploadDir.toFile().getAbsolutePath();

        if(dirName.startsWith("../")) dirName = dirName.replace("../", "");

        registry.addResourceHandler("/", dirName + "/**").addResourceLocations("file:/" + uploadPath + "/");
    }
}
