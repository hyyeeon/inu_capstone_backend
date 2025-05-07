package com.capstone.startmap.domain.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "로그인 응답 DTO")
public class LoginUserResponse {
    @Schema(description = "자체 회원가입일 경우: 이메일 | 카카오회원일 경우: 부여된 kakao id")
    private String email;
    @Schema(description = "자체 회원가입일 경우: 유저 설정 닉네임 | 카카오회원일 경우: 부여된 회원nickname")
    private String nickname;
    private String accessToken;
    private String refreshToken;
    private Long expire; //액세스 토큰 만료시간

}
