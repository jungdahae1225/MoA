package com.moaserver.moa.controller;

import com.moaserver.moa.entity.goal.Goal;
import com.moaserver.moa.entity.goal.GoalRequestDto;
import com.moaserver.moa.exception.GoalException;
import com.moaserver.moa.service.GoalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GoalController {

    private final GoalService goalService;

    /*
    목표달성 추가 API
    해당 년, 월을 입력받아 목표를 작성하는 API
     */

    @PostMapping("/goal/{memberId}")
    public Goal addGoal(@PathVariable Long memberId, @RequestBody @Valid GoalRequestDto goalDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            throw new GoalException("입력값을 다시 확인해주세요");
        }

        return goalService.addGoal(memberId, goalDto);
    }
}
