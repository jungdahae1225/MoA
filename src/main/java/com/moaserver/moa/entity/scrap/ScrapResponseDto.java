package com.moaserver.moa.entity.scrap;

import com.moaserver.moa.entity.mypage.Member;
import com.moaserver.moa.entity.store.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScrapResponseDto {

    Long scrapId;

    private Member member;

    private Store store;

}
