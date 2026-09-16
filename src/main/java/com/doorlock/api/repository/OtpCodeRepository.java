package com.doorlock.api.repository;

import com.doorlock.api.domain.OtpCode;
import com.doorlock.api.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpCodeRepository extends JpaRepository<OtpCode, Long> {
    Optional<OtpCode> findTopByStoreIdAndPhoneOrderByCreatedAtDesc(Long storeId, String phone);//인증 번호 매치시 가장 최근의 값 매치
}

