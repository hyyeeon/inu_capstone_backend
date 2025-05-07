package com.capstone.startmap.domain.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "회원가입 응답 DTO")
public class CreateUserResponse {
    @Schema(description = "유저 고유 id")
    private Long user_id;
    @Schema(description = "자체 회원가입이라 null값만 나옴")
    private String kakao_id;
    @Schema(description = "유저 설정 닉네임")
    private String nickname;
    @Schema(description = "유저 설정 이메일")
    private String email;
}