package com.capstone.startmap.domain.user;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id",nullable = false)
    private long user_id;

    @Column(name="kakao_id")
    private String kakao_id;

    @Column(name="nickname",nullable = false)
    private String nickname;


}
