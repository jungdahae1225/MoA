package com.moaserver.moa.service;

import com.moaserver.moa.entity.goal.Goal;
import com.moaserver.moa.entity.goal.GoalRequestDto;
import com.moaserver.moa.entity.goal.GoalResponseDto;
import com.moaserver.moa.entity.mypage.Member;
import com.moaserver.moa.repository.GoalRepository;
import com.moaserver.moa.repository.GoalRepositoryCustomImpl;
import com.moaserver.moa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class GoalService {

    private final GoalRepository goalRepository;
    private final GoalRepositoryCustomImpl goalRepositoryCustom;
    private final MemberRepository memberRepository;

    /*
    목표생성
     */
    public Goal addGoal(Long memberId, GoalRequestDto goalDto){

        Optional<Member> fMember = memberRepository.findById(memberId);
        Member member = fMember.get();

        Goal goal = goalRepository.save(goalDto.toEntity());
        goal.setMember(member);

        return goal;


    }

    /*
    목표수정
     */

    public Goal updateGoal(Long goalId, GoalRequestDto.UpdateDto goalDto){

        Optional<Goal> findGoal = goalRepository.findById(goalId);
        Goal goal = findGoal.get();

        goal.GoalUpdate(goalDto.getContent(), goalDto.getPrice(), goalDto.isCompleted());


        return goalRepository.save(goal);

    }

    
    /*
    목표삭제
     */
    public Long deleteGoal(Long goal_Id) {

        goalRepository.deleteById(goal_Id);

        return goal_Id;
    }


    /*
    해당하는 날짜(년도, 월)의 목표조회
     */

    @Transactional(readOnly = true)
    public List<GoalResponseDto> getGoals(Long memberId, String date){

        Optional<Member> fMember = memberRepository.findById(memberId);
        Member member = fMember.get();

        List<Goal> goals = goalRepository.findByMemberAndDate(member, YearMonth.parse(date));

        List<GoalResponseDto> goalList = new ArrayList<>();

        for(Goal goal : goals){

            GoalResponseDto goalDto = new GoalResponseDto(goal.getId(), goal.getContent(), goal.getPrice(),
                    goal.isCompleted(), goal.getDate()
            );

            goalList.add(goalDto);
        }



        return goalList;

    }




    //월별 목표달성 전체개수
    public Long countByMemberAndDate(Long memberId, String date){

        Optional<Member> fMember = memberRepository.findById(memberId);
        Member member = fMember.get();

        Long cnt = goalRepository.countByMemberAndDate(member, YearMonth.parse(date));

        return cnt;
    }

    public Long completedGoal(Long memberId, String date){

        Optional<Member> fMember = memberRepository.findById(memberId);
        Member member = fMember.get();

        Long cnt = goalRepositoryCustom.completeCount(member, YearMonth.parse(date));

        return cnt;

    }



}

