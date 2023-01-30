package com.kj.WhatShouldIEatTodayBack.controller;

import com.kj.WhatShouldIEatTodayBack.service.search.SearchService;
import com.kj.WhatShouldIEatTodayBack.service.auth.AuthService;
import com.kj.WhatShouldIEatTodayBack.service.auth.MemberInfoDto;
import com.kj.WhatShouldIEatTodayBack.service.search.SearchStoreResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Size;
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
    public String searchStoreForm() {
        return "page/search/store";
    }

    @PostMapping("/store/{keyword}")
    public String searchStore(@PathVariable("keyword") @Size(min=1, max=64) String keyword,
                              Model model) {
        List<SearchStoreResponseDto> searchResults = searchService.searchStoreByName(keyword);
        model.addAttribute("searchResults", searchResults);
        return "page/search/storeDetail";
    }
}
