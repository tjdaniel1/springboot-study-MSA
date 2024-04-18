package com.example.song.global.domain.dto;

import java.time.LocalDateTime;

public record SongDto(
         Long id,
         String title,
         String lyrics,
         LocalDateTime createdAt
) {
}
