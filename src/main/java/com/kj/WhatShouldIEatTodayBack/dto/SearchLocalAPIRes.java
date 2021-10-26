package com.kj.WhatShouldIEatTodayBack.dto;


import lombok.Data;

import java.util.List;

/**
 * 카카오 API로부터 받아온 데이터를 저장하는 객체입니다.
 * <p>
 *     Data 어노테이션을 사용해서 toString, Getter, Setter등을 사용 할 수 있습니다.
 * </p>
 * @author  Jeon KwangJong
 * @since   0.1
 */
@Data
public class SearchLocalAPIRes {

    List<ResponseDocument> documents;
    ResponseMeta meta;
}
