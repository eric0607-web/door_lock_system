package com.doorlock.api.dto;

public record SignupRequest(
        String email,
        String password,
        String name,
        String role
) {
}
