package com.doorlock.api.dto;

public record LoginRequest(
        String email,
        String password
) {
}
