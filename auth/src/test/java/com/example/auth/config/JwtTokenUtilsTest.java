package com.example.auth.config;

import com.example.auth.global.domain.entity.User;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtTokenUtilsTest {

    @Test
    void createToken() {
        JwtTokenUtils jwtTokenUtils = new JwtTokenUtils();
        String token = jwtTokenUtils.createToken(
                new User(1l, "1","1","1",null)
        );
        System.out.println(token);
        Assertions.assertNotNull(token);
    }

    @Test
    void validateToken() {
        JwtTokenUtils jwtTokenUtils = new JwtTokenUtils();
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwibmlja25hbWUiOiIxIiwiZXhwIjoxNzEzNTA0MjY2fQ.Cght3xvygb-v9LKjqj4oH4xktPbjbbP4azCquYft9Rg";
        Assertions.assertThrows(
                ExpiredJwtException.class,
                () -> jwtTokenUtils.validateToken(token));
    }
    @Test
    void validateToken1() {
        JwtTokenUtils jwtTokenUtils = new JwtTokenUtils();
        String token = jwtTokenUtils.createToken(
                new User(1l, "1","1","1",null)
        );
        Assertions.assertNotNull(jwtTokenUtils.validateToken(token));
//        Assertions.assertEquals(true, jwtTokenUtils.validateToken(token));
    }

    @Test
    void validateToken2() {
        JwtTokenUtils jwtTokenUtils = new JwtTokenUtils();
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
        assertThrows(SignatureException.class, () -> jwtTokenUtils.validateToken(token));
//        jwtTokenUtils.validateToken(token);
    }
}