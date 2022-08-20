package com.moaserver.moa.controller.cash.dto;

import com.moaserver.moa.entity.cash.Cash;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CashWithdrawRequestDto {
    private Double cash;
    private String withdrawDate;

    public Cash toEntity(){
        return Cash.builder()
                .cashBalance(cash)
                .withdrawDate(withdrawDate)
                .build();
    }
}
