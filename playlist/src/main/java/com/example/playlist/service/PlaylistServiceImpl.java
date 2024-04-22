package com.example.playlist.service;

import com.example.playlist.config.TokenInfo;
import com.example.playlist.dto.request.UpdateRequest;
import com.example.playlist.global.domain.repository.PlaylistRepository;
import com.example.playlist.dto.request.PlaylistRequest;
import com.example.playlist.dto.response.PlaylistResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl
implements PlaylistService{
    private final PlaylistRepository playlistRepository;
    @Override
    public void save(PlaylistRequest playlist, TokenInfo tokenInfo) {
        playlistRepository.save(playlist.toEntity(tokenInfo));
    }

    @Override
    public List<PlaylistResponse> getAll() {
//        playlistRepository.deleteById(id);

//        List<PlaylistResponse> responses = new ArrayList<>();

//        List<Playlist> all = playlistRepository.findAll();
//        for (Playlist playlist : all) {
//            PlaylistResponse response = new PlaylistResponse(playlist.getId(),
//                    playlist.getTitle(),
//                    new UserDto(
//                            playlist.getUser().getId()
//                            , playlist.getUser().getNickname()
//                    ));
//            responses.add(response);
//        }
        return playlistRepository.findAll()
                .stream()
                .map(PlaylistResponse::from)
                .toList();
//        return responses;
    }

    @Override
    @Transactional
    public void updateUser(UpdateRequest request, Long uid) {
        //update playlists set nickname = ? where userId = uid
        playlistRepository.updateUserNickname(request.nickname(), uid);
    }


}
