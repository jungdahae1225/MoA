package com.moaserver.moa.controller;

import com.moaserver.moa.entity.goal.Goal;
import com.moaserver.moa.entity.goal.GoalRequestDto;
import com.moaserver.moa.entity.goal.GoalResponseDto;

import com.moaserver.moa.exception.GoalException;

import com.moaserver.moa.service.GoalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GoalController {

    private final GoalService goalService;

    /*
    목표달성 작성 API
     */

    @PostMapping("/goal/{memberId}")
    public Goal addGoal(@PathVariable Long memberId, @RequestBody @Valid GoalRequestDto goalDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            throw new GoalException("입력값을 다시 확인해주세요");
        }

        return goalService.addGoal(memberId, goalDto);
    }



    /*
    월별 목표달성 조회 API
    해당 년, 월을 파라미터로 입력받아 목표를 월별로 조화하는 API
     */
    @GetMapping("/goal/{memberId}")
    public List<GoalResponseDto> getGoal(@PathVariable Long memberId, @RequestParam String date) {

        List<GoalResponseDto> goals = goalService.getGoals(memberId, date);

        return goals;
    }



    /*
    목표달성 수정 API
     */
    @PutMapping("/goal/update/{goalId}")
    public Goal updateGoal(@PathVariable Long goalId, @RequestBody @Valid GoalRequestDto.UpdateDto updateDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            throw new GoalException("입력값 오류");
        }

        return goalService.updateGoal(goalId, updateDto);


    }


    /*
    목표달성 삭제 API
     */
    @DeleteMapping("/goal/{goalId}")
    public Long deleteGoal(@PathVariable Long goalId) {

        Long id = goalService.deleteGoal(goalId);

        return id;
    }
}
