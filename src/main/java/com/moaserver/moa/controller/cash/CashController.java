package com.moaserver.moa.controller.cash;

import com.moaserver.moa.controller.cash.dto.CashRequestDto;
import com.moaserver.moa.controller.cash.dto.CashResponseDto;
import com.moaserver.moa.entity.cash.Cash;
import com.moaserver.moa.entity.cash.Mileage;
import com.moaserver.moa.entity.mypage.Member;
import com.moaserver.moa.errors.UserException;
import com.moaserver.moa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//응답 정보는 이후 다시 수정

@RestController
@RequiredArgsConstructor
public class CashController {
    private final MemberRepository memberRepository;

    @GetMapping("/cash/{memberId}")
    public ResponseEntity cash(@PathVariable Long memberId) {
        Member member = memberRepository.findById(memberId).get();
        Cash cash = member.getCash();
        //멤버의 cash 정보를 불러옴
        return new ResponseEntity(cash, HttpStatus.CREATED);
    }

    @PostMapping("/cash/deposit/{memberId}")
    public ResponseEntity DepositCash(@PathVariable Long memberId, @RequestBody CashRequestDto cashRequestDto) {

        Member member = memberRepository.findById(memberId).get();
        Double cash = member.getCash().getCashBalance() + cashRequestDto.getCash();
        member.getCash().setCashBalance(cash);

        CashResponseDto cashResponseDto = new CashResponseDto();
        cashResponseDto.setCashBalance(cash);

        //멤버의 mileage 정보를 불러옴
        return new ResponseEntity(cashResponseDto, HttpStatus.CREATED);
    }

    @PostMapping("/cash/withdraw/{memberId}")
    public ResponseEntity WithdrawCash(@PathVariable Long memberId, @RequestBody CashRequestDto cashRequestDto) {

        Member member = memberRepository.findById(memberId).get();
        Double cash = member.getCash().getCashBalance() - cashRequestDto.getCash();

        if(cash < 0){
            throw new UserException("입력값이 잘못 되었습니다.");
        }
        member.getCash().setCashBalance(cash);
        CashResponseDto cashResponseDto = new CashResponseDto();
        cashResponseDto.setCashBalance(cash);

        //멤버의 mileage 정보를 불러옴
        return new ResponseEntity(cashResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/mileage/{memberId}")
    public ResponseEntity mileage(@PathVariable Long memberId) {

        Member member = memberRepository.findById(memberId).get();
        Mileage mileage = member.getMileage();

        //멤버의 mileage 정보를 불러옴
        return new ResponseEntity(mileage, HttpStatus.CREATED);
    }
}
