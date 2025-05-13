package com.capstone.startmap.domain.user.dto.oauth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Schema(description = "카카오 인가코드를 담는 DTO")
public class OAuthRequest {
    @Schema(description = "카카오 인가 코드")
    String code;
}
