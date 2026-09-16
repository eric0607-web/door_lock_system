package com.doorlock.api.repository;

import com.doorlock.api.domain.Inquiry;
import com.doorlock.api.domain.InquiryStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
    Page<Inquiry> findByStatusOrderByCreatedAtDesc(InquiryStatus status, Pageable pageable);
    Page<Inquiry> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
