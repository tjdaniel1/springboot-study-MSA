package com.example.playlist.controller;

import com.example.playlist.config.JwtTokenUtils;
import com.example.playlist.config.TokenInfo;
import com.example.playlist.dto.request.PlaylistRequest;
import com.example.playlist.dto.request.UpdateRequest;
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
    private final JwtTokenUtils jwtTokenUtils;
    @GetMapping
    public List<PlaylistResponse> getAllPlaylists() {
        return playlistService.getAll();
    }
    @PostMapping
    public void createPlaylist(
            @RequestBody PlaylistRequest req,
            @RequestHeader("Authorization") String bearerToken
    ) {
        String token = bearerToken.substring(7);
        TokenInfo tokenInfo = jwtTokenUtils.parseToken(token);
        playlistService.save(req, tokenInfo);
    }
    @PutMapping("/user/{uid}")
    public void updatePlaylistUser(
            @RequestBody UpdateRequest request, @PathVariable Long uid
            ){
        playlistService.updateUser(request, uid);
    }
    /*
     * endpoint api/v1/playlists/user/:uid
     * method put
     * body UpdateRequest
     * 내 플레이리스트에 있는
     * 모든것을 update하는 로직을 만든다
     * */
}
