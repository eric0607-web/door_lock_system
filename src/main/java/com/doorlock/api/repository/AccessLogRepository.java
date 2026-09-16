package com.doorlock.api.repository;

import com.doorlock.api.domain.AccessLog;
import com.doorlock.api.domain.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccessLogRepository extends JpaRepository<AccessLog, Long> {
    Page<AccessLog> findByStoreIdOrderByOccurredAtDesc(Long storeId, Pageable pageable);//관련값을 페이지 형식으로 조회
}
