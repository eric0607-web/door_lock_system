package com.doorlock.api.service;

import com.doorlock.api.domain.Role;
import com.doorlock.api.domain.User;
import com.doorlock.api.repository.UserRepository;
import com.doorlock.api.security.JwtProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional
    public  String signup(String email, String rawPassword, String name, Role role){
        if (userRepository.existsByEmail(email)){
            throw new IllegalArgumentException("이미 가입된 메일입니다.");
        }

        User user = new User();
        user.setEmail(email);
        user.setPasswordHash(passwordEncoder.encode(rawPassword));
        user.setName(name);
        user.setRole(role);

        userRepository.save(user);

        return jwtProvider.generateToken(user.getId(), user.getEmail(), user.getRole());
    }

    public  String login(String email, String rawPassword){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다."));

        if (!passwordEncoder.matches(rawPassword, user.getPasswordHash())) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다.");
        }

        return jwtProvider.generateToken(user.getId(), user.getEmail(), user.getRole());
    }

}
