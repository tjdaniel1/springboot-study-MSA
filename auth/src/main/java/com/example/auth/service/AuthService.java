package com.example.auth.service;

import com.example.auth.dto.request.LoginRequest;
import com.example.auth.dto.request.SignupRequest;
import com.example.auth.dto.request.UpdateRequest;
import com.example.auth.dto.response.UserResponse;

import java.util.List;
import java.util.Map;

public interface AuthService {
    void save(Map<String, String> map);
    List<UserResponse> getAll();
    void signup(SignupRequest request);
    String login(LoginRequest request);
    Object getPlayList();
    void update(UpdateRequest updateRequest, Long id);
}
