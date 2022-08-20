package com.moaserver.moa.service.cash;

import com.moaserver.moa.controller.cash.dto.CashDepositRequestDto;
import com.moaserver.moa.controller.cash.dto.CashWithdrawRequestDto;
import com.moaserver.moa.entity.cash.Cash;
import com.moaserver.moa.repository.CashRepository;
import com.moaserver.moa.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class CashService {

    private final CashRepository cashRepository;
    private final MemberRepository memberRepository;

    public Cash saveNewDeposit(CashDepositRequestDto cashDepositRequestDto) {
        Cash cash = cashDepositRequestDto.toEntity();
        cashRepository.save(cash);
        return cash;
    }

    public Cash saveNewWithdraw(CashWithdrawRequestDto cashWithdrawRequestDto) {
        Cash cash = cashWithdrawRequestDto.toEntity();
        cashRepository.save(cash);
        return cash;
    }

    public List<Cash> getMyCash(Long memberId) {
        List<Cash> cashes = cashRepository.findAll();
        List<Cash> myCashes = new ArrayList<>();

        for(Cash cash : cashes){
            if(cash.getMember().getId() == memberId)
                myCashes.add(cash);
        }
        return myCashes;
    }
}
