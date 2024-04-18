package com.example.song.service;

import com.example.song.global.domain.entity.Song;
import com.example.song.global.domain.repository.SongRepository;
import com.example.song.dto.request.SongRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService{
    private final SongRepository songRepository;
    @Override
    public void save(SongRequest req) {
        songRepository.save(req.toEntity());
    }

    @Override
    public List<Song> getAll() {
        return songRepository.findAll();
    }

    @Override
    @Transactional
    public Song getById(Long id) {
        Optional<Song> byId = songRepository.findById(id);
        Song song = byId.orElse(new Song());return song;
//        if(byId.isEmpty()) return null;
//        return byId.get();
    }

    @Override
    @Transactional
    public Song update(SongRequest req, Long id) {
//        Optional<Song> byId = songRepository.findById(id);
//        if(byId.isEmpty()) throw new IllegalArgumentException();
//        Song song = byId.get();
        Song song = songRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        song.setTitle(req.title());
        song.setLyrics(req.lyrics());

        return null;
    }
}