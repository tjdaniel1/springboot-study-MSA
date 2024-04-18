package com.example.playlist.service;

import com.example.playlist.global.domain.repository.PlaylistRepository;
import com.example.playlist.dto.request.PlaylistRequest;
import com.example.playlist.dto.response.PlaylistResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService{
    private final PlaylistRepository playlistRepository;
    @Override
    public void save(PlaylistRequest req) {
        playlistRepository.save(req.toEntity());
    }

    @Override
    public List<PlaylistResponse> getAll() {
        return playlistRepository.findAll()
                .stream()
                .map(PlaylistResponse::from)
                .toList();
//        return playlistRepository.findAll();
    }



}
