package com.moaserver.moa.repository;

import com.moaserver.moa.entity.cash.Cash;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CashRepository extends JpaRepository<Cash, Long> {
    List<Cash> findByMemberId(Long memberId);
}
