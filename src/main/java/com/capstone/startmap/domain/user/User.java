package com.capstone.startmap.domain.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id",nullable = false)
    private Long user_id;

    @Column(name="kakao_id")
    private String kakao_id;

    @Column(name="nickname",nullable = false)
    private String nickname;

    private String email;

    private String password;

    @Builder
    private User(String kakao_id, String nickname,
                 String email, String password){
        this.kakao_id=kakao_id;
        this.nickname=nickname;
        this.email=email;
        this.password=password;
    }
    public static User of(String nickname, String email, String password, String kakao_id) {
        return User.builder()
                .kakao_id(kakao_id)
                .nickname(nickname)
                .email(email)
                .password(password)
                .build();
    }
}
