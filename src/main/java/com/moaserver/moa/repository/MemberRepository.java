package com.moaserver.moa.repository;

import com.moaserver.moa.entity.Member;
import com.moaserver.moa.entity.location.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
