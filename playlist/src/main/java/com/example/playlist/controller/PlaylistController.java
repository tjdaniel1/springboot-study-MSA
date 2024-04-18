package com.example.playlist.controller;

import com.example.playlist.dto.request.PlaylistRequest;
import com.example.playlist.dto.response.PlaylistResponse;
import com.example.playlist.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/playlists")
public class PlaylistController {
    private final PlaylistService playlistService;

    @GetMapping
    public List<PlaylistResponse> getAll() {
        return playlistService.getAll();
    }

    @PostMapping
    public void save(@RequestBody PlaylistRequest req) {
        playlistService.save(req);
    }


}
