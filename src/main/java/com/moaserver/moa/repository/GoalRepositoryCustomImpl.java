package com.moaserver.moa.repository;

import com.moaserver.moa.entity.mypage.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Repository
@Transactional
public class GoalRepositoryCustomImpl implements GoalRepositoryCustom{

    private final EntityManager em;


//    Long countByGoals(Member member, String date){
//
//        return em.createQuery("select g from g")
//    }
}
