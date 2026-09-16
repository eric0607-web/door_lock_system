package com.doorlock.api.repository;

import com.doorlock.api.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findByEntryToken(String entryToken);
}
