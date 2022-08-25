package com.moaserver.moa.service;

import com.moaserver.moa.entity.mypage.Member;
import com.moaserver.moa.entity.scrap.Scrap;
import com.moaserver.moa.entity.scrap.ScrapRequestDto;
import com.moaserver.moa.entity.scrap.ScrapResponseDto;
import com.moaserver.moa.entity.store.Store;
import com.moaserver.moa.repository.MemberRepository;
import com.moaserver.moa.repository.ScrapRepository;
import com.moaserver.moa.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ScrapService {

    MemberRepository memberRepository;
    StoreRepository storeRepository;
    ScrapRepository scrapRepository;



    /*
    사용처 스크랩
     */
    public Scrap scrapResister(Long memberId, ScrapRequestDto scrapDto){


        Member member = memberRepository.findById(memberId).get();

        Store store = storeRepository.findById(scrapDto.getStore().getId()).get();

        Scrap scrap = scrapRepository.save(scrapDto.toEntity());

        scrap.setMember(member);
        scrap.setStore(store);


        return scrap;
    }

    
    /*
    마이페이지 스크랩 목록 조회
     */

    @Transactional(readOnly = true)
    public List<ScrapResponseDto> getScraps(Long memberId){

        Member member = memberRepository.findById(memberId).get();

        List<Scrap> scraps = scrapRepository.findByMember(member);

        List<ScrapResponseDto> scrapList = new ArrayList<>();

        for(Scrap scrap : scraps){

            ScrapResponseDto scrapDto = new ScrapResponseDto(scrap.getId(), scrap.getMember(), scrap.getStore()
            );

            scrapList.add(scrapDto);
        }



        return scrapList;

    }
    

    /*
    스크랩 삭제
     */

    public Long deleteScrap(Long scrap_Id) {

        scrapRepository.deleteById(scrap_Id);

        return scrap_Id;
    }

}
