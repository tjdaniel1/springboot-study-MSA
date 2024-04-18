package com.example.auth.global.domain.repository;

import com.example.auth.global.domain.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    //named query
    List<Playlist> findByTitle(String title);

    @Query("select pl from Playlist pl " +
    "join fetch pl.user u " +
    "where u.nickname like concat('%',:nickname,'%')")
    List<Playlist> findAllWithUsers(@Param("nickname") String nickname);

}