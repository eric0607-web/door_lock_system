package com.doorlock.api.repository;

import com.doorlock.api.domain.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findByStoreIdAndResolvedFalseOrderByCreatedAtDesc(Long storeId);
}
