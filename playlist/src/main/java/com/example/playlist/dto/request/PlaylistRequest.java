package com.example.playlist.dto.request;


import com.example.playlist.global.domain.entity.Playlist;
import com.example.playlist.global.domain.entity.User;

public record PlaylistRequest(
        String title,
        Long userId
) {
    public Playlist toEntity(){
        return Playlist.builder()
                .title(title)
                .user(new User(userId, null, null, null, null))
                .build();
    }
}
