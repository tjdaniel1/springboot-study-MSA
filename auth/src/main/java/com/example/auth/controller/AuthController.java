package com.example.auth.controller;

import com.example.auth.api.FeignPlaylist;
import com.example.auth.config.JwtTokenUtils;
import com.example.auth.config.TokenInfo;
import com.example.auth.dto.request.LoginRequest;
import com.example.auth.dto.request.SignupRequest;
import com.example.auth.dto.request.UpdateRequest;
import com.example.auth.dto.response.UserResponse;
import com.example.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.Token;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthService authService;
    private final FeignPlaylist feignPlaylist;
// put
// ep /api/v1/auth
// body UpdateRequest
// update
    @PutMapping
    public void update(@RequestBody UpdateRequest updateRequest,
                       @RequestHeader("Authorization") String bearerToken){
        TokenInfo tokenInfo = jwtTokenUtils.parseToken(
                bearerToken.substring(7));
        authService.update(updateRequest, tokenInfo.id());
    }

//    header Authorization : Bearer ~~~~
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request){
        return authService.login(request);
    }
    @PostMapping("/signup")
    public void signup(@RequestBody SignupRequest request){
        authService.signup(request);
    }
    @GetMapping("/me")
    public TokenInfo getMe(
            @RequestHeader("Authorization") String bearerToken
    ){
        String token = bearerToken.substring(7);
        return jwtTokenUtils.parseToken(token);
    }

    @GetMapping
    public List<UserResponse> getAll(
            @RequestHeader("Authorization") String bearerToken){
        String token = bearerToken.substring(7);
        jwtTokenUtils.parseToken(token);
        return authService.getAll();
    }
    @PostMapping
    public void save(){
        Map<String,String> map = new HashMap<>();
        map.put("username", "name");
        map.put("nickname", "nickname");
        map.put("password", "password");
        authService.save(map);
    }
    @GetMapping("test")
    public String test(){
        return "강사";
    }

    //    http://localhost:9000/api/v1/auth/play
    @GetMapping("/play")
    public Object getPlaylist(){
        return feignPlaylist.getAllPlaylists();
    }

}

