package com.doorlock.api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "door_state")
public class DoorState {

    @Id
    @Column(name = "store_id")
    private Long storeId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "store_id")
    private Store store;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DoorStatus status;

    @Column(name = "changed_at", nullable = false)
    private LocalDateTime changedAt;
}
