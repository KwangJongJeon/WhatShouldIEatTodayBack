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
        log.info("url: {}", url);
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
    public CrawlResultDto crawlWithRegion(String region, String storeName) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        String url = getURLFromRegion(region, storeName);
        if(url == null) return null;

        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(LOCAL_SEARCH_WAIT_TIME_MILLIS));


        String phoneNumber;
        List<WebElement> menuNames;
        List<WebElement> prices;


        // 메뉴 Iframe 접근
        try {
            WebElement entryIframe = driver.findElement(By.cssSelector("iframe#entryIframe"));
            log.info("entryIframe = {}", entryIframe);
            if(entryIframe == null) {
                // 메뉴를 찾지 못했다면 searchIFrame이 있는지 확인
                WebElement searchIFrame = driver.findElement(By.cssSelector("iframe#searchIframe"));
                log.info("searchIFrame = {}", searchIFrame);
                if(searchIFrame != null) {
                    driver.switchTo().frame(searchIFrame);

                    try {
                        List<WebElement> elements = driver.findElements(By.cssSelector(".C6RjW>.place_bluelink"));
                        elements.get(0).click();
                    } catch (Exception ex) {
                        log.error(ex.toString());
                        driver.quit();
                        return null;
                    }

                    // 프레임 변경
                    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
                    driver.switchTo().defaultContent();
                    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
                } else {
                    return null;
                }
            }

            // 상점 상세 Iframe 접근
            driver.switchTo().frame(entryIframe);
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));


            menuNames = driver.findElements(By.cssSelector(".place_section_content>ul.jnwQZ>li.gHmZ_" +
                    ">div.yFDCH>div.RhpMT>span.vEsKn"));
            prices = driver.findElements(By.cssSelector(".place_section_content>ul.jnwQZ>li.gHmZ_" +
                    ">div.yFDCH>em"));

            if(menuNames.isEmpty()) {
                menuNames = driver.findElements(By.cssSelector(".place_section_content>ul.mpoxR>li.yhGu6" +
                        ">a>div.MN48z>div.erVoL>div.MENyI"));
                prices = driver.findElements(By.cssSelector(".place_section_content>ul.mpoxR>li.yhGu6" +
                        ">a.Ozh8q>div.MN48z>div.Yrsei>div.gl2cc"));
            }

            phoneNumber = driver.findElement(By.cssSelector("div.x8JmK>span.dry01")).getText();
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

        // 메뉴 크롤링
        for (int i = 0; i < menuNames.size(); i++) {
            try {
                String menuName = menuNames.get(i).getText();
                String price = prices.get(i).getText();
                char[] priceCharArr = price.toCharArray();
                StringBuilder priceBuilder = new StringBuilder();

                // 이름에 \n이 존재할 경우
                if(menuName.indexOf("\n") != -1) {
                    String[] menuTextArr = menuName.split("\n");
                    menuName = menuTextArr[0];
                }

                for (char c : priceCharArr) {
                    if(Character.isDigit(c)) {
                        priceBuilder.append(c);
                    }
                }

                int processedPrice = Integer.parseInt(priceBuilder.toString());
                menuList.add(new Menu(menuName, processedPrice));
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

        CrawlResultDto crawlResultDto = new CrawlResultDto(menuList, phoneNumber);

        return crawlResultDto;
    }



    @Override
    public CrawlResultDto crawlWithLotAddress(String lotAddress, String storeName) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        String url = getURLFromLotAddress(lotAddress, storeName);
        if(url == null) return null;

        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(LOCAL_SEARCH_WAIT_TIME_MILLIS));


        String phoneNumber;
        List<WebElement> menuNames;
        List<WebElement> prices;


        // 메뉴 Iframe 접근
        try {
            WebElement entryIframe = driver.findElement(By.cssSelector("iframe#entryIframe"));

            if(entryIframe == null) {
                // 메뉴를 찾지 못했다면 searchIFrame이 있는지 확인
                WebElement searchIFrame = driver.findElement(By.cssSelector("iframe#searchIframe"));
                if(searchIFrame != null) {
                    driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#searchIframe")));

                    try {
                        List<WebElement> elements = driver.findElements(By.cssSelector(".C6RjW>.place_bluelink"));
                        elements.get(0).click();
                    } catch (Exception ex) {
                        log.error(ex.toString());
                        driver.quit();
                        return null;
                    }

                    // 프레임 변경
                    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
                    driver.switchTo().defaultContent();
                    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
                } else {
                    return null;
                }
            }

            // 상점 상세 Iframe 접근
            driver.switchTo().frame(entryIframe);
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));


            menuNames = driver.findElements(By.cssSelector(".place_section_content>ul.jnwQZ>li.gHmZ_" +
                    ">div.yFDCH>div.RhpMT>span.vEsKn"));
            prices = driver.findElements(By.cssSelector(".place_section_content>ul.jnwQZ>li.gHmZ_" +
                    ">div.yFDCH>em"));

            if(menuNames.isEmpty()) {
                menuNames = driver.findElements(By.cssSelector(".place_section_content>ul.mpoxR>li.yhGu6" +
                        ">a>div.MN48z>div.erVoL>div.MENyI"));
                prices = driver.findElements(By.cssSelector(".place_section_content>ul.mpoxR>li.yhGu6" +
                        ">a.Ozh8q>div.MN48z>div.Yrsei>div.gl2cc"));
            }

            phoneNumber = driver.findElement(By.cssSelector("div.x8JmK>span.dry01")).getText();
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

        // 메뉴 크롤링
        for (int i = 0; i < menuNames.size(); i++) {
            try {
                String menuName = menuNames.get(i).getText();
                String price = prices.get(i).getText();
                char[] priceCharArr = price.toCharArray();
                StringBuilder priceBuilder = new StringBuilder();

                // 이름에 \n이 존재할 경우
                if(menuName.indexOf("\n") != -1) {
                    String[] menuTextArr = menuName.split("\n");
                    menuName = menuTextArr[0];
                }

                for (char c : priceCharArr) {
                    if(Character.isDigit(c)) {
                        priceBuilder.append(c);
                    }
                }

                int processedPrice = Integer.parseInt(priceBuilder.toString());
                menuList.add(new Menu(menuName, processedPrice));
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

        CrawlResultDto crawlResultDto = new CrawlResultDto(menuList, phoneNumber);

        return crawlResultDto;
    }

    private String getURLFromRegion(String region, String storeName) {
        StringBuilder sb = new StringBuilder();

        String naverURL = "https://map.naver.com/v5/search/";
        sb.append(region + " ");
        sb.append(storeName);

        String query = URLEncoder.encode(sb.toString(), StandardCharsets.UTF_8);

        return naverURL + query;
    }


    private String getURLFromLotAddress(String lotAddress, String storeName) {
        StringBuilder sb = new StringBuilder();
        String naverURL = "https://map.naver.com/v5/search/";
        try {
            String[] addresses = lotAddress.split(" ");
            sb.append(addresses[0] + " ");
            sb.append(addresses[1] + " ");
            sb.append(addresses[2] + " ");
            sb.append(storeName);
        } catch (IndexOutOfBoundsException e) {

            return null;
        }

        String query = URLEncoder.encode(sb.toString(), StandardCharsets.UTF_8);

        return naverURL + query;
    }


}
