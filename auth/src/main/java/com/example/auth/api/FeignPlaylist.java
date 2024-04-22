package com.example.auth.api;

import com.example.auth.dto.request.UpdateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient("PLAYLIST-SERVICE")
public interface FeignPlaylist {

    @GetMapping("/api/v1/playlists")
    Object getAllPlaylists();
    @PutMapping("/user/{uid}")
    public void updatePlaylistUser(
            @RequestBody UpdateRequest request, @PathVariable Long uid
    );
}
