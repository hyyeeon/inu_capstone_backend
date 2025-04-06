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

    public static User of(String nickname, String email, String password) {
        User user = new User();
        user.setNickname(nickname);
        user.setEmail(email);
        //User.setPassword(password);
        return user;
    }
}
