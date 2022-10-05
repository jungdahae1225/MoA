package com.moaserver.moa.controller;

import com.moaserver.moa.entity.scrap.Scrap;
import com.moaserver.moa.entity.scrap.ScrapRequestDto;
import com.moaserver.moa.entity.scrap.ScrapResponseDto;
import com.moaserver.moa.service.ScrapService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import java.util.List;

public class ScrapController {


    ScrapService scrapService;


    /*
    스크랩 등록 API
     */

    @PostMapping("/scrap/{memberId}")
    public Scrap addScrap(@PathVariable Long memberId, @RequestBody ScrapRequestDto scrapDto) {

        return scrapService.scrapResister(memberId, scrapDto);
    }


    /*
    스크랩 삭제 API
    */
    @DeleteMapping("/scrap/{scrapId}")
    public Long deleteMember(@PathVariable Long id) {

        Long scrapId = scrapService.deleteScrap(id);

        return scrapId;
    }



    /*
      스크랩 목록 조회 API
  */
    @GetMapping("/scrap/myPage/{memberId}")
    public List<ScrapResponseDto> getScrap(@PathVariable Long memberId) {

        List<ScrapResponseDto> scraps = scrapService.getScraps(memberId);

        return scraps;
    }
}


