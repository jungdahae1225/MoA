package com.moaserver.moa.controller.cash;

import com.moaserver.moa.entity.Member;
import com.moaserver.moa.entity.location.Location;
import com.moaserver.moa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CashController {
    private final MemberRepository memberRepository;

    @GetMapping("/cash/{memberid}")
    public ResponseEntity<Location> cash(@PathVariable Long memberId) {

        Optional<Member> findMember = memberRepository.findById(memberId);
        Member member = findMember.get();

        //멤버의 cash 정보를 불러옴
        return new ResponseEntity(locationDto, HttpStatus.CREATED);
    }

    @GetMapping("/mileage/{memberid}")
    public ResponseEntity<Location> mileage(@PathVariable Long memberId) {

        Optional<Member> findMember = memberRepository.findById(memberId);
        Member member = findMember.get();

        //멤버의 mileage 정보를 불러옴
        return new ResponseEntity(locationDto, HttpStatus.CREATED);
    }


}
