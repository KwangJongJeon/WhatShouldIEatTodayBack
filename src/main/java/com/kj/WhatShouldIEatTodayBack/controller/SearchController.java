package com.kj.WhatShouldIEatTodayBack.controller;

import com.kj.WhatShouldIEatTodayBack.controller.dto.SearchStoreDto;
import com.kj.WhatShouldIEatTodayBack.service.search.SearchService;
import com.kj.WhatShouldIEatTodayBack.service.auth.AuthService;
import com.kj.WhatShouldIEatTodayBack.service.auth.MemberInfoDto;
import com.kj.WhatShouldIEatTodayBack.service.search.SearchStoreResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/search")
@Controller
public class SearchController {

    private final AuthService authService;
    private final SearchService searchService;

    @ModelAttribute("memberInfoDto")
    public MemberInfoDto memberInfoDto(Authentication authentication) {
        return authService.getMemberInfo(authentication);
    }


    @GetMapping("/store")
    public String searchStoreForm(@ModelAttribute SearchStoreDto searchStoreDto) {
        return "page/searchStore";
    }

    @PostMapping("/store/{keyword}")
    public String searchStore(@ModelAttribute SearchStoreDto searchStoreDto,
                              Model model) {
        List<SearchStoreResponseDto> searchResults = searchService.searchStoreByName(searchStoreDto.getKeyword());
        model.addAttribute("searchResults", searchResults);
        return "page/searchResult";
    }
}
