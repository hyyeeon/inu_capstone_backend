package com.capstone.startmap.domain.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateUserResponse {

    private Long user_id;
    private String kakao_id;
    private String nickname;
    private String email;
}