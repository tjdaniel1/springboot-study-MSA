package com.example.auth.service;

import com.example.auth.dto.response.UserResponse;

import java.util.List;
import java.util.Map;

public interface AuthService {
    void save(Map<String, String> map);
    List<UserResponse> getAll();

}
