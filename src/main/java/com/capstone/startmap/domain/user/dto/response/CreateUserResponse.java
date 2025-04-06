package com.capstone.startmap.domain.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateUserResponse {

    private Long id;
    private String email;
    private String nickname;

}