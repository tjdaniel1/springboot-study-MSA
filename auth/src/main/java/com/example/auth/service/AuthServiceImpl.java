package com.example.auth.service;

import com.example.auth.config.JwtTokenUtils;
import com.example.auth.dto.request.LoginRequest;
import com.example.auth.dto.request.SignupRequest;
import com.example.auth.dto.response.UserResponse;
import com.example.auth.global.domain.entity.User;
import com.example.auth.global.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;
    private final JwtTokenUtils jwtTokenUtils;
    private final PasswordEncoder passwordEncoder;
    public String login(LoginRequest request){
        //DB에 있는것을 찾는다 username 가지고 찾아서
        List<User> byUsername = userRepository.findByUsername(request.username());
        if(byUsername.isEmpty()){
            throw new IllegalArgumentException("로그인 실패");
        }
        // 패스워드 비교를 할 것 이다.
        User user = byUsername.get(0);
        if(!passwordEncoder.matches(request.password(), user.getPassword())){
            throw new IllegalArgumentException("로그인 실패");
        }
        //맞으면 토큰을 리턴할것이다.
        return jwtTokenUtils.createToken(user);

    }

    public void signup(SignupRequest request){
        String encoded = passwordEncoder.encode(request.password());
        User entity = request.toEntity(encoded);
        userRepository.save(entity);

    }

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
