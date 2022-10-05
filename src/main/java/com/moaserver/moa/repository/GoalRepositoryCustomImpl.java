package com.moaserver.moa.repository;

import com.moaserver.moa.entity.mypage.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.YearMonth;

@RequiredArgsConstructor
@Repository
@Transactional
public class GoalRepositoryCustomImpl implements GoalRepositoryCustom {

    private final EntityManager em;

    @Override
    public Long completeCount(Member member, YearMonth date) {
        return em.createQuery("select count(g) from Goal g where g.member = :member and g.date = :date and g.completed = true",
                        Long.class)
                .setParameter("member", member)
                .setParameter("date", date)
                .getSingleResult();
    }
}



