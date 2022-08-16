package com.moaserver.moa.entity.goal;


import lombok.Getter;

import java.time.YearMonth;

@Getter
public class GoalResponseDto {

    private Long goalId;

    private String content;

    private Long price;

    private boolean completed;

    private YearMonth date;


    public GoalResponseDto(Long goalId, String content, Long price,  boolean completed, YearMonth date){

        this.goalId = goalId;
        this.content = content;
        this.price = price;
        this.completed = completed;
        this.date = date;
    }


}
