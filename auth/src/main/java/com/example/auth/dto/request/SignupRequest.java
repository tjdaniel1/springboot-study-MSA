package com.example.auth.dto.request;

import com.example.auth.global.domain.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public record SignupRequest(
        String username, String password, String nickname
) {
    public User toEntity(String encodedPassword){
        return new User(
                null,
                username,
                encodedPassword,
                nickname,
                null
        );
    }
}
