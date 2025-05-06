package com.capstone.startmap.domain.user.dto.oauth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "토큰 DTO")
public class OAuthToken {
    @Schema(description = "액세스 토큰, 헤더 Authentication 키에 넣을 값")
    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    private String scope;
    private int refresh_token_expires_in;

}