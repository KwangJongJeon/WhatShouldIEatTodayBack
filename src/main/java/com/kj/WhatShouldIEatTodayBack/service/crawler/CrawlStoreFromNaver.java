package com.kj.WhatShouldIEatTodayBack.service.crawler;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.IllegalFormatConversionException;
import java.util.List;

/**
 * 셀레니움을 사용해 웹을 크롤링하는 클래스
 */
@Slf4j
@Service
public class CrawlStoreFromNaver implements CrawlStore {

    private final int LOCAL_SEARCH_WAIT_TIME_MILLIS = 5000;

    @Override
    public List<Menu> crawlMenu(String storeName) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        StringBuilder sb = new StringBuilder();
        sb.append("https://map.naver.com/v5/search/");
        sb.append(URLEncoder.encode(storeName, StandardCharsets.UTF_8));

        String url = sb.toString();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#searchIframe")));

        try {
            List<WebElement> elements = driver.findElements(By.cssSelector(".C6RjW>.place_bluelink"));
            elements.get(0).click();
        } catch (Exception e) {
            log.error(e.toString());
            driver.quit();
            return null;
        }


        // 프레임 변경
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.switchTo().defaultContent();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#entryIframe")));

        List<WebElement> placeSectionContents = driver.findElements(By.cssSelector(".place_section_content"));
        WebElement menuElement = placeSectionContents.get(placeSectionContents.size() - 1);
        List<WebElement> menus = menuElement.findElements(By.cssSelector("ul>li"));

        List<Menu> menuList = new ArrayList<>();
        for (WebElement menu : menus) {
            try {
                String[] menuInfo = menu.getText().split("\n");
                String name = menuInfo[0];
                char[] priceCharArr  = menuInfo[1].toCharArray();
                StringBuilder priceBuilder = new StringBuilder();

                for (char c : priceCharArr) {
                    if(Character.isDigit(c)) {
                        priceBuilder.append(c);
                    }
                }
                int price = Integer.parseInt(priceBuilder.toString());
                menuList.add(new Menu(name, price));
            } catch (ArrayIndexOutOfBoundsException e) {
                log.error(e.toString());
                continue;
            } catch (IllegalFormatConversionException e) {
                log.error(e.toString());
                continue;
            }
        }

        driver.quit();

        return menuList;
    }



    @Override
    public List<Menu> crawlMenuWithRegion(String region, String storeName) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        StringBuilder sb = new StringBuilder();
        sb.append("https://map.naver.com/v5/search/");
        sb.append(URLEncoder.encode(region + " " + storeName, StandardCharsets.UTF_8));

        String url = sb.toString();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(LOCAL_SEARCH_WAIT_TIME_MILLIS));

        List<WebElement> menus;

        try {
            driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#entryIframe")));
            List<WebElement> placeSectionContents = driver.findElements(By.cssSelector(".place_section_content"));
            WebElement menuElement = placeSectionContents.get(placeSectionContents.size() - 1);
            menus = menuElement.findElements(By.cssSelector("ul>li"));
        } catch(NoSuchElementException e){
            log.error(e.toString());
            driver.quit();
            return null;
        } catch(NoSuchFrameException e){
            log.error(e.toString());
            driver.quit();
            return null;
        } catch(StaleElementReferenceException e){
            log.error(e.toString());
            driver.quit();
            return null;
        }

        List<Menu> menuList = new ArrayList<>();

        for (WebElement menu : menus) {
            try {
                String[] menuInfo = menu.getText().split("\n");
                String name = menuInfo[0];
                char[] priceCharArr  = menuInfo[1].toCharArray();
                StringBuilder priceBuilder = new StringBuilder();

                for (char c : priceCharArr) {
                    if(Character.isDigit(c)) {
                        priceBuilder.append(c);
                    }
                }
                int price = Integer.parseInt(priceBuilder.toString());
                menuList.add(new Menu(name, price));
            } catch (ArrayIndexOutOfBoundsException e) {
                log.error(e.toString());
                continue;
            } catch (IllegalFormatConversionException e) {
                log.error(e.toString());
                continue;
            } catch (NumberFormatException e) {
                log.error(e.toString());
                continue;
            }
        }

        driver.quit();

        return menuList;
    }


}
