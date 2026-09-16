package com.doorlock.api.repository;

import com.doorlock.api.domain.DoorState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoorStateRepository extends JpaRepository<DoorState, Long> {

}
