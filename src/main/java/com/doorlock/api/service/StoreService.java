package com.doorlock.api.service;

import com.doorlock.api.domain.Role;
import com.doorlock.api.domain.Store;
import com.doorlock.api.domain.User;
import com.doorlock.api.dto.StoreCreateRequest;
import com.doorlock.api.repository.StoreRepository;
import com.doorlock.api.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long createStore(StoreCreateRequest request){
        if(userRepository.existsByEmail(request.ownerEmail())){
            throw new IllegalArgumentException("이미 가입된 이메일 입니다.");
        }

        Store store = new Store();
        store.setName(request.name());
        store.setAddress(request.address());
        store.setRelayDeviceId(request.relayDeviceId());
        store.setSensorDeviceId(request.sensorDeviceId());
        store.setDoorOpenSec(request.doorOpenSec());
        store.setEntryToken(UUID.randomUUID().toString());

        storeRepository.save(store);

        User owner = new User();

        owner.setEmail(request.ownerEmail());
        owner.setPasswordHash(passwordEncoder.encode(request.ownerPassword()));
        owner.setName(request.ownerName());
        owner.setRole(Role.OWNER);
        owner.setStore(store);

        userRepository.save(owner);

        return store.getId();
    }
}
