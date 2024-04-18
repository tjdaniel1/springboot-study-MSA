package com.example.playlist.dto.response;

import com.example.playlist.global.domain.dto.UserDto;
import com.example.playlist.global.domain.entity.Playlist;
import com.example.playlist.global.domain.entity.User;

public record PlaylistResponse(
        Long id, String title, UserDto user
) {
    public static PlaylistResponse from(Playlist playlist) {
        User user = playlist.getUser();
        UserDto userDto = new UserDto(user.getId(), user.getNickname());
        return new PlaylistResponse(playlist.getId(), playlist.getTitle(), userDto);
    }
}
