package com.example.auth.service;

import com.example.auth.api.ApiPlaylist;
import com.example.auth.api.FeignPlaylist;
import com.example.auth.config.JwtTokenUtils;
import com.example.auth.dto.request.LoginRequest;
import com.example.auth.dto.request.SignupRequest;
import com.example.auth.dto.request.UpdateRequest;
import com.example.auth.dto.response.UserResponse;
import com.example.auth.global.domain.entity.User;
import com.example.auth.global.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;
    private final JwtTokenUtils jwtTokenUtils;
    private final PasswordEncoder passwordEncoder;
    private final ApiPlaylist apiPlaylist;
    @Override
    @Transactional
    public void update(UpdateRequest updateRequest, Long id) {
        Optional<User> byId = userRepository.findById(id);
        User user = byId
                .orElseThrow(() -> new IllegalArgumentException("없는 유저"));
        // save 업데이트
        user.setNickname(updateRequest.nickname());
        user.setPassword(
                passwordEncoder.encode(updateRequest.password())
        );
        apiPlaylist.updatePlaylist(updateRequest, id);

//        userRepository.save(user);
        // dirty checking
    }

    public String login(LoginRequest request){
//         디비에 있는 것을 찾는다 username 가지고 찾아서
        List<User> byUsername = userRepository.findByUsername(
                request.username());
        if(byUsername.isEmpty()){
            throw new IllegalArgumentException("로그인 실패");
        }
//        패스워드 비교를 할 것이다
        User user = byUsername.get(0);
        if(!passwordEncoder.matches(request.password(), user.getPassword())){
            throw new IllegalArgumentException("로그인 실패");
        }
//        맞으면 토큰을 리턴 할것이다.
        return jwtTokenUtils.createToken(user);
    }

    @Override
    public Object getPlayList() {
//       const data = await axios("get", "/api/v1/playlists")
        return null;
    }



    public void signup(SignupRequest request){
        String encoded = passwordEncoder.encode(
                request.password());
        if(!userRepository.findByUsername(request.username()).isEmpty())
            throw new IllegalArgumentException("이씀");
        User entity = request.toEntity(encoded);
        userRepository.save(entity);
    }

    @Override
    public void save(Map<String, String> map) {

        User user = new User(null,
                "test",
                "test",
                "test",
                null);
        userRepository.save(user);
    }

    @Override
    public List<UserResponse> getAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponse::from)
                .toList();
    }
}
