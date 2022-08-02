package com.moaserver.moa.service;

import com.moaserver.moa.entity.goal.Goal;
import com.moaserver.moa.entity.goal.GoalRequestDto;
import com.moaserver.moa.entity.mypage.Member;
import com.moaserver.moa.repository.GoalRepository;
import com.moaserver.moa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class GoalService {

    private final GoalRepository goalRepository;
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
}

