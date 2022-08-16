package com.moaserver.moa.repository;

import com.moaserver.moa.entity.mypage.Member;

import java.time.YearMonth;

public interface GoalRepositoryCustom {

    Long countByGoals(Member member, YearMonth date);


}
