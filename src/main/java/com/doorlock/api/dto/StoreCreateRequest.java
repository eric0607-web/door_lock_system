package com.doorlock.api.dto;

public record StoreCreateRequest(
        String name,
        String address,
        String relayDeviceId,
        String sensorDeviceId,
        Integer doorOpenSec,
        String ownerEmail,
        String ownerPassword,
        String ownerName
) {

}
