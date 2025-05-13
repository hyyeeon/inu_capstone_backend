package com.capstone.startmap.domain.user.controller;

import com.capstone.startmap.config.security.jwt.JwtAuthenticationFilter;
import com.capstone.startmap.domain.user.dto.oauth.KakaoProfile;
import com.capstone.startmap.domain.user.dto.oauth.OAuthRequest;
import com.capstone.startmap.domain.user.dto.oauth.OAuthToken;
import com.capstone.startmap.domain.user.dto.response.LoginUserResponse;
import com.capstone.startmap.domain.user.service.OauthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/kakao")
@Tag(name = "Kakao", description = "Kakao API")
public class OauthController {

    private final OauthService oauthService;

    @PostMapping("/callback")
    @Operation(summary = "카카오 로그인 토큰 생성", description = "카카오 인가 코드를 입력하면 백엔드 서버 액세스토큰과 회원정보를 리턴합니다.")
    public ResponseEntity<LoginUserResponse> callback(@RequestBody OAuthRequest request) throws JsonProcessingException {
        OAuthToken token = oauthService.getKakaoOAuthToken(request.getCode());
        KakaoProfile profile = oauthService.getKakaoProfile(token.getAccess_token());
        LoginUserResponse response = oauthService.registerOrLoginUser(profile);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtAuthenticationFilter.AUTHORIZATION_HEADER, "Bearer " + response.getAccessToken());

        return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
    }

}

