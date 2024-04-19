package com.example.auth.config;

import com.example.auth.global.domain.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenUtils {

    //     만료 시간이 존재 한다(ms)
    @Value("${token.expiration}")
    private Long tokenExpiration;
    //    secretkey 존재한다
    @Value("${token.secret}")
    private String tokenSecret;


    // 토큰 만들기
    public String createToken(User user) {
        SecretKey secretKey = Keys.hmacShaKeyFor(tokenSecret.getBytes());
        String token = Jwts.builder()
                .claim("id", user.getId())
                .claim("nickname", user.getNickname())
                .signWith(secretKey)
                .expiration(new Date(new Date().getTime() + tokenExpiration))
                .compact();
        return token;
    }

    //토큰 parse
    public boolean parseToken(String token) {
        Claims payload = (Claims) Jwts
                .parser()
                .verifyWith(Keys.hmacShaKeyFor(tokenSecret.getBytes()))
                .build()
                .parse(token)
                .getPayload();
        System.out.println(payload);
        return true;
    }

    // 토큰 검증
    public boolean validateToken(String token){
        Claims payload = (Claims) Jwts
                .parser()
                .verifyWith(Keys.hmacShaKeyFor(tokenSecret.getBytes()))
                .build()
                .parse(token)
                .getPayload();
        System.out.println(payload);
        return true;
    }

}