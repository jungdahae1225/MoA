package com.moaserver.moa.repository;

import com.moaserver.moa.entity.mypage.Member;
import com.moaserver.moa.entity.scrap.Scrap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {


    List<Scrap> findByMember(Member member);

}
