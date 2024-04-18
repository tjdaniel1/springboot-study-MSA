package com.example.auth.service;

import com.example.auth.dto.response.UserResponse;
import com.example.auth.global.domain.entity.User;
import com.example.auth.global.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;
    @Override
    public void save(Map<String, String> map) {
//        User user = new User(
//                null, map.get("username")
//                ,map.get("password"), map.get("nickname")
//        );
        User user = new User(null, "test", "test","Test",null);
        userRepository.save(user);
    }

    @Override
    public List<UserResponse> getAll() {

        return userRepository.findAll().stream().map(UserResponse::from).toList();
    }
}
