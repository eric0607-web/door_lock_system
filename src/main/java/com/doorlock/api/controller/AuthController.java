package com.doorlock.api.controller;

import com.doorlock.api.domain.Role;
import com.doorlock.api.dto.LoginRequest;
import com.doorlock.api.dto.SignupRequest;
import com.doorlock.api.dto.TokenResponse;
import com.doorlock.api.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<TokenResponse> signup(@RequestBody SignupRequest request) {
        String token = authService.signup(
                request.email(),
                request.password(),
                request.name(),
                Role.valueOf(request.role())
        );
        return ResponseEntity.ok(new TokenResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request){
        String token = authService.login(request.email(), request.password());
        return ResponseEntity.ok(new TokenResponse(token));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
