package com.example.auth.global.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter @AllArgsConstructor
@NoArgsConstructor
@Table(name = "USERS"
//        ,indexes = {@Index(columnList = "USER_NAME")}
)
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;
    @Column(name = "USER_NAME")
    private String username;
    @Column(name = "USER_PASSWORD")
    private String password;
    @Column(name = "USER_NICKNAME")
    private String nickname;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Playlist> playlists;
}
