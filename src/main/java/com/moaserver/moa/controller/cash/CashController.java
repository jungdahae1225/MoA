package com.moaserver.moa.controller.cash;

import com.moaserver.moa.entity.Member;
import com.moaserver.moa.entity.cash.Cash;
import com.moaserver.moa.entity.location.Location;
import com.moaserver.moa.entity.mileage.Mileage;
import com.moaserver.moa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//응답 정보는 이후 다시 수정

@RestController
@RequiredArgsConstructor
public class CashController {
    private final MemberRepository memberRepository;

    @GetMapping("/cash/{memberid}")
    public ResponseEntity<Location> cash(@PathVariable Long memberId) {
        Member member = memberRepository.findById(memberId).get();
        Cash cash = member.getCash();
        //멤버의 cash 정보를 불러옴
        return new ResponseEntity(cash, HttpStatus.CREATED);
    }

    @GetMapping("/mileage/{memberid}")
    public ResponseEntity<Location> mileage(@PathVariable Long memberId) {

        Member member = memberRepository.findById(memberId).get();
        Mileage mileage = member.getMileage();

        //멤버의 mileage 정보를 불러옴
        return new ResponseEntity(mileage, HttpStatus.CREATED);
    }


}
