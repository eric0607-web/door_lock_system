package com.doorlock.api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "store")
@NoArgsConstructor
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private  String name;

    @Column(nullable = false)
    private  String address;

    @Column(name = "relay_device_id")
    private  String relayDeviceId;

    @Column(name = "sensor_device_id")
    private  String sensorDeviceId;

    @Column(name = "entry_token", nullable = false, unique = true)
    private  String entryToken;

    @Column(name = "door_open_sec")
    private  Integer doorOpenSec;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}
