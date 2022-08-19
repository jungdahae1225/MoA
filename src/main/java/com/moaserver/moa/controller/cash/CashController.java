package com.moaserver.moa.controller.cash;

import com.moaserver.moa.controller.cash.dto.CashDepositRequestDto;
import com.moaserver.moa.controller.cash.dto.CashWithdrawRequestDto;
import com.moaserver.moa.entity.cash.Cash;
import com.moaserver.moa.entity.mypage.Member;
import com.moaserver.moa.errors.UserException;
import com.moaserver.moa.repository.MemberRepository;
import com.moaserver.moa.service.cash.CashService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CashController {
    private final MemberRepository memberRepository;
    private final CashService cashService;
    
    //총 현금 조회
    @GetMapping("/cash/{memberId}")
    public ResponseEntity cash(@PathVariable Long memberId) {
        Member member = memberRepository.findById(memberId).get();
        Cash cash = member.getCash();
        //멤버의 cash 정보를 불러옴
        return new ResponseEntity(cash, HttpStatus.CREATED);
    }

    //날력에 표시할 현금 detail 정보
    @GetMapping("/cash/detail/{memberId}")
    public ResponseEntity cashDetail(@PathVariable Long memberId) {
       List<Cash> cashes = cashService.getMyCash(memberId);
        return new ResponseEntity(cashes, HttpStatus.CREATED);
    }

    //현금 입금
    @PostMapping("/cash/deposit/{memberId}")
    public ResponseEntity DepositCash(@PathVariable Long memberId, @RequestBody CashDepositRequestDto cashDepositRequestDto) {

        Member member = memberRepository.findById(memberId).get();
        Double cash = member.getCash().getCashBalance() + cashDepositRequestDto.getCash(); //총액수에 더하기
        member.getCash().setCashBalance(cash);

        cashService.saveNewDeposit(cashDepositRequestDto); //입금 객체 저장

        //멤버의 mileage 정보를 불러옴
        return new ResponseEntity(cashDepositRequestDto, HttpStatus.CREATED);
    }

    //현금 출금
    @PostMapping("/cash/withdraw/{memberId}")
    public ResponseEntity WithdrawCash(@PathVariable Long memberId, @RequestBody CashWithdrawRequestDto cashWithdrawRequestDto) {

        Member member = memberRepository.findById(memberId).get();
        Double cash = member.getCash().getCashBalance() - cashWithdrawRequestDto.getCash(); //총 액수에서 빼기

        if(cash < 0){
            throw new UserException("입력값이 잘못 되었습니다.");
        }
        member.getCash().setCashBalance(cash);

        cashService.saveNewWithdraw(cashWithdrawRequestDto);//출금 객체 저장

        //멤버의 mileage 정보를 불러옴
        return new ResponseEntity(cashWithdrawRequestDto, HttpStatus.CREATED);
    }

    /**
     * @param memberId
     * 멤버의 마일리지 조회
     * @return
     */
    @GetMapping("/mileage/{memberid}")
    public ResponseEntity<Member> mileage(@PathVariable Long memberId) {

        Optional<Member> findMember = memberRepository.findById(memberId);
        Member member = findMember.get();

        //멤버의 mileage 정보를 불러옴
        return new ResponseEntity(member, HttpStatus.CREATED);
    }


}
