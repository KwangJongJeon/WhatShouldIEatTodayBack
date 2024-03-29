package com.kj.WhatShouldIEatTodayBack.controller.search;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagination {

    // 1. 페이지 당 보여지는 게시글의 최대 개수
    private int pageSize = 10;

    // 2. 페이징된 버튼의 블럭당 최대 개수
    private int blockSize = 10;

    // 3. 현재 페이지
    private int page = 1;

    // 4. 현재 블럭
    private int block = 1;

    // 5. 총 게시글 수
    private int totalListCnt;

    // 6. 총 페이지 수
    private int totalPageCnt;

    // 7. 총 블럭 수
    private int totalBlockCnt;

    // 8. 블럭 시작 페이지
    private int startPage = 1;

    // 9. 블럭 마지막 페이지
    private int endPage = 1;

    // 10. DB 접근 시작 index
    private int startIndex = 0;

    // 11. 이전 블럭의 마지막 페이지
    private int prevBlock;

    // 12. 다음 블럭의 시작 페이지
    private int nextBlock;


    public Pagination(int totalListCnt, int page) {
        // 총 게시물 수와 현재 페이지를 Controller로 부터 받아온다.

        // 총 게시물 수 - totalListCnt
        // 현재 페이지 - page

        // 현재 페이지
        setPage(page);

        // 총 게시글 수
        setTotalListCnt(totalListCnt);

        // 총 페이지 수
        // 한 페이지의 최대 개수를 총 게시물 수 * 1.0로 나누어 주고 올림해준다.
        setTotalPageCnt((int) Math.ceil(totalListCnt * 1.0 / pageSize));

        // 총 블럭 수
        // 한 블럭의 최대 개수를 총 페이지의 수 * 1.0로 나누어주고 올림 해준다.
        setBlock((int) Math.ceil((page * 1.0) / blockSize));

        // 블럭 시작 페이지
        setStartPage((block-1) * blockSize + 1);

        // 블럭 마지막 페이지
        setEndPage(startPage + blockSize - 1);

        // 블럭 마지막 페이지에 대한 validation
        if(endPage > totalPageCnt) {
            this.endPage = totalPageCnt;
        }

        // 이전 블럭(클릭 시, 이전 블럭 마지막 페이지)
        setPrevBlock((block * blockSize) - blockSize);

        // 이전 블럭에 대한 validation
        if(prevBlock < 1) {
            this.prevBlock = 1;
        }

        // 다음 블럭 (클릭 시 다음 블럭 첫번째 페이지)
        setNextBlock((block * blockSize) + 1);

        // 다음 블럭에 대한 validation
        if(nextBlock > totalPageCnt) {
            nextBlock = totalPageCnt;
        }

        // DB 접근 시작 index
        setStartIndex((page-1) * pageSize);
    }
}
