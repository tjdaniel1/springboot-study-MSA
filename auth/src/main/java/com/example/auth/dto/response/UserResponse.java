package com.example.auth.dto.response;

import com.example.auth.global.domain.dto.PlaylistDto;
import com.example.auth.global.domain.entity.User;

import java.util.List;

public record UserResponse(
        Long id,
        String nickname,
        List<PlaylistDto> playlists


) {
    public static UserResponse from(User user){
        List<PlaylistDto> list = user.getPlaylists()
                .stream()
                .map(playlist ->
                        new PlaylistDto(playlist.getId(), playlist.getTitle()))
                .toList();

        return new UserResponse(user.getId(), user.getNickname(), list);
    }
}
