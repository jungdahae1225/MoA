package com.moaserver.moa.repository;

import com.moaserver.moa.entity.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
