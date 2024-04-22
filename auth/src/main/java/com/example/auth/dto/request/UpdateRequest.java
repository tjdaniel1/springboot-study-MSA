package com.example.auth.dto.request;

import com.example.auth.global.domain.entity.User;


// put
// ep /api/v1/auth
// body UpdateRequest
// update
public record UpdateRequest(
        String password, String nickname
) {

}
