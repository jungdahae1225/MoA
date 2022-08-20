package com.moaserver.moa.controller;

import com.moaserver.moa.entity.store.Store;
import com.moaserver.moa.entity.store.StoreDto;
import com.moaserver.moa.repository.StoreRepository;
import com.moaserver.moa.service.store.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;
    private final StoreRepository storeRepository;

    //마일리지 사용처 등록
    @PostMapping("/store")
    public ResponseEntity<List<Store>> Store() {
        List<Store> all = storeRepository.findAll();
        return new ResponseEntity(all, HttpStatus.CREATED);
    }

    //주변 사용처
    @GetMapping("/store")
    public ResponseEntity<List<Store>> StoreAll(@PathVariable Long memberId) {
        List<Store> nearBy = storeService.findNearBy(memberId);
        return new ResponseEntity(nearBy,HttpStatus.CREATED);
    }

    //주변 사용처
    @GetMapping("/store/{memberid}")
    public ResponseEntity<List<Store>> location(@PathVariable Long memberId) {
        List<Store> nearBy = storeService.findNearBy(memberId);
        return new ResponseEntity(nearBy,HttpStatus.CREATED);
    }

}
