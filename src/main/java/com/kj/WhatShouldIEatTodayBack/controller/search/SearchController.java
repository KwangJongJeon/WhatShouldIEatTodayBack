package com.kj.WhatShouldIEatTodayBack.controller.search;

import com.kj.WhatShouldIEatTodayBack.service.search.SearchService;
import com.kj.WhatShouldIEatTodayBack.service.auth.AuthService;
import com.kj.WhatShouldIEatTodayBack.service.auth.MemberInfoDto;
import com.kj.WhatShouldIEatTodayBack.service.search.SearchStoreResponseDetailDto;
import com.kj.WhatShouldIEatTodayBack.service.search.SearchStoreResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Size;
import java.util.List;


@Slf4j
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

    @GetMapping("/store/result")
    public String searchStore(@RequestParam("keyword") @Size(min=1, max=64) String keyword,
                              @RequestParam(name = "page", defaultValue = "1") int page,
                              Model model) {

        // 총 게시물 수
        int totalListCnt = searchService.getResultCnt(keyword);
        log.info("TOTALLISTCNT: {}", totalListCnt);

        // 생성 인자로 총 게시물 수, 현재 페이지를 전달
        Pagination pagination = new Pagination(totalListCnt, page);

        log.info("TotalPageCnt: {}", pagination.getTotalPageCnt());
        log.info("endPage: {}", pagination.getEndPage());

        // DB select start index
        int startIndex = pagination.getStartIndex();

        // 페이지당 보여지는 게시글의 최대 개수
        int pageSize = pagination.getPageSize();

        List<SearchStoreResponseDto> storeList = searchService.searchByStoreNamePaging(keyword, startIndex, pageSize);

        model.addAttribute("keyword", keyword);
        model.addAttribute("storeList", storeList);
        model.addAttribute("pagination", pagination);


        return "page/search/storeResult";
    }

    @GetMapping("/store/result/{id}")
    public String searchStoreResult(@PathVariable Long id, Model model) {
        SearchStoreResponseDetailDto searchResult = searchService.searchByStoreId(id);

        model.addAttribute("searchResult", searchResult);
        return "page/search/storeDetail";
    }

}
