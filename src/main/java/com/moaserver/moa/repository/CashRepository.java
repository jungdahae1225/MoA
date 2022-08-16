package com.moaserver.moa.repository;

import com.moaserver.moa.entity.location.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashRepository extends JpaRepository<Location, Long> {
}
