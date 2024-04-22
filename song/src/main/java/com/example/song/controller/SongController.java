package com.example.song.controller;

import com.example.song.global.domain.entity.Song;
import com.example.song.dto.request.SongRequest;
import com.example.song.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/songs")
@RequiredArgsConstructor
public class SongController {
    private final SongService songService;
    @GetMapping
    public List<Song> getAll(){
        return songService.getAll();
    }
    @PostMapping
    public void save(@RequestBody SongRequest req){
        songService.save(req);
    }
    @GetMapping("/{id}")
    public Song getById(@PathVariable("id") Long id){
        return songService.getById(id);
    }
}
